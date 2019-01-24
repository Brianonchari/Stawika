package com.example.brian.stawika.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import com.example.brian.stawika.activities.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }





}
