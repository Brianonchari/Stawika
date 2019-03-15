package com.example.brian.stawika.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.brian.stawika.R;
import com.example.brian.stawika.api.RestApiInterface;
import com.example.brian.stawika.api.RestClient;
import com.example.brian.stawika.model.request.CommonRequest;
import com.example.brian.stawika.model.response.RegistrationDropdownResponse;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class

RegistrationActivity extends AppCompatActivity {

    private TextInputEditText firstnameEt, lastnameEt, othernamesEt, IDEt, emailEt, DOBEt;
    private String firstName, lastName, otherName, idNumber, email, dob;
    private RestApiInterface apiService = RestClient.getClient().create(RestApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firstnameEt = findViewById(R.id.firstname);
        lastnameEt = findViewById((R.id.lastname));
        othernamesEt = findViewById(R.id.othernames);
        IDEt = findViewById(R.id.id);
        emailEt = findViewById(R.id.email);
        DOBEt = findViewById(R.id.enter_dob);

        findViewById(R.id.btn_proceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (firstnameEt.getText() != null && firstnameEt.length() > 0)
                    firstName = firstnameEt.getText().toString();

                if (lastnameEt.getText() != null && lastnameEt.length() > 0)
                    lastName = lastnameEt.getText().toString();

                if (othernamesEt.getText() != null && othernamesEt.length() > 0)
                    otherName = othernamesEt.getText().toString();

                if (IDEt.getText() != null && IDEt.length() > 0)
                    idNumber = IDEt.getText().toString();

                if (emailEt.getText() != null && emailEt.length() > 0)
                    email = emailEt.getText().toString();

                if (DOBEt.getText() != null && DOBEt.length() > 0)
                    dob = DOBEt.getText().toString();

                if (firstName.isEmpty()) {
                    firstnameEt.setError("This Field is required");
                    firstnameEt.requestFocus();
                    return;
                }

                if (lastName.isEmpty()) {
                    lastnameEt.setError("This Field is required");
                    lastnameEt.requestFocus();
                    return;
                }
                if (otherName.isEmpty()) {
                    othernamesEt.setError("This Field is required");
                    othernamesEt.requestFocus();
                    return;
                }
                if (idNumber.isEmpty()) {
                    IDEt.setError("This Field is required");
                    IDEt.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    emailEt.setError("This Field is required");
                    emailEt.requestFocus();
                    return;
                }
                if (dob.isEmpty()) {
                    DOBEt.setError("This Field is required");
                    DOBEt.requestFocus();
                    return;
                }

                CommonRequest commonRequest = new CommonRequest();
                commonRequest.setModel("model");
                commonRequest.setImei("imei");
                commonRequest.setManufacturer("manufacturer");
                commonRequest.setSimSerial("sim serial");

                Log.e("REQUEST", new Gson().toJson(commonRequest));

                Call<RegistrationDropdownResponse> call = apiService.dropDownresponse(commonRequest);

                call.enqueue(new Callback<RegistrationDropdownResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationDropdownResponse> call, Response<RegistrationDropdownResponse> response) {

                        Log.e("RESPONSE", new Gson().toJson(response.body()));

                        if (response.isSuccessful()) {
                            Intent intent = new Intent(RegistrationActivity.this, Registration2Activity.class);
                            intent.putExtra("firstname", firstName);
                            intent.putExtra("lastname", lastName);
                            intent.putExtra("othernames", otherName);
                            intent.putExtra("id", idNumber);
                            intent.putExtra("email", email);
                            intent.putExtra("dob", dob);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegistrationDropdownResponse> call, Throwable t) {
                        if (t instanceof IOException) {
                            Toast.makeText(RegistrationActivity.this, "Network Error " +
                                    "Check your Internet connection", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }


    public void setDob(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
    }

    public void signIn(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void nextStep(View view) {
        Intent intent = new Intent(this, Registration2Activity.class);
        startActivity(intent);
    }
}
