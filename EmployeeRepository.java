package com.example.employeedata.repository;

import com.example.employeedata.bean.Employee;
import org.springframework.data.repository.CrudRepository;


public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	//Employee findById(long id); //if want to find specific employee
}
