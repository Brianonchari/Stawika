package com.example.brian.stawika.Model.request;

public class RegisterUserRequest {

    String firstName,lastName,otherName,gender,idNumber,email,dob,smsCode,nextKinFullname,nextKinPhone;
    private CommonRequest commonRequest;

    public RegisterUserRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public CommonRequest getCommonRequest() {
        return commonRequest;
    }

    public void setCommonRequest(CommonRequest commonRequest) {
        this.commonRequest = commonRequest;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public void setNextKinFullname(String nextKinFullname) {
        this.nextKinFullname = nextKinFullname;
    }

    public void setNextKinPhone(String nextKinPhone) {
        this.nextKinPhone = nextKinPhone;
    }
}
