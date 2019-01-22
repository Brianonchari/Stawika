package com.example.brian.stawika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.brian.stawika.Api.Api;
import com.example.brian.stawika.Api.ApiClient;
import com.example.brian.stawika.Model.UsersResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUp extends AppCompatActivity {
    public static final String BASE_URL = "https://api-test.stawika.com";
    private final String API_KEY = "/api/register/register-user";
    private EditText edit;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        button = findViewById(R.id.btn_sign_up);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }

    private void createUser() {
        Retrofit retrofit = ApiClient.getClient();
        Api api = ApiClient.getClient().create(Api.class);
        Call<UsersResponse> call = api.createuser(API_KEY);

        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {

            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });
    }

    public void signUp(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void launchCode(View view ){
        Intent intent = new Intent(this,enterCode.class);
        startActivity(intent);
    }
    public void signIn(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
