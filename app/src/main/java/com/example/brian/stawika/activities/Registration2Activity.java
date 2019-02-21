package com.example.brian.stawika.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import com.example.brian.stawika.Api.RestApiInterface;
import com.example.brian.stawika.Api.RestClient;
import com.example.brian.stawika.Model.SpinnerArrayObject;
import com.example.brian.stawika.Model.request.CommonRequest;
import com.example.brian.stawika.Model.response.EducationLevel;
import com.example.brian.stawika.Model.response.EmploymentLevel;
import com.example.brian.stawika.Model.response.IncomeBand;
import com.example.brian.stawika.Model.response.MaritalStatus;
import com.example.brian.stawika.Model.response.RegistrationDropdownResponse;
import com.example.brian.stawika.Model.response.Rental;
import com.example.brian.stawika.R;
import com.example.brian.stawika.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Registration2Activity extends AppCompatActivity {

    private TextInputEditText nextOfkinEt, nextOfkinPhone;
    private String firstname, lastname, othernames, id, email, dob;
    private RestApiInterface apiService = RestClient.getClient().create(RestApiInterface.class);
    private Spinner spCompany, eductionLevel, maritalStatus, rental, averageIncome;


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

        Log.e("Receive intent", "Received");

        spCompany = findViewById(R.id.spCompany);
        eductionLevel = findViewById(R.id.educationLevel);
        averageIncome = findViewById(R.id.averageIncome);
        maritalStatus = findViewById(R.id.maritalStatus);
        rental = findViewById(R.id.rental);
        nextOfkinEt = findViewById(R.id.next_of_kin);
        nextOfkinPhone = findViewById(R.id.nextOfkinphone);


        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String nextOfkin = nextOfkinEt.getText().toString();
                final String nextOfkinphone = nextOfkinPhone.getText().toString();

                if (nextOfkin.isEmpty()) {
                    nextOfkinEt.setError("This field is required");
                    nextOfkinEt.requestFocus();
                    return;
                }
                if (nextOfkinphone.isEmpty()) {
                    nextOfkinPhone.setError("This field is required");
                    nextOfkinPhone.requestFocus();
                    return;
                }

                CommonRequest commonRequest = new CommonRequest();
                commonRequest.setImei("imei");
                commonRequest.setManufacturer("manufacturer");
                commonRequest.setSimSerial("sim serial");
                commonRequest.setModel(" model");

                Log.e("REQUEST", new Gson().toJson(commonRequest));

                Call<RegistrationDropdownResponse> call = apiService.dropDownresponse(commonRequest);
                call.enqueue(new Callback<RegistrationDropdownResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationDropdownResponse> call, Response<RegistrationDropdownResponse> response) {

                        if (response.isSuccessful()) {
                            Log.e("RESPONSE", new Gson().toJson(response.body()));
                            Constants.registrationDropdownResponse = response.body();

                            ArrayList<SpinnerArrayObject> spinnerArrayObjects = new ArrayList<>();
                            List<IncomeBand> incomeBands = response.body().getIncome();
                            for (IncomeBand level : incomeBands) {
                                SpinnerArrayObject arrayObject = new SpinnerArrayObject();
                                arrayObject.setId(level.getId());
                                arrayObject.setName(String.valueOf(level.getLowerBand()) + "-" + (String.valueOf(level.getHigherBand())));
//

                                spinnerArrayObjects.add(arrayObject);
                            }

                            CustomAdapter customAdapter = new CustomAdapter(Registration2Activity.this, spinnerArrayObjects);
                            averageIncome.setAdapter(customAdapter);
                        }
                        if (response.isSuccessful()) {
                            Log.e("RESPONSE", new Gson().toJson(response.body()));
                            Constants.registrationDropdownResponse = response.body();
                            ArrayList<SpinnerArrayObject> spinnerArrayObjects = new ArrayList<>();

                            List<EmploymentLevel> employmentLevels = response.body().getEmployment();
                            for (EmploymentLevel level : employmentLevels) {
                                SpinnerArrayObject arrayObject = new SpinnerArrayObject();
                                arrayObject.setId(level.getEmpId());
                                arrayObject.setName(level.getName());
                                spinnerArrayObjects.add(arrayObject);
                            }

                            CustomAdapter customAdapter = new CustomAdapter(Registration2Activity.this, spinnerArrayObjects);
                            spCompany.setAdapter(customAdapter);
                        }

                        if (response.isSuccessful()) {
                            Log.e("RESPONSE", new Gson().toJson(response.body()));
                            Constants.registrationDropdownResponse = response.body();

                            ArrayList<SpinnerArrayObject> spinnerArrayObjects = new ArrayList<>();
                            List<EducationLevel> educationLevels = response.body().getEducation();
                            for (EducationLevel level : educationLevels) {
                                SpinnerArrayObject arrayObject = new SpinnerArrayObject();
                                arrayObject.setId(level.getId());
                                arrayObject.setName(level.getEducationLevel());
                                spinnerArrayObjects.add(arrayObject);
                            }

                            CustomAdapter customAdapter = new CustomAdapter(Registration2Activity.this, spinnerArrayObjects);
                            eductionLevel.setAdapter(customAdapter);
                        }

                        if (response.isSuccessful()) {
                            Log.e("RESPONSE", new Gson().toJson(response.body()));
                            Constants.registrationDropdownResponse = response.body();

                            ArrayList<SpinnerArrayObject> spinnerArrayObjects = new ArrayList<>();
                            List<MaritalStatus> maritalStatuses = response.body().getMarital();
                            for (MaritalStatus level : maritalStatuses) {
                                SpinnerArrayObject arrayObject = new SpinnerArrayObject();
                                arrayObject.setName(level.getName());
                                arrayObject.setId(level.getStatusId());
                                spinnerArrayObjects.add(arrayObject);
                            }
                            CustomAdapter customAdapter = new CustomAdapter(Registration2Activity.this, spinnerArrayObjects);
                            maritalStatus.setAdapter(customAdapter);
                        }
                        if (response.isSuccessful()) {
                            Log.e("RESPONSE", new Gson().toJson(response.body()));
                            Constants.registrationDropdownResponse = response.body();

                            ArrayList<SpinnerArrayObject> spinnerArrayObjects = new ArrayList<>();
                            List<Rental> rentals = response.body().getRental();
                            for (Rental level : rentals) {
                                SpinnerArrayObject arrayObject = new SpinnerArrayObject();
                                arrayObject.setName(level.getName());
                                arrayObject.setId(level.getRentalId());
                                spinnerArrayObjects.add(arrayObject);
                            }

                            CustomAdapter customAdapter = new CustomAdapter(Registration2Activity.this, spinnerArrayObjects);
                            rental.setAdapter(customAdapter);
                        }


                    }

                    @Override
                    public void onFailure(Call<RegistrationDropdownResponse> call, Throwable t) {
                    }
                });
            }
        });
    }

    public void redirectTologin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
