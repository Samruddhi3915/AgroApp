package com.example.kisan360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

public class AdminLoginActivity extends AppCompatActivity {
Button btnLogin;
EditText edt_username,edt_password;
    MaterialDialog process_dialog;
    MaterialDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        builder = new MaterialDialog.Builder(AdminLoginActivity.this)
                .content("Please wait...")
                .progress(true, 0);

        btnLogin=findViewById(R.id.btnLogin);
        edt_username=findViewById(R.id.edt_username);
        edt_password=findViewById(R.id.edt_password);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edt_username.getText().toString();
                String password=edt_password.getText().toString();

                if (username.equals("Admin")&& password.equals("admin")){
                    process_dialog = builder.build();
                    process_dialog.show();
                    goToHome();
                }else {
                    Toast.makeText(AdminLoginActivity.this, "Please enter valid details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goToHome() {
        Intent intent=new Intent(getApplicationContext(),AdminHomeActivity.class);
        process_dialog.dismiss();
        startActivity(intent);
        finish();
    }
}