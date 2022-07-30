package com.example.kisan360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kisan360.Api.APIService;
import com.example.kisan360.Api.APIUrl;
import com.example.kisan360.Model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminProductDetailsActivity extends AppCompatActivity {
    String pk_id,p_name,image,price,desc,phone,address,s_name;
    ImageView imageview;
    TextView tv_name,tv_price,tv_desc,tv_seller_name,tv_seller_phone,tv_seller_address;
    Button btn_buy;
    String status="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_details);

        Intent intent=getIntent();
        pk_id=intent.getExtras().getString("pk_id");
        p_name=intent.getExtras().getString("p_name");
        image=intent.getExtras().getString("image");
        price=intent.getExtras().getString("price");
        desc=intent.getExtras().getString("desc");
        phone=intent.getExtras().getString("phone");
        address=intent.getExtras().getString("address");
        s_name=intent.getExtras().getString("s_name");


        imageview=findViewById(R.id.imageview);
        tv_name=findViewById(R.id.tv_name);
        tv_price=findViewById(R.id.tv_price);
        tv_desc=findViewById(R.id.tv_desc);
        tv_seller_name=findViewById(R.id.tv_seller_name);
        tv_seller_phone=findViewById(R.id.tv_seller_phone);
        tv_seller_address=findViewById(R.id.tv_seller_address);
        btn_buy=findViewById(R.id.btn_buy);


        tv_name.setText(p_name);
        tv_price.setText(price);
        tv_desc.setText(desc);
        tv_seller_name.setText(s_name);
        tv_seller_phone.setText(phone);
        tv_seller_address.setText(address);


        Glide.with(AdminProductDetailsActivity.this)
                .load(image)
                .into(imageview);


        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProductStatus(pk_id);
            }
        });



    }
    private void updateProductStatus(String pk_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        APIService api = retrofit.create(APIService.class);

        Call<Result> call = api.changevaccineStatus(pk_id);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()){
                    Result resp = response.body();
                    if (resp != null) {
                        if (resp.getMessage().equals("success")) {
                            finish();
                            Toast.makeText(AdminProductDetailsActivity.this, "Success...", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(AdminProductDetailsActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AdminProductDetailsActivity.this, "No User Found", Toast.LENGTH_SHORT).show();

                    }
                }
                else {
                    // error case
                    System.out.println("Error : " + response.errorBody());
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(AdminProductDetailsActivity.this, "server error 404", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(AdminProductDetailsActivity.this, "server broken", Toast.LENGTH_SHORT).show();

                            break;
                        default:
                            Toast.makeText(AdminProductDetailsActivity.this, "Unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }


            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("MY TAG","failed");
                Toast.makeText(AdminProductDetailsActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}