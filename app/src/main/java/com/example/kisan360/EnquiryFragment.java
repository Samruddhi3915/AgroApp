package com.example.kisan360;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kisan360.Api.APIService;
import com.example.kisan360.Api.APIUrl;
import com.example.kisan360.Model.DataModel;
import com.example.kisan360.Utility.SessionManager;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnquiryFragment extends BottomSheetDialogFragment {
EditText edtName,edtMobile,edtRemark;
TextView btnSubmit;
ProgressDialog progressDialog;
SessionManager sessionManager;
    public static EnquiryFragment newInstance() {
        return new EnquiryFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_enquiry, container, false);

        sessionManager=new SessionManager(getActivity());
        edtName=view.findViewById(R.id.edtName);
        edtMobile=view.findViewById(R.id.edtMobile);
        edtRemark=view.findViewById(R.id.edtRemark);
        btnSubmit=view.findViewById(R.id.btnSubmit);
        progressDialog=new ProgressDialog(getActivity());

        progressDialog.setMessage("Please Wait...");

        edtName.setText(sessionManager.getStringData("name"));
        edtMobile.setText(sessionManager.getStringData("phone"));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String phone=edtMobile.getText().toString();
                String remark=edtRemark.getText().toString();

                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(remark)){
                    Toast.makeText(getActivity(), "Please Enter All Details Properly", Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.show();
                    addEnquiry(name,phone,remark);
                }
            }
        });


   return view;
    }

    private void addEnquiry(String name, String phone, String remark) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<DataModel> call = api.addEnquiry(name,phone,remark);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    DataModel resp =response.body();
                    if (resp != null){
                        if (resp.getRegistrationResult().getMessage().equals("Inserted Successfully")){
                            Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
                            dismiss();
                        }else {
                            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }progressDialog.dismiss();

                    }else {
                        Toast.makeText(getActivity(), "No User Found", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    progressDialog.dismiss();
                    System.out.println("Error : " + response.errorBody());
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(getActivity(), "Server Error 404", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(getActivity(), "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(getActivity(), "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }

                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });

    }
}