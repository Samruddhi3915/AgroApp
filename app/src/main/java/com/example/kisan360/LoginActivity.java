package com.example.kisan360;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.kisan360.Api.APIService;
import com.example.kisan360.Api.APIUrl;
import com.example.kisan360.Model.UserModel;
import com.example.kisan360.Utility.Constants;
import com.example.kisan360.Utility.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
Button sendButton;
EditText phoneText;
ProgressDialog progressDialog;
String role;
Spinner spinner_role;
SessionManager mSessionManager;
TextView btnReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        progressDialog=new ProgressDialog(this);
        btnReg=findViewById(R.id.btnReg);
        spinner_role=findViewById(R.id.spinner_role);
        sendButton=findViewById(R.id.sendButton);
        phoneText=findViewById(R.id.phoneText);
        progressDialog.setMessage("Please Wait...");
        mSessionManager=new SessionManager(this);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));

            }
        });

        spinner_role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                role=parent.getSelectedItem().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=phoneText.getText().toString();
                String role=spinner_role.getSelectedItem().toString();

                if (phone.equals("")||phone.length()!=10){
                    Toast.makeText(LoginActivity.this, "Invalid phone !", Toast.LENGTH_SHORT).show();
                }else if (role.equals("Select Your Role")){
                    Toast.makeText(LoginActivity.this, "please Select Your Role", Toast.LENGTH_SHORT).show();

                }else {
                    progressDialog.show();
                    loginProcess(phone,role);
                }
            }
        });

    }

    private void loginProcess(String phone, final String role) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        APIService api = retrofit.create(APIService.class);
        Call<UserModel> call = api.transportLogin(phone,role);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {

                    UserModel resp = response.body();
                    if (resp != null) {
                        if (resp.getMessage().equals("success")) {
                            showToast("Login Success..");
                            mSessionManager.putStringData(Constants.KEY_PKID, resp.getPk_id());
                            mSessionManager.putStringData(Constants.KEY_NAME, resp.getName());
                            mSessionManager.putStringData(Constants.KEY_EMAIL, resp.getEmail());
                            mSessionManager.putStringData(Constants.KEY_PHONE, resp.getPhone());
                            mSessionManager.putStringData(Constants.KEY_ADDRESS, resp.getAddress());
                            if (role.equalsIgnoreCase("Farmer")){
                                mSessionManager.putStringData("role","Farmer");
                            }else if (role.equalsIgnoreCase("Merchant")){
                                mSessionManager.putStringData("role","Merchant");

                            }
                            goTOHome();

                        } else {
                            showSnack("Something went wrong. please check details");

                        }
                    }else{
                        showSnack("No user Found");
                    }
                }
                else {
                    // error case
                    System.out.println("Error : "+response.errorBody());
                    switch (response.code()) {
                        case 404:
                            showSnack("Server Error 404");
                            break;
                        case 500:
                            showSnack("server broken");
                            break;
                        default:
                            showSnack("unknown error");
                            break;
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }

    private void goTOHome() {
        if (role.equalsIgnoreCase("Farmer")){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }else if (role.equalsIgnoreCase("Merchant")){
            //Intent intent=new Intent(getApplicationContext(),CategoryActivity.class);
            Intent intent=new Intent(getApplicationContext(),Merchant.class);
            startActivity(intent);
            finish();

        }
    }

    public void showSnack(String message){
        TSnackbar snackbar = TSnackbar.make(findViewById(android.R.id.content), message, TSnackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public void showToast(String message){
        Toast toast = Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}