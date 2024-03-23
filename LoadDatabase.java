package com.example.employeedata.db;

import com.example.employeedata.EmployeeDataJpaApplication;
import com.example.employeedata.bean.Employee;
import com.example.employeedata.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(EmployeeDataJpaApplication.class);
    @Bean
    public CommandLineRunner initDatabase(EmployeeRepository repository) {
        return (args) -> {
            repository.save(new Employee(1, "vasu", "deva", "vasu@gmail.com", "5649856498", "2013-12-23", 987869));
            // fetch all
            log.info("Employees found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(employee -> {
                log.info(employee.toString());
            });

        };
    }
}