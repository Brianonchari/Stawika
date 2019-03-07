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

                progress = new ProgressDialog(SignUpActivity.this);
                progress.setTitle("Please Wait");
                progress.setMessage("Sign Up");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setProgress(0);
                progress.show();

                final AccountCheckRequest accountCheckRequest = new AccountCheckRequest();
                accountCheckRequest.setPassword(pinEt.getText().toString());
                accountCheckRequest.setPhoneNumber(phoneNumberEt.getText().toString());

                Log.e("REQUEST", new Gson().toJson(accountCheckRequest));
                Call<AccountCheckResponse> call = apiService.createUser(accountCheckRequest);
                call.enqueue(new Callback<AccountCheckResponse>() {

                    @Override
                    public void onResponse(Call<AccountCheckResponse> call, Response<AccountCheckResponse> response) {

                        progress.dismiss();

                        if (response.code() == 201) {
                            Intent intent = new Intent(SignUpActivity.this, EnterCodeActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (response.code() != 201 || response.code() == 422) {
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(SignUpActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
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
