package com.example.brian.stawika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ResetPin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pin);

    }
    public void onresetSuccess(View view ){
        Intent i = new Intent(ResetPin.this, PasswordSent.class);
        startActivity(i);

    }

}
