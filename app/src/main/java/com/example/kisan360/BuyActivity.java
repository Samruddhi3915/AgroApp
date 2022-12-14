package com.example.kisan360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.kisan360.Api.APIService;
import com.example.kisan360.Api.APIUrl;
import com.example.kisan360.Model.DataModel;
import com.example.kisan360.Utility.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuyActivity extends AppCompatActivity {
EditText edt_bid,edt_quantity,edt_p_name,edt_s_name,edt_price,edt_name,edt_phone,edt_address;
String p_name,s_name,price;
Button btn_reg;
    MaterialDialog process_dialog;
    MaterialDialog.Builder builder;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);


        Intent intent=getIntent();
        p_name=intent.getExtras().getString("p_name");
        s_name=intent.getExtras().getString("s_name");
        price=intent.getExtras().getString("price");

        sessionManager=new SessionManager(this);
        builder = new MaterialDialog.Builder(BuyActivity.this)
                .content("Please wait...")
                .progress(true, 0);



        //edt_bid=findViewById(R.id.edt_bid);
        edt_quantity=findViewById(R.id.edt_quantity);
        edt_p_name=findViewById(R.id.edt_p_name);
        edt_s_name=findViewById(R.id.edt_s_name);
        edt_price=findViewById(R.id.edt_price);
        edt_name=findViewById(R.id.edt_name);
        edt_phone=findViewById(R.id.edt_phone);
        edt_address=findViewById(R.id.edt_address);
        btn_reg=findViewById(R.id.btn_reg);

        edt_name.setText(sessionManager.getStringData("name"));
        edt_phone.setText(sessionManager.getStringData("phone"));
        edt_address.setText(sessionManager.getStringData("address"));


        edt_p_name.setText(p_name);
        edt_price.setText(price);
        edt_s_name.setText(s_name);


        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product_name=edt_p_name.getText().toString();
                String product_price=edt_price.getText().toString();
                String seller_name=edt_s_name.getText().toString();
                String name=edt_name.getText().toString();
                String phone=edt_phone.getText().toString();
                String address=edt_address.getText().toString();
                String quantity=edt_quantity.getText().toString();
                //String bid_price=edt_bid.getText().toString();


                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(address)||TextUtils.isEmpty(quantity)){
                    //||TextUtils.isEmpty(bid_price)
                    Toast.makeText(BuyActivity.this, "please fill all details Properly", Toast.LENGTH_SHORT).show();
                }else {
                    process_dialog = builder.build();
                    process_dialog.show();
                    addEnquiry(product_name,product_price,seller_name,name,phone,address,quantity);//,bid_price
                }
            }
        });




    }

    private void addEnquiry(String product_name, String product_price, String seller_name, final String name, String phone, String address,final String quantity) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        APIService api = retrofit.create(APIService.class);
        Call<DataModel> call = api.buyApi(product_name, product_price, seller_name, name, phone, address,quantity);//,bid_price
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()){
                    process_dialog.dismiss();
                    DataModel resp =response.body();
                    if (resp != null){
                        if (resp.getRegistrationResult().getMessage().equals("Inserted Successfully")){
                            Intent intent=new Intent(getApplicationContext(),BillActivity.class);
                            intent.putExtra("p_name",p_name);
                            intent.putExtra("s_name",s_name);
                            intent.putExtra("price",price);
                            intent.putExtra("name",name);
                            intent.putExtra("quantity",quantity);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Payment Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }process_dialog.dismiss();

                    }else {
                        Toast.makeText(getApplicationContext(), "No User Found", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    process_dialog.dismiss();
                    System.out.println("Error : " + response.errorBody());
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(getApplicationContext(), "Server Error 404", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(getApplicationContext(), "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }

                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });
    }
}