package com.example.employeedata.controller;
import java.util.ArrayList;
import java.util.List;

import com.example.employeedata.bean.Employee;
import com.example.employeedata.bean.EmployeeTax;
import com.example.employeedata.exceptions.InvalidEmployeeDataException;
import com.example.employeedata.repository.EmployeeRepository;
import com.example.employeedata.validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
    public Employee addEmployee(@RequestBody Employee employee) {
//get data from each variable and do validations
        EmployeeValidator validator = new EmployeeValidator();
        if(validator.isPhoneNumberValid(employee.getPhoneNumber()))
        return employeeData.save(employee);
        else
            throw new InvalidEmployeeDataException("invalid phone number entered");

    }

    @RequestMapping(value = "/listEmployees", method = RequestMethod.GET)
    public List<EmployeeTax> getEmployeesTaxInfo() {
        List<Employee> allEmployees = (List<Employee>) employeeData.findAll().iterator();
        List<EmployeeTax> taxedEmployees = new ArrayList<>();

        for (Employee employee:allEmployees) {
            EmployeeTax taxData = null;
            taxData = new EmployeeTax();
            long salaryForTax=findSalaryForTax(employee.getSalary(),Integer.parseInt(employee.getDateOfJoining().substring(5,6)),Integer.parseInt(employee.getDateOfJoining().substring(8,9)));
            taxData.setEmployeeId(employee.getEmployeeId());
            taxData.setFirstName(employee.getFirstName());
            taxData.setLastName(employee.getLastName());
            taxData.setTaxAmount(calculateTax(salaryForTax));
            long cessOnSalary = calculateCess(salaryForTax);
            taxData.setCessAmount(cessOnSalary);
            taxedEmployees.add(taxData);
        }

return taxedEmployees;
    }
private long findSalaryForTax(long salaryPerMonth,int month,int date){
    return salaryPerMonth*(12-month)+calculateRemainingMonthSalary(salaryPerMonth,month,date);
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
             return   ((31-date)+1)*salaryPerMonth/31;
            case 2:
                //check leap year--todo
                return   ((28-date)+1)*salaryPerMonth/28;
            case 4:
            case 6:
            case 9:
            case 11:
                return   ((30-date)+1)*salaryPerMonth/30;
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
            return salary * 5/100;

    }

    private long tax10per(long salary){
    if(salary == 0)
    return 50000;
    else
        return salary * 10/100;

    }

    private long tax20Per(long salary){
        if(salary == 0)
            return 200000;
        else
            return salary * 10/100;

    }

    private long calculateCess(long salary){
        if (salary > SALARY_TWENTY_FIVE_LAKHS){
            return salary - SALARY_TWENTY_FIVE_LAKHS * 2/100;
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
