package com.example.kisan360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.kisan360.Utility.SessionManager;

public class CategoryActivity extends AppCompatActivity {
LinearLayout fruits,vegetables,grains,seeds,vehicles,animals;
RelativeLayout parent;
SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        sessionManager=new SessionManager(this);
        parent=findViewById(R.id.parent);
        fruits=findViewById(R.id.fruits);
        vegetables=findViewById(R.id.vegetables);
        grains=findViewById(R.id.grains);
        seeds=findViewById(R.id.seeds);
        vehicles=findViewById(R.id.vehicles);
        animals=findViewById(R.id.animals);


        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ListProductsActivity.class);
                intent.putExtra("category","fruits");
                startActivity(intent);
            }
        });
        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ListProductsActivity.class);
                intent.putExtra("category","vegetables");
                startActivity(intent);
            }
        });
        grains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ListProductsActivity.class);
                intent.putExtra("category","grains");
                startActivity(intent);
            }
        });
        seeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ListProductsActivity.class);
                intent.putExtra("category","seeds");
                startActivity(intent);
            }
        });
        vehicles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ListProductsActivity.class);
                 intent.putExtra("category","vehicles");
                 startActivity(intent);
            }
        });
        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ListProductsActivity.class);
                intent.putExtra("category","animals");
                startActivity(intent);
            }
        });
    }
}