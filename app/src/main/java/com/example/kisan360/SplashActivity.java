package com.example.kisan360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.kisan360.Utility.SessionManager;

public class SplashActivity extends AppCompatActivity {
SessionManager sessionManager;
String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sessionManager=new SessionManager(this);
        role=sessionManager.getStringData("role");

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
              if (role.equals("")){
                  startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                  Toast.makeText(SplashActivity.this, "Register Now", Toast.LENGTH_SHORT).show();
                  finish();
              }else if (role.equals("Farmer")){
                  startActivity(new Intent(getApplicationContext(),MainActivity.class));
                  Toast.makeText(SplashActivity.this, "Welcome Farmer", Toast.LENGTH_SHORT).show();
                  finish();
              }else if (role.equals("Merchant")){
                  startActivity(new Intent(getApplicationContext(),CategoryActivity.class));
                  Toast.makeText(SplashActivity.this, "Welcome Merchant", Toast.LENGTH_SHORT).show();
                  finish();
              }else {
                  startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                  Toast.makeText(SplashActivity.this, "Register Now", Toast.LENGTH_SHORT).show();
                  finish();
              }
           }
       },4000);
    }
}