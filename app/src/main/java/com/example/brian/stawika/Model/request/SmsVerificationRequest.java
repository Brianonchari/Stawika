package com.example.brian.stawika.Model.request;

public class SmsVerificationRequest {


    private String verificationToken, smsVerificationCode;
    private CommonRequest commonRequest;


    public SmsVerificationRequest() {

    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public String getSmsVerificationCode() {
        return smsVerificationCode;
    }

    public void setSmsVerificationCode(String smsVerificationCode) {
        this.smsVerificationCode = smsVerificationCode;
    }

    public CommonRequest getCommonRequest() {
        return commonRequest;
    }

    public void setCommonRequest(CommonRequest commonRequest) {
        this.commonRequest = commonRequest;
    }
}
