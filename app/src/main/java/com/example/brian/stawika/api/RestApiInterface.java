package com.example.brian.stawika.api;

import com.example.brian.stawika.model.request.AccountCheckRequest;
import com.example.brian.stawika.model.request.CommonRequest;
import com.example.brian.stawika.model.request.RegisterUserRequest;
import com.example.brian.stawika.model.request.SmsVerificationRequest;
import com.example.brian.stawika.model.request.TransactionsHomeRequest;
import com.example.brian.stawika.model.response.AccountCheckResponse;
import com.example.brian.stawika.model.response.RegisterUserResponse;
import com.example.brian.stawika.model.response.RegistrationDropdownResponse;
import com.example.brian.stawika.model.response.SmsVerificationResponse;
import com.example.brian.stawika.model.response.TransactionHomeResponse;


import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApiInterface {

    @POST("/api/register/account-check")
    Call<AccountCheckResponse> createUser(@Body AccountCheckRequest accountCheckRequest);

    @POST("/api/register/sms-verify")
    Call<SmsVerificationResponse> verifySms(@Body SmsVerificationRequest smsVerificationRequest);

    @POST("/api/register/register-user")
    Call<RegisterUserResponse> registerUser(@Body RegisterUserRequest registerUserResponse);

    @POST("/api/register/registrations-dropdowns")
    Call<RegistrationDropdownResponse> dropDownresponse(@Body CommonRequest commonRequest);

    @POST("oauth/token")
    Call<Map<String, Object>> authenticate(@Header("Authorization")
                                                   String authorization,
                                           @Query("grant_type") String grant_type,
                                           @Query("username") String username,
                                           @Query("password") String password);


    @GET("/api/transactions/home-page")

    Call<TransactionHomeResponse>  getLimit(@Header("Authorization") String authorization,
                                            @Query("access_token") String access_token);

}
