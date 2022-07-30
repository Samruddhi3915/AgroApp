package com.example.kisan360;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class BillActivity extends AppCompatActivity {

String p_name,s_name,name,price,quantity;
TextView tv_name,tv_quanity,tv_total,tv_date,tv_product,tv_price;
Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        Intent intent=getIntent();
        p_name=intent.getExtras().getString("p_name");
        s_name=intent.getExtras().getString("s_name");
        name=intent.getExtras().getString("name");
        price=intent.getExtras().getString("price");
        quantity=intent.getExtras().getString("quantity");

        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());

        tv_name=findViewById(R.id.tv_name);
        tv_quanity=findViewById(R.id.tv_quanity);
        tv_total=findViewById(R.id.tv_total);
        tv_date=findViewById(R.id.tv_date);
        tv_product=findViewById(R.id.tv_product);
        tv_price=findViewById(R.id.tv_price);
        btnSubmit=findViewById(R.id.btnSubmit);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                alertbox.setMessage("Are you want to use our Transport Service ?");
                alertbox.setTitle("Transport Service");
                alertbox.setIcon(R.drawable.delivery_truck);
                alertbox.setPositiveButton("yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent=new Intent(getApplicationContext(),TransportActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                alertbox.show();
            }
        });

        tv_date.setText(currentDateTimeString);

        getTotal();



        tv_name.setText(name);
        tv_price.setText(price);
        tv_product.setText(p_name);
        tv_quanity.setText(quantity);

    }

    private void getTotal() {
        double a = Double.parseDouble(price);
        double b = Double.parseDouble(quantity);
        double c = a * b;
        tv_total.setText(String.valueOf(c));
    }
}