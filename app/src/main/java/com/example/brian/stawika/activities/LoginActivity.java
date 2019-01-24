package com.example.brian.stawika.activities;


import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.example.brian.stawika.Api.RestApiInterface;
import com.example.brian.stawika.Api.RestClient;
import com.example.brian.stawika.Profile;
import com.example.brian.stawika.R;
import com.example.brian.stawika.ResetPin;
import com.example.brian.stawika.SignUp;

import retrofit2.Retrofit;


public class LoginActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    private Button mButton;
    private static Retrofit retrofit = null;
    private EditText edit;

    private final String API_KEY = "/api/register/register-user" ;
    public static final String BASE_URL = "https://api-test.stawika.com";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        Log.d("LoginActivity","");

//        set cursor position
        edit = findViewById(R.id.phone);
        edit.requestFocus();
        edit.setSelection(edit.length());


        RestApiInterface api = RestClient.getClient().create(RestApiInterface.class);
     ;


    }

    public void launchProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void signUp(View view) {
        Intent signup = new Intent(this, SignUp.class);
        startActivity(signup);
    }


    public void launchCode(View view) {
        Intent code = new Intent(LoginActivity.this, EnterCodeActivity.class);
        startActivity(code);
    }

    public void resetPin(View view) {
        Intent i = new Intent(LoginActivity.this, ResetPin.class);
        startActivity(i);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
