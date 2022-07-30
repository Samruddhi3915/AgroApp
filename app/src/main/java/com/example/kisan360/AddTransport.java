package com.example.kisan360;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.kisan360.Api.APIService;
import com.example.kisan360.Api.APIUrl;
import com.example.kisan360.Model.DataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class    AddTransport extends AppCompatActivity {
EditText edtName,edtMobileNumber,edtRate,edtVehicleNumber;
Button btnSubmit;
    MaterialDialog process_dialog;
    MaterialDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transport);

        edtName=findViewById(R.id.edtName);
        edtMobileNumber=findViewById(R.id.edtMobileNumber);
        edtRate=findViewById(R.id.edtRate);
        edtVehicleNumber=findViewById(R.id.edtVehicleNumber);
        btnSubmit=findViewById(R.id.btnSubmit);

        builder = new MaterialDialog.Builder(AddTransport.this)
                .content("Please wait...")
                .progress(true, 0);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edtName.getText().toString();
                String rate=edtRate.getText().toString();
                String vehicle_no=edtVehicleNumber.getText().toString();
                String phone=edtMobileNumber.getText().toString();

                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(rate)||TextUtils.isEmpty(vehicle_no)||TextUtils.isEmpty(phone)){
                    Toast.makeText(AddTransport.this, "please enter all details properly", Toast.LENGTH_SHORT).show();
                }else {
                    process_dialog = builder.build();
                    process_dialog.show();
                    addTransport(name,rate,vehicle_no,phone);
                }
            }
        });



    }

    private void addTransport(String name, String rate, String vehicle_no, String phone) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<DataModel> call = api.addTransportApi(name, rate, vehicle_no, phone);
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