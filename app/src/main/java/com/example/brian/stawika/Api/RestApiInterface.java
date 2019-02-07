package com.example.brian.stawika.Api;

import com.example.brian.stawika.Model.request.AccountCheckRequest;
import com.example.brian.stawika.Model.request.CommonRequest;
import com.example.brian.stawika.Model.request.RegisterUserRequest;
import com.example.brian.stawika.Model.request.SmsVerificationRequest;
import com.example.brian.stawika.Model.response.AccountCheckResponse;
import com.example.brian.stawika.Model.response.RegistrationDropdownResponse;
import com.example.brian.stawika.Model.response.RegisterUserResponse;
import com.example.brian.stawika.Model.response.SmsVerificationResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface RestApiInterface {

    @POST("/api/register/account-check")
    Call<AccountCheckResponse> createUser(@Body AccountCheckRequest accountCheckRequest);

    @POST("/api/register/sms-verify")
    Call<SmsVerificationResponse> verifySms(@Body SmsVerificationRequest smsVerificationRequest);

    @POST("/api/register/register-user")
    Call<RegisterUserResponse> registerUser(@Body RegisterUserRequest registerUserResponse );

    @POST("/api/register/registrations-dropdowns")
    Call<RegistrationDropdownResponse> dropDownresponse (@Body CommonRequest commonRequest);
}
