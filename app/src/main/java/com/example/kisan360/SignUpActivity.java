package com.example.kisan360;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kisan360.Api.APIService;
import com.example.kisan360.Api.APIUrl;
import com.example.kisan360.Model.DataModel;
import com.example.kisan360.Utility.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
EditText edtName,edtPhone,edtEmail,edtAddress;
TextView btnSubmit;
ProgressDialog progressDialog;
String user;
SessionManager sessionManager;
Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        sessionManager=new SessionManager(this);
        btnSubmit=findViewById(R.id.btnSubmit);
        edtName=findViewById(R.id.edtName);
        edtPhone=findViewById(R.id.edtPhone);
        edtEmail=findViewById(R.id.edtEmail);
        edtAddress=findViewById(R.id.edtAddress);
        spinner=findViewById(R.id.spinner);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Waait...");


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getSelectedItem().toString().equals("Farmer")){
                    user="Farmer";
                }else if (parent.getSelectedItem().toString().equals("Merchant")){
                    user="Merchant";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edtName.getText().toString();
                String phone=edtPhone.getText().toString();
                String email=edtEmail.getText().toString();
                String address=edtAddress.getText().toString();
                String role=user;

                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(email)||TextUtils.isEmpty(address)){
                    Toast.makeText(SignUpActivity.this, "Please Enter all details properly", Toast.LENGTH_SHORT).show();
                }else if (role.equals("Select Your Role")){
                    Toast.makeText(SignUpActivity.this, "Please Select Role", Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.show();
                    addUser(name,phone,email,address,role);
                }
            }
        });
    }

    private void addUser(final String name, String phone, String email, String address, String role) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<DataModel> call=api.addUser(name, phone, email, address, role);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    DataModel resp =response.body();
                    if (resp != null){
                        if (resp.getRegistrationResult().getMessage().equals("Inserted Successfully")){
                            goToHome();
                        }else {
                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }progressDialog.dismiss();

                    }else {
                        Toast.makeText(getApplicationContext(), "No User Found", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    progressDialog.dismiss();
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

    private void goToHome() {
        if (user.equals("Farmer")){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }else if (user.equals("Merchant")){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}