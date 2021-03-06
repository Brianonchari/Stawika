package com.example.brian.stawika.model;

import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("idNumber")
    private int idNumber;
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("othername")
    private String othername;
    @SerializedName("gender")
    private String gender;
    @SerializedName("nextKinfullname")
    private String nextKinfullname;
    @SerializedName("nextOfKinphone")
    private String nextOfKinphone;
    @SerializedName("smsCode")
    private String smsCode;
    @SerializedName("email")
    private String email;

    public Users(int idNumber, String firstname, String lastname, String othername,
                 String gender, String nextKinfullname, String nextOfKinphone, String smsCode, String email) {
        this.idNumber = idNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.othername = othername;
        this.gender = gender;
        this.nextKinfullname = nextKinfullname;
        this.nextOfKinphone = nextOfKinphone;
        this.smsCode = smsCode;
        this.email = email;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNextKinfullname() {
        return nextKinfullname;
    }

    public void setNextKinfullname(String nextKinfullname) {
        this.nextKinfullname = nextKinfullname;
    }

    public String getNextOfKinphone() {
        return nextOfKinphone;
    }

    public void setNextOfKinphone(String nextOfKinphone) {
        this.nextOfKinphone = nextOfKinphone;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
