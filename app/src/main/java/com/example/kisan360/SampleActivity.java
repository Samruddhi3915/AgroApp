package com.example.kisan360;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class SampleActivity extends AppCompatActivity {
WebView webview;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);


        Toast.makeText(this, "Please wait....", Toast.LENGTH_LONG).show();

        webview=findViewById(R.id.webview);
        webview.loadUrl("https://ayaminteractive.com/farmers_app/Android/dashboard.php");


    }
}