package com.example.employeedata.validator;

import java.util.regex.Pattern;

public class EmployeeValidator {
public EmployeeValidator(){

}
    public boolean isPhoneNumberValid(String phone){
        Pattern pattern = Pattern.compile("^\\d{10}$");
        return pattern.matcher(phone).matches();

    }
    public boolean isdobValid(String dob){
        //validations to add - todo
        return true;
    }

    public boolean isEmailValid(String email){
        //validations to add - todo
        return true;
    }

}
