package com.example.brian.stawika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class enterCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);


    }

    public void registrationForm(View view){
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);
    }
}
