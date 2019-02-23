package com.example.brian.stawika.activities;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.Map;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements DialogInterface.OnClickListener, View.OnKeyListener {


    private TextInputEditText phoneEt, pinEt;
    private ProgressDialog progress;
    private CheckBox checkBox;
    private RestApiInterface apiService = RestClient.getClient().create(RestApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        Log.d("LoginActivity", "");

        phoneEt = findViewById(R.id.phone);
        pinEt = findViewById(R.id.pinEt);
        checkBox = findViewById(R.id.checkBox);

        pinEt.setOnKeyListener(this);

        phoneEt.requestFocus();
        phoneEt.setSelection(phoneEt.length());

        findViewById(R.id.btnSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final String phoneNumber = phoneEt.getText().toString();
                final String password = pinEt.getText().toString();


                if (phoneNumber == "" || phoneNumber.length() != 12) {
                    phoneEt.setError("Enter valid phone number");
                    phoneEt.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    pinEt.setError("Password can not be empty");
                    pinEt.requestFocus();
                    return;
                }

                if(checkBox.isChecked()){

                }else {
                    Toast.makeText(LoginActivity.this, "Accept Terma and conditions", Toast.LENGTH_SHORT).show();
                }

                final String authorization = Credentials.basic("android-app", "secret");

                Call<Map<String, Object>> call = apiService.authenticate(authorization, "password", phoneEt.getText().toString(), pinEt.getText().toString());
                call.enqueue(new Callback<Map<String, Object>>() {
                    @Override
                    public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                        if (response.isSuccessful()) {
                            progress = new ProgressDialog(LoginActivity.this);
                            progress.setMessage("Log In");
                            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progress.setIndeterminate(true);
                            progress.setProgress(0);
                            progress.show();

                            final int totalProgressTime = 100;
                            final Thread t = new Thread() {
                                @Override
                                public void run() {
                                    int jumpTime = 1;

                                    while (jumpTime < totalProgressTime) {
                                        try {
                                            sleep(20000);
                                            jumpTime += 1;
                                            progress.setProgress(jumpTime);
                                        } catch (InterruptedException e) {

                                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            };
                            t.start();
                            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                            finish();


                        } else {
                            Log.e("LogIn", String.valueOf(response));
                            Toast.makeText(getBaseContext(), "Check Your details", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, Object>> call, Throwable t) {

                    }
                });
            }
        });
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


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }
}
