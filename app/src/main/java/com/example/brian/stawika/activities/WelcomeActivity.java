package com.example.brian.stawika.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brian.stawika.R;
import com.example.brian.stawika.api.RestApiInterface;
import com.example.brian.stawika.api.RestClient;
import com.example.brian.stawika.model.request.TransactionsHomeRequest;
import com.example.brian.stawika.model.response.TransactionHomeResponse;
import com.example.brian.stawika.model.response.WalletTransactions;
import com.example.brian.stawika.utils.Constants;
import com.google.gson.Gson;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity {

    private Context context;
    private RestApiInterface apiService = RestClient.getClient().create(RestApiInterface.class);
    private TextView loanLimitTv, walletBalanceTv;


    //inflate menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        return super.onContextItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

      loanLimitTv = findViewById(R.id.loanLimitTv);

       TransactionsHomeRequest transactionsHomeRequest = new TransactionsHomeRequest();
       transactionsHomeRequest.getStatusCode();
       transactionsHomeRequest.getStatusCodeVale();
       transactionsHomeRequest.getBody();

        Log.e("REQUEST", new Gson().toJson(transactionsHomeRequest));

        Call<TransactionHomeResponse> call = apiService.getLimit();
        call.enqueue(new Callback<TransactionHomeResponse>() {
            @Override
            public void onResponse(Call<TransactionHomeResponse> call, Response<TransactionHomeResponse> response) {
                if(response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<TransactionHomeResponse> call, Throwable t) {

            }
        });



    }

    public void startLoan(View view){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.customstartloan_layout,null);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("Info", "NEgative button clicked");
            }
        });
        alert.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();



    }

    public void profile(View view) {
        Intent intent = new Intent(WelcomeActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}
