package com.example.brian.stawika.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.brian.stawika.R;


public class EnterCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerReceiver(broadcastReceiver, new IntentFilter("broadCastName"));
        setContentView(R.layout.activity_enter_code);
    }
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle b = intent.getExtras();
            String code = b.getString("code");
            Log.e("CODE", code);

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    public void registrationForm(View view){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
    public void  signUp(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
