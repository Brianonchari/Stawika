package com.example.brian.stawika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;

public class Registration2 extends AppCompatActivity {
    private AppCompatSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_tworegister);

        spinner = findViewById(R.id.gender);


    }
}
