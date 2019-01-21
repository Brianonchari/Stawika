package com.example.brian.stawika;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.Toast;

import java.util.Calendar;

public class Registration extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private EditText editText;
    private int Year, Month, Day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Initialize
        editText = findViewById(R.id.enter_dob);
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(Year, Month+1,Day);
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
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);

    }
}
