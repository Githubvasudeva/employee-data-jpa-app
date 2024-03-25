package com.example.accessingdatajpa.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {

	@Id
	private Long employeeId;
	private String firstName;
	private String lastName;

	private String email;

	private String phoneNumber;
	//@DateTimeFormat(pattern = "yyyy-mm-dd")
	private String dateOfJoining;

	private long salary;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	protected Employee() {}

	public Employee(long employeeId,String firstName, String lastName,String email,String phoneNumber,String dateOfJoining,long salary) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateOfJoining = dateOfJoining;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return String.format(
				"Employee[id=%d, firstName='%s', lastName='%s']",
				employeeId, firstName, lastName);
	}

}
