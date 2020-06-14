package com.example.corona.view.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.corona.R;

public class WelcomePage extends AppCompatActivity {


    private static int TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        TextView textView = findViewById(R.id.corona);
        TextView textViewcvd=findViewById(R.id.corona2);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSelected(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcomeIntent = new Intent(WelcomePage.this, Login.class);
                startActivity(welcomeIntent);
                finish();
            }
        }, TIME_OUT);
    }

}
