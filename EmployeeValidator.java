package com.example.accessingdatajpa.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class EmployeeValidator {
public EmployeeValidator(){

}
    public boolean isPhoneNumberValid(String phone){
        Pattern pattern = Pattern.compile("^\\d{10}$");
        return pattern.matcher(phone).matches();

    }
    public boolean isDojValid(String doj){
        SimpleDateFormat date= new SimpleDateFormat("yyyy-MM-dd");
        try {
            date.parse(doj);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public boolean isEmailValid(String email){
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        return pattern.matcher(email).matches();
    }

}
