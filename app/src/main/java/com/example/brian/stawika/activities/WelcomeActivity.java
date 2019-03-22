package com.example.brian.stawika.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.brian.stawika.R;
import com.example.brian.stawika.api.RestApiInterface;
import com.example.brian.stawika.api.RestClient;
import com.example.brian.stawika.model.response.LoanProductResponse;
import com.example.brian.stawika.model.response.Product;
import com.example.brian.stawika.model.response.TransactionHomeResponse;
import com.example.brian.stawika.model.response.WalletTransactions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity {



    private RestApiInterface apiService = RestClient.getClient().create(RestApiInterface.class);
    private TextView loanLimitTv, walletBalanceTv, twenyOnePercent, thirtyPercent;

    private String accessToken;
    private Button cancelButton;
    private List<Product> loanProductResponse;

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

        SharedPreferences preferences = this.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        accessToken = preferences.getString("token", "");

        cancelButton = findViewById(R.id.cancelButton);

        findViewById(R.id.startLoanButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getLoanProducts();
                Log.e(" <> ", "Button clicked");

            }
        });


        Call<TransactionHomeResponse> call = apiService.transaction("Bearer " + accessToken);
        call.enqueue(new Callback<TransactionHomeResponse>() {
            @Override
            public void onResponse(Call<TransactionHomeResponse> call, Response<TransactionHomeResponse> response) {
                if (response.isSuccessful()) {
                    Log.e("RESPONSE", new Gson().toJson(response.body()));


                    if (response.body() != null) ;
                    boolean hasloan = response.body().isHasLoan();
                    if (!hasloan) {
                        String loanLimit = String.valueOf(response.body().getLoanLimit());
                        String walletBalance = String.valueOf(response.body().getWalletBalance());
                        List<WalletTransactions> walletTransactions = response.body().getWalletTransactions();


                        loanLimitTv = findViewById(R.id.loanLimitTv);
                        walletBalanceTv = findViewById(R.id.walletBalanceTv);
                        loanLimitTv.setText(loanLimit);
                        walletBalanceTv.setText(walletBalance);
                    } else {

                    }
//                    callApi();
                }
            }

            @Override
            public void onFailure(Call<TransactionHomeResponse> call, Throwable t) {

            }
        });
    }

    public void loadWallet(){

    }

    private void getLoanProducts() {

        Call<List<Product>> call = apiService.loanProduct("Bearer" + accessToken);

        call.enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                Log.e("LoanProductResponse >> ", String.valueOf(response.code()));

                if (response.isSuccessful()) {

                    Log.e("RESPONSE", new Gson().toJson(response.body()));
                    LayoutInflater inflater = getLayoutInflater();
                    View alertLayout = inflater.inflate(R.layout.customstartloan_layout, null);
                    AlertDialog.Builder alert = new AlertDialog.Builder(WelcomeActivity.this);

                    alert.setView(alertLayout);
                    alert.setCancelable(false);
                    final AlertDialog dialog = alert.create();
                    dialog.show();

//                    String products = null;
//                    if (response.body() != null) {
//                        products = response.body().get(0).getProduct();
//
//                    }
                    String rate1 = response.body().get(0).getProduct();
                    String rate2 = response.body().get(1).getProduct();

                    twenyOnePercent = alertLayout.findViewById(R.id.twentyOnePercent);
                    thirtyPercent = alertLayout.findViewById(R.id.thirtyPercent);
                    twenyOnePercent.setText(rate1);
                    thirtyPercent.setText(rate2);

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

                Log.e("LPR failure >> ", t.getMessage());

            }
        });

    }

    public void profile(View view) {
        Intent intent = new Intent(WelcomeActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void loanDetails(View view) {
        Intent intent = new Intent(this, LoanDetailsActivity.class);
        startActivity(intent);
    }

    public  void dismissDialog(View view){
        Intent intent = new Intent(this,WelcomeActivity.class);
        startActivity(intent);
    }


    public void updateUi() {

    }
//    public void startLoan(View view ){
//
//    }
}

