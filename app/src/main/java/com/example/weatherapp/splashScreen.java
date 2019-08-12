package com.example.weatherapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler mhandler = new Handler();

        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }

}
