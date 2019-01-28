package com.example.brian.stawika.Model.request;

public class RegistrationDropdownsRequest {

    private String nextOfkinFullname,EducationLevel,EmploymentLevel,NextOfkinPhone,
            AverageIncome,Rental,MaritalStatus,firstName,gender,otherName,lastName,smsCode,email,idNumber,dob;

    private CommonRequest commonRequest;

    public RegistrationDropdownsRequest() {
    }

    public CommonRequest getCommonRequest() {
        return commonRequest;
    }

    public void setCommonRequest(CommonRequest commonRequest) {
        this.commonRequest = commonRequest;
    }

    public String getNextOfkinFullname() {
        return nextOfkinFullname;
    }

    public void setNextOfkinFullname(String nextOfkinFullname) {
        this.nextOfkinFullname = nextOfkinFullname;
    }

    public String getEducationLevel() {
        return EducationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        EducationLevel = educationLevel;
    }

    public String getEmploymentLevel() {
        return EmploymentLevel;
    }

    public void setEmploymentLevel(String employmentLevel) {
        EmploymentLevel = employmentLevel;
    }

    public String getNextOfkinPhone() {
        return NextOfkinPhone;
    }

    public void setNextOfkinPhone(String nextOfkinPhone) {
        NextOfkinPhone = nextOfkinPhone;
    }

    public String getAverageIncome() {
        return AverageIncome;
    }

    public void setAverageIncome(String averageIncome) {
        AverageIncome = averageIncome;
    }

    public String getRental() {
        return Rental;
    }

    public void setRental(String rental) {
        Rental = rental;
    }

    public String getMaritalStatus() {
        return MaritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        MaritalStatus = maritalStatus;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
