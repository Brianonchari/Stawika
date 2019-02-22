package com.example.brian.stawika.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.brian.stawika.R;
import com.example.brian.stawika.api.RestApiInterface;
import com.example.brian.stawika.api.RestClient;
import com.example.brian.stawika.model.request.SmsVerificationRequest;
import com.example.brian.stawika.model.response.SmsVerificationResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EnterCodeActivity extends AppCompatActivity {

    private TextInputEditText enterCodeEt;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle b = intent.getExtras();
            String code = b.getString("code");
            Log.e("CODE", code);
            Log.e("CODE", code);
            enterCodeEt.setText(code);
        }
    };
    private String token;
    private RestApiInterface apiService = RestClient.getClient().create(RestApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        Log.e("token", token);


        registerReceiver(broadcastReceiver, new IntentFilter("broadCastName"));
        setContentView(R.layout.activity_enter_code);
        enterCodeEt = findViewById(R.id.code);
        findViewById(R.id.btn_proceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = enterCodeEt.getText().toString().trim();
                if (code.isEmpty()) {
                    enterCodeEt.setError("This field cant be empty");
                    enterCodeEt.requestFocus();
                    return;
                }

                verifyOtp(code);
                SmsVerificationRequest smsVerificationRequest = new SmsVerificationRequest();
                smsVerificationRequest.setSmsVerificationCode(enterCodeEt.getText().toString());
                smsVerificationRequest.setVerificationToken(token);

                Log.e("REQUEST", new Gson().toJson(smsVerificationRequest));


                Call<SmsVerificationResponse> call = apiService.verifySms(smsVerificationRequest);
                call.enqueue(new Callback<SmsVerificationResponse>() {
                    @Override
                    public void onResponse(Call<SmsVerificationResponse> call, Response<SmsVerificationResponse> response) {


                        if (response.isSuccessful()) {
                            Intent intent = new Intent(EnterCodeActivity.this, RegistrationActivity.class);
                            intent.putExtra("token", response.body().getToken());
                            startActivity(intent);
                        } else {
                            Toast.makeText(getBaseContext(), "Try Again", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SmsVerificationResponse> call, Throwable t) {

                        Toast.makeText(EnterCodeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });


            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
    //    verify sms code

    private void verifyOtp(String otp) {


        if (!otp.isEmpty()) {
            Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
            i.putExtra("otp", otp);
            startService(i);
        }
    }


    public void registrationForm(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
