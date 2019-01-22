package com.example.brian.stawika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final EditText edit = findViewById(R.id.enter_phone);
        edit.requestFocus();
        edit.setSelection(edit.length());



    }

    public void signUp(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void launchCode(View view ){
        Intent intent = new Intent(this,enterCode.class);
        startActivity(intent);
    }
    public void signIn(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
