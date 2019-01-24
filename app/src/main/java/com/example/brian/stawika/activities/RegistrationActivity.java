package com.example.brian.stawika.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.brian.stawika.Api.RestApiInterface;
import com.example.brian.stawika.Api.RestClient;
import com.example.brian.stawika.Model.request.AccountCheckRequest;
import com.example.brian.stawika.Model.response.AccountCheckResponse;
import com.example.brian.stawika.R;
import com.google.gson.Gson;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private EditText editText;
    private int Year, Month, Day;
    private TextInputEditText firstnameEt, lastnameEt, othernamesEt, IDEt, emailEt, DOBEt;
    private RestApiInterface apiService = RestClient.getClient().create(RestApiInterface.class);
    private RadioGroup radiobtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editText = findViewById(R.id.enter_dob);
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(Year, Month+1,Day);

        firstnameEt = findViewById(R.id.firstname);
        lastnameEt = findViewById((R.id.lastname));
        othernamesEt = findViewById(R.id.othernames);
        IDEt = findViewById(R.id.id);
        emailEt = findViewById(R.id.email);
        DOBEt = findViewById(R.id.enter_dob);

        findViewById(R.id.btn_proceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = firstnameEt.getText().toString();
                String lastname = lastnameEt.getText().toString();
                String othernames = othernamesEt.getText().toString();
//                String gender= radiobtn.isClickable()
                String ID = IDEt.getText().toString();
                String email = emailEt.getText().toString();
                String DOB = DOBEt.getText().toString();

                if (firstname.isEmpty()) {
                    firstnameEt.setError("This Field is required");
                    firstnameEt.requestFocus();
                    return;
                }
                if (lastname.isEmpty()) {
                    lastnameEt.setError("This Field is required");
                    lastnameEt.requestFocus();
                    return;
                }
                if (othernames.isEmpty()) {
                    othernamesEt.setError("This Field is required");
                    othernamesEt.requestFocus();
                    return;
                }
                if (ID.isEmpty()) {
                    IDEt.setError("This Field is required");
                    IDEt.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    emailEt.setError("This Field is required");
                    emailEt.requestFocus();
                    return;
                }
                if (DOB.isEmpty()) {
                    DOBEt.setError("This Field is required");
                    DOBEt.requestFocus();
                    return;
                }

                AccountCheckRequest accountCheckRequest = new AccountCheckRequest();
                accountCheckRequest.setFirstname(firstnameEt.getText().toString());
                accountCheckRequest.setLastname(lastnameEt.getText().toString());
                accountCheckRequest.setOtherNames(othernamesEt.getText().toString());
                accountCheckRequest.setId(IDEt.getText().toString());
                accountCheckRequest.setEmai(emailEt.getText().toString());
                accountCheckRequest.setDOB(lastnameEt.getText().toString());
                Log.e("REQUEST", new Gson().toJson(accountCheckRequest));

                Call<AccountCheckResponse> call = apiService.createUser(accountCheckRequest);
                call.enqueue(new Callback<AccountCheckResponse>() {
                    @Override
                    public void onResponse(Call<AccountCheckResponse> call, Response<AccountCheckResponse> response) {
                        Log.e("RESPONSE", new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(RegistrationActivity.this, Registration2Activity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<AccountCheckResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
    public void setDob(View view){
        showDialog(999);
        Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR,Year);
            calendar.set(Calendar.MONTH,Month);
            calendar.set(Calendar.DAY_OF_MONTH,Day);
            showDate(Year, month+1,Day);

        }
    };

    private void showDate(int Year,int Month, int Day){
        editText.setText(new StringBuilder().append(Day).append("/")
                .append(Month).append("/").append(Year));


    }
    @Override
    protected Dialog onCreateDialog(int id) {
        showDialog(99);
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, Year, Month, Day);
        }
        return null;
    }

    public void signIn(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void nextStep(View view){
        Intent intent = new Intent(this, Registration2Activity.class);
        startActivity(intent);
    }
}
