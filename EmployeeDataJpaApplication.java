package com.example.employeedata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(EmployeeDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDataJpaApplication.class);
	}

}
