package com.example.accessingdatajpa.repository;

import com.example.accessingdatajpa.bean.Employee;
import org.springframework.data.repository.CrudRepository;


public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	//Employee findById(long id); //if want to find specific employee
}
