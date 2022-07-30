package com.example.kisan360;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.kisan360.Api.APIService;
import com.example.kisan360.Api.APIUrl;
import com.example.kisan360.Model.DataModel;
import com.example.kisan360.Utility.Constants;
import com.example.kisan360.Utility.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DonationActivity extends AppCompatActivity {

    AppCompatButton btnSubmit;
    EditText edt_name,edt_address,edt_phone,edt_amount,edt_desc;
    MaterialDialog process_dialog;
    MaterialDialog.Builder builder;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        sessionManager=new SessionManager(this);
        edt_name=findViewById(R.id.edt_name);
        edt_address=findViewById(R.id.edt_address);
        edt_phone=findViewById(R.id.edt_phone);
        edt_amount=findViewById(R.id.edt_amount);
        edt_desc=findViewById(R.id.edt_desc);
        btnSubmit=findViewById(R.id.btnSubmit);

        builder = new MaterialDialog.Builder(DonationActivity.this)
                .content("Please wait...")
                .progress(true, 0);


        edt_name.setText(sessionManager.getStringData(Constants.KEY_NAME));
        edt_address.setText(sessionManager.getStringData(Constants.KEY_ADDRESS));
        edt_phone.setText(sessionManager.getStringData(Constants.KEY_PHONE));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edt_name.getText().toString();
                String address=edt_address.getText().toString();
                String phone=edt_phone.getText().toString();
                String amount=edt_amount.getText().toString();
                String description=edt_desc.getText().toString();

                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(address)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(amount)||TextUtils.isEmpty(description)){
                    Toast.makeText(DonationActivity.this, "please fill all details properly", Toast.LENGTH_SHORT).show();
                }else {
                    process_dialog = builder.build();
                    process_dialog.show();
                    submitDonation(name,address,phone,amount,description);
                }
            }
        });

    }

    private void submitDonation(String name, String address, String phone, String amount, String description) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<DataModel> call = api.donationApi(name, address, phone, amount, description);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()){
                    process_dialog.dismiss();
                    DataModel resp =response.body();
                    if (resp != null){
                        if (resp.getRegistrationResult().getMessage().equals("Inserted Successfully")){
                            Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }process_dialog.dismiss();

                    }else {
                        Toast.makeText(getApplicationContext(), "No User Found", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    process_dialog.dismiss();
                    System.out.println("Error : " + response.errorBody());
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(getApplicationContext(), "Server Error 404", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(getApplicationContext(), "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "unknown error", Toast.LENGTH_SHORT).show();
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