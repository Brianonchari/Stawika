package com.example.brian.stawika.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.brian.stawika.R;
import com.example.brian.stawika.api.RestApiInterface;
import com.example.brian.stawika.api.RestClient;
import com.example.brian.stawika.model.request.AccountCheckRequest;
import com.example.brian.stawika.model.response.AccountCheckResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText phoneNumberEt, pinEt, confirmPinEt;
    ProgressDialog progress;
    private RestApiInterface apiService = RestClient.getClient().create(RestApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        phoneNumberEt = findViewById(R.id.phone);
        pinEt = findViewById(R.id.password);
        confirmPinEt = findViewById(R.id.confirmpassword);

        //setting cursor position
        phoneNumberEt.requestFocus();
        phoneNumberEt.setSelection(phoneNumberEt.length());

        findViewById(R.id.btn_sign_up).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                String phone = phoneNumberEt.getText().toString().trim();
                String password = pinEt.getText().toString().trim();
                String Confirmpassword = confirmPinEt.getText().toString().trim();

                if (phone.isEmpty() && phone.length() != 12) {
                    phoneNumberEt.setError("Enter a valid phone number");
                    phoneNumberEt.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    pinEt.setError("Enter pin");
                    pinEt.requestFocus();
                    return;
                }

                if (!password.equals(Confirmpassword)) {
                    confirmPinEt.setError("Password Not Matching");
                    confirmPinEt.requestFocus();
                    return;

                }

                final AccountCheckRequest accountCheckRequest = new AccountCheckRequest();
                accountCheckRequest.setPassword(pinEt.getText().toString());
                accountCheckRequest.setPhoneNumber(phoneNumberEt.getText().toString());

                Log.e("REQUEST", new Gson().toJson(accountCheckRequest));
                Call<AccountCheckResponse> call = apiService.createUser(accountCheckRequest);
                call.enqueue(new Callback<AccountCheckResponse>() {

                    @Override
                    public void onResponse(Call<AccountCheckResponse> call, Response<AccountCheckResponse> response) {

                        if (response.isSuccessful()) {

                            Intent intent = new Intent(SignUpActivity.this, EnterCodeActivity.class);
                            intent.putExtra("token", response.body().getToken());
                            startActivity(intent);


                            progress = new ProgressDialog(SignUpActivity.this);
                            progress.setMessage("Sign Up");
                            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progress.setProgress(0);
                            progress.show();

                            final int totalProgressTime = 100;
                            final Thread t = new Thread() {
                                @Override
                                public void run() {
                                    int jumpTime = 0;

                                    while (jumpTime < totalProgressTime) {
                                        try {
                                            sleep(2000);
                                            jumpTime += 1;
                                            progress.setProgress(jumpTime);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                }
                            };
                            t.start();
                        }

                    }

                    @Override
                    public void onFailure(Call<AccountCheckResponse> call, Throwable t) {

                    }
                });

            }
        });
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void launchCode(View view) {
        Intent intent = new Intent(this, EnterCodeActivity.class);
        startActivity(intent);
    }

    public void signIn(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
