package com.example.brian.stawika.activities;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.brian.stawika.R;

import retrofit2.Retrofit;


public class LoginActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    private Button mButton;
    private static Retrofit retrofit = null;
    private EditText edit;

//    private final String API_KEY = "/api/register/register-user";
//    public static final String BASE_URL = "https://api-test.stawika.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        Log.d("LoginActivity", "");

        edit = findViewById(R.id.phone);
        edit.requestFocus();
        edit.setSelection(edit.length());

    }

    public void launchProfile(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    public void signUp(View view) {
        Intent signup = new Intent(this, SignUpActivity.class);
        startActivity(signup);
    }


    public void launchCode(View view) {
        Intent code = new Intent(LoginActivity.this, EnterCodeActivity.class);
        startActivity(code);
    }

    public void resetPin(View view) {
        Intent i = new Intent(LoginActivity.this, ResetPinActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    public void profile(View view) {
    }
}
