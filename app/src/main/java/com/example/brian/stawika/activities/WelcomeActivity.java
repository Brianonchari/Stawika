package com.example.brian.stawika.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.brian.stawika.R;
import com.example.brian.stawika.adapters.RatesAdapter;
import com.example.brian.stawika.api.RestApiInterface;
import com.example.brian.stawika.api.RestClient;
import com.example.brian.stawika.model.RatesArrayObject;
import com.example.brian.stawika.model.response.LoanProductResponse;
import com.example.brian.stawika.model.response.Product;
import com.example.brian.stawika.model.response.TransactionHomeResponse;
import com.example.brian.stawika.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity {

    private Context context;
    private RestApiInterface apiService = RestClient.getClient().create(RestApiInterface.class);
    private TextView loanLimitTv, walletBalanceTv, twenyOnePercent, thirtyPercent;
    private TextInputEditText enterAmountEt;
    private Spinner productSpinner;

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

        productSpinner = findViewById(R.id.productSpinner);
        SharedPreferences preferences = WelcomeActivity.this.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        final String accessToken = preferences.getString("token", "");

        Log.e("Retrieved", accessToken);

        Call<TransactionHomeResponse> call = apiService.transaction("Bearer " + accessToken);
        call.enqueue(new Callback<TransactionHomeResponse>() {
            @Override
            public void onResponse(Call<TransactionHomeResponse> call, Response<TransactionHomeResponse> response) {
                if (response.isSuccessful()) {

                    Boolean chekLoan = response.body().isHasLoan();
                    if(chekLoan==true){

                    }else if(chekLoan ==false) {

                        Log.e("RESPONSE", new Gson().toJson(response.body()));
                        String loanLimit = String.valueOf(response.body().getLoanLimit());
                        String walletBalance = String.valueOf(response.body().getWalletBalance());
                        loanLimitTv = findViewById(R.id.loanLimitTv);
                        walletBalanceTv = findViewById(R.id.walletBalanceTv);
                        loanLimitTv.setText(loanLimit);
                        walletBalanceTv.setText(walletBalance);
                    }

                }
            }

            @Override
            public void onFailure(Call<TransactionHomeResponse> call, Throwable t) {

            }
        });

    }


    public void profile(View view) {
        Intent intent = new Intent(WelcomeActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void startLoanButton(View view){

        callApi();

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.customstartloan_layout, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(WelcomeActivity.this);
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("Info", "Negative button clicked");
            }
        });
        alert.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void  callApi(){

        findViewById(R.id.startLoanButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = WelcomeActivity.this.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
                final String accessToken = preferences.getString("token", "");

               // Log.e("Retrieved", accessToken);

                Call<LoanProductResponse> call = apiService.loanProduct("Bearer" +accessToken);
                call.enqueue(new Callback<LoanProductResponse>() {
                    @Override
                    public void onResponse(Call<LoanProductResponse> call, Response<LoanProductResponse> response) {
                        if(response.isSuccessful()){
                            Log.e("RESPONSE", new Gson().toJson(response.body()));
                            Constants.loanProductResponse= response.body();


                            ArrayList<RatesArrayObject> ratesArrayObjects = new ArrayList<>();
                            List<Product> products = response.body().getProducts();
                            for(Product level : products){
                                RatesArrayObject arrayObject = new RatesArrayObject();
                                arrayObject.setProductId(level.getProductId());
                                arrayObject.setProduct(level.getProduct());
                                ratesArrayObjects.add(arrayObject);
                            }

                            RatesAdapter adapter = new RatesAdapter(ratesArrayObjects,WelcomeActivity.this);
                            productSpinner.setAdapter(adapter);

                        }
                    }

                    @Override
                    public void onFailure(Call<LoanProductResponse> call, Throwable t) {

                    }
                });



            }
        });

    }
}

