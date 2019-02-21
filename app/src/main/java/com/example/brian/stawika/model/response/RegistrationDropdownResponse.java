package com.example.brian.stawika.model.response;

import java.util.List;

public class RegistrationDropdownResponse {

    private List<MaritalStatus> marital;
    private List<IncomeBand> income;
    private List<EducationLevel> education;
    private List<Rental> rental;
    private List<EmploymentLevel> employment;

    public List<MaritalStatus> getMarital() {
        return marital;
    }

    public List<IncomeBand> getIncome() {
        return income;
    }

    public List<EducationLevel> getEducation() {
        return education;
    }

    public List<Rental> getRental() {
        return rental;
    }

    public List<EmploymentLevel> getEmployment() {
        return employment;
    }
}
