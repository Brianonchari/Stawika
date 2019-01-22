package com.example.brian.stawika;


import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brian.stawika.Api.Api;
import com.example.brian.stawika.Api.ApiClient;
import com.example.brian.stawika.Model.Users;
import com.example.brian.stawika.Model.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;


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


        Api api = ApiClient.getClient().create(Api.class);
        Call<UsersResponse> call = api.createuser(API_KEY);
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
//                List<Users> users = response.body().getUsers();

            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });


    }

    public void launchProfile(View view) {
        Intent intent = new Intent(this,Profile.class);
        startActivity(intent);
    }

    public void signUp(View view) {
        Intent signup = new Intent(this, SignUp.class);
        startActivity(signup);
    }


    public void launchCode(View view) {
        Intent code = new Intent(LoginActivity.this,enterCode.class);
        startActivity(code);
    }

    public void resetPin(View view) {
        Intent i = new Intent(LoginActivity.this,ResetPin.class);
        startActivity(i);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
