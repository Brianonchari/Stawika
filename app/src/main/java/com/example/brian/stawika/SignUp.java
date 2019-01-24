package com.example.brian.stawika;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.brian.stawika.Api.RestApiInterface;
import com.example.brian.stawika.Api.RestClient;
import com.example.brian.stawika.Model.request.AccountCheckRequest;
import com.example.brian.stawika.Model.response.AccountCheckResponse;
import com.example.brian.stawika.activities.EnterCodeActivity;
import com.example.brian.stawika.activities.LoginActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUp extends AppCompatActivity {

    private TextInputEditText phoneNumberEt, pinEt, confirmPinEt;
    private RestApiInterface apiService = RestClient.getClient().create(RestApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        phoneNumberEt = findViewById(R.id.phone);
        pinEt = findViewById(R.id.password);
        confirmPinEt = findViewById(R.id.confirmpassword);

        findViewById(R.id.btn_sign_up).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                String phone =  phoneNumberEt.getText().toString().trim();
                String password = pinEt.getText().toString().trim();
                String Confirmpassword =confirmPinEt.getText().toString().trim();

                if(phone.isEmpty()){
                    phoneNumberEt.setError("Phone is required");
                    phoneNumberEt.requestFocus();
                    return;
                }

                if(password.isEmpty()){
                    pinEt.setError("Enter pin");
                    pinEt.requestFocus();
                    return;
                }

                if (!password.equals(Confirmpassword)){
                    confirmPinEt.setError("Password Not Matching");
                    confirmPinEt.requestFocus();
                    return;

                }



                AccountCheckRequest accountCheckRequest = new AccountCheckRequest();
                accountCheckRequest.setPassword(pinEt.getText().toString());
                accountCheckRequest.setPhoneNumber(phoneNumberEt.getText().toString());

                Log.e("REQUEST", new Gson().toJson(accountCheckRequest));
                Call<AccountCheckResponse> call = apiService.createUser(accountCheckRequest);
                call.enqueue(new Callback<AccountCheckResponse>() {

                    @Override
                    public void onResponse(Call<AccountCheckResponse> call, Response<AccountCheckResponse> response) {

                        Log.e("RESPONSE", new Gson().toJson(response.body()));

                        if(response.isSuccessful()){
                            Intent intent = new Intent(SignUp.this, EnterCodeActivity.class);
                            startActivity(intent);

                        }

                    }

                    @Override
                    public void onFailure(Call<AccountCheckResponse> call, Throwable t) {

                    }
                });

            }
        });

    }


    private void createUser() {


    }

    public void signUp(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

   public void launchCode(View view ){
        Intent intent = new Intent(this, EnterCodeActivity.class);
        startActivity(intent);
    }
    public void signIn(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
