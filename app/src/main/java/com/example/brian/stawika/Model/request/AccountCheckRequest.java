package com.example.brian.stawika.Model.request;

import java.util.List;

public class AccountCheckRequest {

    private String password;
    private String phoneNumber;
    private CommonRequest commonRequest;
    private List<Account> accounts;

    public AccountCheckRequest() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CommonRequest getCommonRequest() {
        return commonRequest;
    }

    public void setCommonRequest(CommonRequest commonRequest) {
        this.commonRequest = commonRequest;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    public void createUser(List<Account> accounts){

    }
}
