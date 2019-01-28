package com.example.brian.stawika.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.brian.stawika.Api.RestApiInterface;
import com.example.brian.stawika.Api.RestClient;
import com.example.brian.stawika.R;

import java.util.List;


public class Registration2Activity extends AppCompatActivity {

    private TextInputEditText nextOfkinEt, nextOfkinPhone;
    private String firstname, lastname, othernames, id, email, dob;
    private RestApiInterface apiService = RestClient.getClient().create(RestApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_tworegister);

        Intent intent = getIntent();
        firstname = intent.getStringExtra("firstname");
        lastname = intent.getStringExtra("lastname");
        othernames = intent.getStringExtra("othernames");
        id = intent.getStringExtra("id");
        email = intent.getStringExtra("email");
        dob = intent.getStringExtra("dob");

        Log.e("firstname",firstname);



    }
}
