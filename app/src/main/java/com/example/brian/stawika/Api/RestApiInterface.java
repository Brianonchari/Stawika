package com.example.brian.stawika.Api;

import com.example.brian.stawika.Model.request.AccountCheckRequest;
import com.example.brian.stawika.Model.response.AccountCheckResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;



public interface RestApiInterface {

    @POST("/api/register/account-check")
    Call<AccountCheckResponse> createUser(@Body AccountCheckRequest accountCheckRequest);



}
