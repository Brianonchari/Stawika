package com.example.brian.stawika.activities;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.brian.stawika.R;
import com.example.brian.stawika.api.RestApiInterface;
import com.example.brian.stawika.api.RestClient;

import java.io.IOException;
import java.util.Map;

import okhttp3.Credentials;
import okhttp3.internal.http2.ErrorCode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements DialogInterface.OnClickListener, View.OnKeyListener {

    private TextInputEditText phoneEt, pinEt;
    private ProgressDialog progress;
    private CheckBox checkBox;
    private RestApiInterface apiService = RestClient.getClient().create(RestApiInterface.class);
    private String phoneNumber, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login2);
        Log.d("LoginActivity", "");

        phoneEt = findViewById(R.id.phone);
        pinEt = findViewById(R.id.pinEt);

        pinEt.setOnKeyListener(this);

        phoneEt.requestFocus();
        phoneEt.setSelection(phoneEt.length());

        findViewById(R.id.btnSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (phoneEt.getText() != null && phoneEt.getText().length() > 0)
                    phoneNumber = phoneEt.getText().toString().replace("|", "");
                ;

                if (pinEt.getText() != null && pinEt.getText().length() > 0)
                    password = pinEt.getText().toString();

                if ("".equals(phoneNumber) || phoneNumber.length() != 12) {
                    phoneEt.setError("Enter valid phone number");
                    phoneEt.requestFocus();
                    return;
                }

                if (password.isEmpty()) {

                    pinEt.setError("Password can not be empty");
                    pinEt.requestFocus();
                    return;
                }

                progress = new ProgressDialog(LoginActivity.this);
                progress.setTitle("Please wait");
                progress.setMessage("Log In");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setIndeterminate(true);
                progress.setProgress(0);
                progress.show();

                final String authorization = Credentials.basic("android-app", "secret");

                Call<Map<String, Object>> call = apiService.authenticate(authorization, "password", phoneEt.getText().toString(), pinEt.getText().toString());
                call.enqueue(new Callback<Map<String, Object>>() {
                    @Override
                    public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {

                        if (response.isSuccessful()) {

                            checkBox = findViewById(R.id.checkBox);

                            if (checkBox.isChecked()) {

                                progress.dismiss();

                                String token = (String) response.body().get("access_token");

                                SharedPreferences preferences = LoginActivity.this.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
                                preferences.edit().putString("token", token).apply();

                                Log.i("token", token);

                                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                checkBox.setError("Please accept terms and conditions");
                                checkBox.requestFocus();
                            }

                        } else {
                            // Log.e("LogIn", String.valueOf(response));
                            if (response.code() == 400) {
                                progress.dismiss();
                                Toast.makeText(LoginActivity.this, "Error 404: Bad Request ,Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                        if (t instanceof IOException) {
                            progress.dismiss();
                            Toast.makeText(LoginActivity.this, "Network Error, " +
                                    "Check yout internet connection", Toast.LENGTH_LONG).show();

                        }

                    }
                });
            }
        });
    }

    public void signUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
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

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }
}
