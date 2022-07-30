package com.example.kisan360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProductEnquiryActivity extends AppCompatActivity {
String p_name,image,price,desc,phone,address,s_name,min_quantity,total_quantity;
ImageView imageview;
TextView tv_min_qty,tv_total_qty,tv_name,tv_price,tv_desc,tv_seller_name,tv_seller_phone,tv_seller_address;
Button btn_enq,btn_buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_enquiry);


        Intent intent=getIntent();
        p_name=intent.getExtras().getString("p_name");
        image=intent.getExtras().getString("image");
        price=intent.getExtras().getString("price");
        desc=intent.getExtras().getString("desc");
        phone=intent.getExtras().getString("phone");
        address=intent.getExtras().getString("address");
        s_name=intent.getExtras().getString("s_name");
        min_quantity=intent.getExtras().getString("min_quantity");
        total_quantity=intent.getExtras().getString("total_quantity");


        imageview=findViewById(R.id.imageview);
        tv_name=findViewById(R.id.tv_name);
        tv_price=findViewById(R.id.tv_price);
        tv_desc=findViewById(R.id.tv_desc);
        tv_seller_name=findViewById(R.id.tv_seller_name);
        tv_seller_phone=findViewById(R.id.tv_seller_phone);
        tv_seller_address=findViewById(R.id.tv_seller_address);
        btn_enq=findViewById(R.id.btn_enq);
        btn_buy=findViewById(R.id.btn_buy);
        tv_min_qty=findViewById(R.id.tv_min_qty);
        tv_total_qty=findViewById(R.id.tv_total_qty);

        tv_name.setText(p_name);
        tv_price.setText(price);
        tv_desc.setText(desc);
        tv_seller_name.setText(s_name);
        tv_seller_phone.setText(phone);
        tv_seller_address.setText(address);
        tv_min_qty.setText(min_quantity);
        tv_total_qty.setText(total_quantity);

        Glide.with(ProductEnquiryActivity.this)
                .load(image)
                .into(imageview);

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),BuyActivity.class);
                intent.putExtra("p_name",p_name);
                intent.putExtra("s_name",s_name);
                intent.putExtra("price",price);
                startActivity(intent);
            }
        });

        btn_enq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:'"+phone+"'"));
                startActivity(callIntent);
            }
        });

    }
}