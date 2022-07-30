package com.example.kisan360;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.kisan360.Adapter.EnquiryAdapter;
import com.example.kisan360.Api.APIService;
import com.example.kisan360.Api.APIUrl;
import com.example.kisan360.Model.EnquiryList;
import com.example.kisan360.Model.EnquiryModel;
import com.example.kisan360.Utility.InternetConnection;
import com.example.kisan360.Utility.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminEnquiryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayout linearnoitem;
    SessionManager sessionManager;
    SwipeRefreshLayout mSwipeRefreshLayout;
    EnquiryAdapter enquiryAdapter;
    List<EnquiryModel> enquirylist = new ArrayList<EnquiryModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_enquiry);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_produxt_list);
        linearnoitem = (LinearLayout) findViewById(R.id.linearnoitem);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.mSwipeRefreshLayout);

        if (InternetConnection.isInternetAvailable(AdminEnquiryActivity.this)) {
            mSwipeRefreshLayout.setRefreshing(true);
            loadJSONProduct();
        } else {
            showSnack("Please check your Internet Connection.");
        }
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (InternetConnection.isInternetAvailable(AdminEnquiryActivity.this)) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    loadJSONProduct();
                } else {
                    showSnack("Please check your Internet Connection.");
                }
            }
        });
    }

    private void loadJSONProduct() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        APIService api = retrofit.create(APIService.class);
        Call<EnquiryList> call = api.getAllEnquiry();
        call.enqueue(new Callback<EnquiryList>() {
            @Override
            public void onResponse(Call<EnquiryList> call, Response<EnquiryList> response) {
                if (response.isSuccessful()) {
                    List<EnquiryModel> categoryItems = response.body().getEnquirylist();
                    recyclerView.removeAllViews();
                    enquirylist.clear();
                    System.out.println(categoryItems);
                    if (categoryItems != null && categoryItems.size() > 0) {
                        for (int i = 0;i < categoryItems.size();i++){
                            enquirylist.add(categoryItems.get(i));
                        }
                        linearnoitem.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        enquiryAdapter = new EnquiryAdapter(getApplicationContext(),enquirylist);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(enquiryAdapter);
                        System.out.println("ItemCount : "+enquiryAdapter.getItemCount());
                        mSwipeRefreshLayout.setRefreshing(false);
                    }else{
                        mSwipeRefreshLayout.setRefreshing(false);
                        linearnoitem.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        showSnack("No Data Found");
                    }
                }
                else {
                    // error case
                    linearnoitem.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    mSwipeRefreshLayout.setRefreshing(false);
                    showSnack("Failed to Retrive Data ");
                    System.out.println("Error : " + response.errorBody());
                    switch (response.code()) {
                        case 404:
                            showSnack("Server Error 404");
                            //Toast.makeText(LoginActivity.this, "not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            //Toast.makeText(LoginActivity.this, "Error 500", Toast.LENGTH_SHORT).show();
                            showSnack("server broken");
                            break;
                        default:
                            showSnack("unknown error");
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<EnquiryList> call, Throwable t) {

            }
        });
    }

    public void showSnack(String message){
        TSnackbar snackbar = TSnackbar.make(findViewById(android.R.id.content), message, TSnackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }
}