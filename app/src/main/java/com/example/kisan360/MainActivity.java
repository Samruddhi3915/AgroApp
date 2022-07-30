package com.example.kisan360;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kisan360.Utility.SessionManager;


public class MainActivity extends AppCompatActivity {

    TextView scroll_text,tv_app_name;
    LinearLayout contact,enquiry,products,market,wether,feedback,expenses,donation,btn_transport;
    Button btn_reg;
    ImageView iv_logout;
    SessionManager sessionManager;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


        sessionManager=new SessionManager(this);
        contact=findViewById(R.id.contact);
        enquiry=findViewById(R.id.enquiry);
        products=findViewById(R.id.products);
        scroll_text=findViewById(R.id.scrollable_text);
        market=findViewById(R.id.market);
        wether=findViewById(R.id.wether);
        feedback=findViewById(R.id.feedback);
        btn_reg=findViewById(R.id.btn_reg);
        expenses=findViewById(R.id.expenses);
        donation=findViewById(R.id.donation);
        btn_transport=findViewById(R.id.btn_transport);
        tv_app_name=findViewById(R.id.tv_app_name);
        iv_logout=findViewById(R.id.iv_logout);

        scroll_text.setSelected(true);


        iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                sessionManager.removeData("role");
                Toast.makeText(MainActivity.this, "Success...", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });



        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ContactListActivity.class);
                startActivity(intent);
            }
        });

        tv_app_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdminLoginActivity.class);
                startActivity(intent);
            }
        });
        btn_transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),TransportActivity.class);
                startActivity(intent);
            }
        });
         donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),DonationActivity.class);
                startActivity(intent);
            }
        });
        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CategoryActivity.class);
                startActivity(intent);
            }
        });
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://farmersportal.mycloudspace.in/Android/dashboard.php";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
       wether.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.accuweather.com/en/in/mh/maharashtra-weather";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://rates.goldenchennai.com/vegetable-price/nashik-vegetable-price-today/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),FeedbackActivity.class);
                startActivity(i);
            }
        });
        expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),GovernementSchemeActivity.class);
                startActivity(i);
            }
        });


        enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnquiryFragment addPhotoBottomDialogFragment =
                        EnquiryFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("from", "transport");
                addPhotoBottomDialogFragment.setArguments(bundle);
                addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                        "add_leave_dialog_fragment");
            }
        });



    }
}