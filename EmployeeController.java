package com.example.accessingdatajpa.controller;

import com.example.accessingdatajpa.bean.Employee;
import com.example.accessingdatajpa.bean.EmployeeTax;
import com.example.accessingdatajpa.exceptions.InvalidEmployeeDataException;
import com.example.accessingdatajpa.repository.EmployeeRepository;
import com.example.accessingdatajpa.validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


@Controller
public class EmployeeController {

    private static final long SALARY_TWO_LAKHS_FIFTY_THOUSAND= 250000;
    private static final long SALARY_FIVE_LAKHS= 500000;
    private static final long SALARY_SEVEN_LAKHS_FIFTY_THOUSAND= 750000;
    private static final long SALARY_TEN_LAKHS= 1000000;
    private static final long SALARY_TWENTY_FIVE_LAKHS= 2500000;

    //use constants for all harcoded values - todo and use config to define - todo

    @Autowired
    private EmployeeRepository employeeData;

    @RequestMapping(value = "/addNewEmployee", method = RequestMethod.POST)
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) {
//get data from each variable and do validations
        EmployeeValidator validator = new EmployeeValidator();
        if(validator.isPhoneNumberValid(employee.getPhoneNumber()) && validator.isDojValid(employee.getDateOfJoining()) && validator.isEmailValid(employee.getEmail())) {
            employeeData.save(employee);
            return new ResponseEntity<Object>("Employee successfully added in DB",HttpStatus.OK);
        }
        else
        return new ResponseEntity<Object>(new InvalidEmployeeDataException("invalid data entered"), HttpStatus.OK);
    }

    @RequestMapping(value = "/listEmployees", method = RequestMethod.GET)
    public ResponseEntity<Object> getEmployeesTaxInfo() {
        List<Employee> allEmployees = ((ArrayList<Employee>) employeeData.findAll());
        List<EmployeeTax> taxedEmployees = new ArrayList<>();

        for (Employee employee:allEmployees) {
            EmployeeTax taxData = null;
            taxData = new EmployeeTax();
            List<String> date=Collections.list(new StringTokenizer(employee.getDateOfJoining(),"-")).stream().map(token->(String)token).toList();
            long salaryForTax=findSalaryForTax(employee.getSalary()/12,Integer.parseInt(date.get(1)),Integer.parseInt(date.get(2)));
            taxData.setEmployeeId(employee.getEmployeeId());
            taxData.setFirstName(employee.getFirstName());
            taxData.setLastName(employee.getLastName());
            taxData.setYearlySalary(employee.getSalary());
            taxData.setTaxableAmount(salaryForTax);
            taxData.setTaxAmount(calculateTax(salaryForTax));
            long cessOnSalary = calculateCess(employee.getSalary());
            taxData.setCessAmount(cessOnSalary);
            taxedEmployees.add(taxData);
        }
        return new ResponseEntity<Object>(taxedEmployees, HttpStatus.OK);
    }
private long findSalaryForTax(long salaryPerMonth,int month,int date){
    month = (month<10? Integer.parseInt("0"+month) :month);
    date = (date<10? Integer.parseInt("0"+date) :date);
    return (salaryPerMonth*(12-month))+calculateRemainingMonthSalary(salaryPerMonth,month,date);
}

private long calculateRemainingMonthSalary(long salaryPerMonth,int month,int date){
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
             return   ((31-date)+1)*(salaryPerMonth/31);
            case 2:
                //check leap year--todo
                return   ((28-date)+1)*(salaryPerMonth/28);
            case 4:
            case 6:
            case 9:
            case 11:
                return   ((30-date)+1)*(salaryPerMonth/30);
            default:
                //UnAcceptableMonthEnteredException - Custom exception should write and return - todo
                throw new RuntimeException("please enter correct value for month");
        }
}
    private long noTax(){
      return 0;

    }
    private long tax5per(long salary){
       if (salary == 0)
           return 12500;
       else
            return (long) (salary * (5/100.0f));

    }

    private long tax10per(long salary){
    if(salary == 0)
    return 50000;
    else
        return (long) (salary * (10/100.0f));

    }

    private long tax20Per(long salary){
        if(salary == 0)
            return 200000;
        else
            return (long) (salary * (10/100.0f));

    }

    private long calculateCess(long salary){
        if (salary > SALARY_TWENTY_FIVE_LAKHS){
            return (long) ((salary - SALARY_TWENTY_FIVE_LAKHS) * (2/100.0f));
        }else return 0;
    }
private long calculateTax(long salary){
        long tax=0;
    if (salary <= SALARY_TWO_LAKHS_FIFTY_THOUSAND){
        return noTax();
    } else if (salary <= SALARY_FIVE_LAKHS) {
       return noTax()+tax5per(salary-SALARY_TWO_LAKHS_FIFTY_THOUSAND);
    }
    else if(salary <= SALARY_TEN_LAKHS){
        return noTax()+tax5per(0)+tax10per(salary-SALARY_SEVEN_LAKHS_FIFTY_THOUSAND);

    }
    else {
        return noTax()+tax5per(0)+tax10per(0)+tax20Per(salary-SALARY_TEN_LAKHS);
    }
}
}
