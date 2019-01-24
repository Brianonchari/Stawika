package com.example.brian.stawika.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.brian.stawika.R;
import com.example.brian.stawika.activities.SignUpActivity;

public class PasswordSentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_sent);
    }
    public void signUp(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
