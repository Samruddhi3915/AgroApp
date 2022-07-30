package com.example.kisan360;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.kisan360.Api.APIService;
import com.example.kisan360.Api.APIUrl;
import com.example.kisan360.Model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedbackActivity extends AppCompatActivity {
    
    Button btnSubmit;
    EditText edtfeedback;
    TextView tvrating;
    RatingBar ratingBar;
    MaterialDialog process_dialog;
    MaterialDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        btnSubmit =findViewById(R.id.btnSubmit);
        edtfeedback =findViewById(R.id.edtfeedback);
        tvrating = findViewById(R.id.tvrating);
        ratingBar = findViewById(R.id.ratingBar);

        builder = new MaterialDialog.Builder(this)
                .content("Please wait...")
                .progress(true, 0);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tvrating.setText("" + rating);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }

            private void submitFeedback() {
                String description=edtfeedback.getText().toString().trim();
                String rating=tvrating.getText().toString().trim();

                if (TextUtils.isEmpty(description)){
                    edtfeedback.setError("Please give your feedback");
                    edtfeedback.requestFocus();
                    return;
                }else if (TextUtils.isEmpty(rating)){
                    tvrating.setError("please give rating");
                    tvrating.requestFocus();
                    return;
                }else {
                    process_dialog = builder.build();
                    process_dialog.show();
                    sumnission(description,rating);



                }

            }

            private void sumnission(String feedback, String rating) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(APIUrl.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIService api=retrofit.create(APIService.class);
                Call<Result> call=api.FeedbackApi(feedback,rating);
                call.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.isSuccessful()){
                            Result resp =response.body();
                            if (resp != null){
                                if (resp.getMessage().equals("data saved successfully")){
                                    process_dialog.dismiss();
                                    Toast.makeText(FeedbackActivity.this, "Thanks For Feedback", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    process_dialog.dismiss();
                                    Toast.makeText(FeedbackActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                }

                            }else {
                                process_dialog.dismiss();
                                Toast.makeText(FeedbackActivity.this, "No user Found", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            process_dialog.dismiss();
                            System.out.println("Error : " + response.errorBody());
                            switch (response.code()) {
                                case 404:
                                    Toast.makeText(FeedbackActivity.this, "Server Error 404", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(FeedbackActivity.this, "server broken", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(FeedbackActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.d("MY TAG","failed");
                        Toast.makeText(FeedbackActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

            }

        });
    }
}