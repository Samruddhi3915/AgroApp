package com.example.kisan360;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.kisan360.Utility.SessionManager;
public class Merchant extends AppCompatActivity {
    LinearLayout fruits,vegetables,grains,seeds,vehicles,animals,donation,feedback;
    RelativeLayout parent;
    ImageView iv_logout;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merchant);

        sessionManager=new SessionManager(this);
        parent=findViewById(R.id.parent);
        fruits=findViewById(R.id.fruits);
        vegetables=findViewById(R.id.vegetables);
        grains=findViewById(R.id.grains);
        seeds=findViewById(R.id.seeds);
        vehicles=findViewById(R.id.vehicles);
        animals=findViewById(R.id.animals);
        feedback=findViewById(R.id.fback);
        donation=findViewById(R.id.d);
        iv_logout=findViewById(R.id.iv_logout);


        iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                sessionManager.removeData("role");
                Toast.makeText(Merchant.this, "Success...", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });


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
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),FeedbackActivity.class);
                startActivity(i);
            }
        });
        donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),DonationActivity.class);
                startActivity(intent);
            }
        });
    }
}