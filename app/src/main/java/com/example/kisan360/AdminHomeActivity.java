package com.example.kisan360;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AdminHomeActivity extends AppCompatActivity {
CardView user_enquiry,add_transport,products_app,view_enquiry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Toast.makeText(this, "Welcome Admin", Toast.LENGTH_SHORT).show();

        products_app=findViewById(R.id.products_app);
        add_transport=findViewById(R.id.add_transport);
        view_enquiry=findViewById(R.id.view_enquiry);
        user_enquiry=findViewById(R.id.user_enquiry);

        products_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminApproveProduct.class);
                startActivity(intent);
            }
        });
        add_transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddTransport.class);
                startActivity(intent);
            }
        });
        view_enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminEnquiryActivity.class);
                startActivity(intent);
            }
        });
        user_enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserEnquiryActivity.class);
                startActivity(intent);
            }
        });

    }
}