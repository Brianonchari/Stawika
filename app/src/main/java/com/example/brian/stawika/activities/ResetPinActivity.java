package com.example.brian.stawika.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.brian.stawika.R;

public class ResetPinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pin);

    }
    public void onresetSuccess(View view ){
        Intent i = new Intent(ResetPinActivity.this, PasswordSentActivity.class);
        startActivity(i);

    }
    public void signUp(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

}
