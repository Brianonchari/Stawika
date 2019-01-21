package com.example.brian.stawika;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
//        set cursor position
        final EditText edit = findViewById(R.id.hint);
        edit.requestFocus();
        edit.setSelection(edit.length());


    }

    public void launchProfile(View view) {
        Intent intent = new Intent(this,Profile.class);
        startActivity(intent);
    }

    public void signUp(View view) {
        Intent signup = new Intent(this, SignUp.class);
        startActivity(signup);
    }


    public void launchCode(View view) {
        Intent code = new Intent(LoginActivity.this,enterCode.class);
        startActivity(code);
    }

    public void resetPin(View view) {
        Intent i = new Intent(LoginActivity.this,ResetPin.class);
        startActivity(i);
    }
}
