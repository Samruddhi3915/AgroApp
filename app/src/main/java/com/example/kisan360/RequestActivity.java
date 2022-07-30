package com.example.kisan360;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.kisan360.Adapter.MyAdapter;
import com.example.kisan360.Api.APIService;
import com.example.kisan360.Api.APIUrl;
import com.example.kisan360.Utility.InternetConnection;
import com.example.kisan360.Utility.SessionManager;
import com.fxn.pix.Pix;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestActivity extends AppCompatActivity {
EditText edttitle,edtdesc,edtSellerName,edtaddress,edtSellerPhone;
RecyclerView recyclerView;
Spinner spinner_category;
Button btnsend;
    CircleImageView updateImageFragmentProfile;
    SessionManager mSessionManager;
    ProgressDialog progressDialog;
    private static final int REQUEST_CODE = 100;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 124;
    private ArrayList<Uri> arrayList = new ArrayList<>();
    ArrayList<String> returnValue;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);


        progressDialog = new ProgressDialog(this);
        mSessionManager = new SessionManager(this);
        edtdesc = findViewById(R.id.edtdesc);
        spinner_category = findViewById(R.id.spinner_category);
        edttitle = findViewById(R.id.edttitle);
        edtSellerName = findViewById(R.id.edtSellerName);
        edtaddress = findViewById(R.id.edtaddress);
        edtSellerPhone = findViewById(R.id.edtSellerPhone);
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(RequestActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        myAdapter = new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);
        updateImageFragmentProfile = findViewById(R.id.updateImageFragmentProfile);
        btnsend = findViewById(R.id.btnsend);


        updateImageFragmentProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the file chooser dialo
                if (askForPermission())
                    Pix.start(RequestActivity.this,                    //Activity or Fragment Instance
                            REQUEST_CODE);    //Number of images to restict selection count
            }
        });


        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product_name = edttitle.getText().toString().trim();
                String description = edtdesc.getText().toString().trim();
                String category = spinner_category.getSelectedItem().toString().trim();
                String seller_name = edtSellerName.getText().toString().trim();
                String seller_address = edtaddress.getText().toString().trim();
                String seller_phone = edtSellerPhone.getText().toString().trim();
                String status = "1";

                if (!arrayList.isEmpty() && arrayList != null && arrayList.size() != 0){
                    if (!TextUtils.isEmpty(product_name) || !TextUtils.isEmpty(description)|| category.equals("Select Category")|| !TextUtils.isEmpty(seller_name)|| !TextUtils.isEmpty(seller_address)|| !TextUtils.isEmpty(seller_phone)){
                        if (InternetConnection.isInternetAvailable(RequestActivity.this)) {
                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                            progressDialog.setMessage("Please wait...");
                            progressDialog.show();
                            AddNewProduct(product_name,description,category,seller_name,seller_address,seller_phone,status);
                        } else {
                            showToast("Please check your Internet Connection.");
                        }
                    }else{
                        showToast("Please enter details properly");
                    }
                }else{
                    showToast("Please select images to upload");
                }


            }
        });

    }

    private void AddNewProduct(String product_name, String description, String category, String seller_name, String seller_address, String seller_phone, String status) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        builder.addFormDataPart("product_name", product_name);
        builder.addFormDataPart("description", description);
        builder.addFormDataPart("category", category);
        builder.addFormDataPart("seller_name", seller_name);
        builder.addFormDataPart("seller_address", seller_address);
        builder.addFormDataPart("seller_phone", seller_phone);
        builder.addFormDataPart("status", status);


        // Map is used to multipart the file using okhttp3.RequestBody
        // Multiple Images
        for (int i = 0; i < arrayList.size(); i++) {
            File file = new File(arrayList.get(i).toString());
            builder.addFormDataPart("file[]", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        }

        //The gson builder
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //creating our api
        APIService api = retrofit.create(APIService.class);

        MultipartBody requestBody = builder.build();
        Call<ResponseBody> call = api.addNewAchivement(requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                showToast("Success " + response.message());
                showToast("Success " + response.body().toString());
                finish();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("TAG", "Error " + t.getMessage());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.e("val", "requestCode ->  " + requestCode+"  resultCode "+resultCode);
        switch (requestCode) {
            case (100): {
                arrayList.clear();
                if (resultCode == Activity.RESULT_OK) {
                    returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
                    myAdapter.addImage(returnValue);
                    for (String s : returnValue) {
                        Uri imageUri = Uri.parse(s);
                        arrayList.add(imageUri);
                        Log.e("val", " ->  " + s);
                    }
                    showToast("Array :"+arrayList);
                }
            }
            break;
        }
    }
    private boolean askForPermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            int hasCallPermission = ContextCompat.checkSelfPermission(RequestActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            if (hasCallPermission != PackageManager.PERMISSION_GRANTED) {
                // Ask for permission
                // need to request permission
                if (ActivityCompat.shouldShowRequestPermissionRationale(RequestActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // explain
                    showMessageOKCancel(
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ActivityCompat.requestPermissions(RequestActivity.this,
                                            new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                                            REQUEST_CODE_ASK_PERMISSIONS);
                                }
                            });
                    // if denied then working here
                } else {
                    // Request for permission
                    ActivityCompat.requestPermissions(RequestActivity.this,
                            new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_ASK_PERMISSIONS);
                }

                return false;
            } else {
                // permission granted and calling function working
                return true;
            }
        } else {
            return true;
        }
    }
    private void showMessageOKCancel(DialogInterface.OnClickListener okListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(RequestActivity.this);
        final AlertDialog dialog = builder.setMessage("You need to grant access to Read External Storage")
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                        ContextCompat.getColor(RequestActivity.this, android.R.color.holo_blue_light));
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                        ContextCompat.getColor(RequestActivity.this, android.R.color.holo_red_light));
            }
        });

        dialog.show();

    }
    //Method to upload image to server
    public void showSnack(String message){
        TSnackbar snackbar = TSnackbar.make(findViewById(android.R.id.content), message, TSnackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }
    public void showToast(String message){
        Toast toast = Toast.makeText(this,message,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

}