package com.example.employeedata.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EmployeeTax {

	@Id
	private Long employeeId;
	private String firstName;
	private String lastName;

	private long yearlySalary;

	private long taxAmount;
	private long cessAmount;

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

	public long getYearlySalary() {
		return yearlySalary;
	}

	public void setYearlySalary(long yearlySalary) {
		this.yearlySalary = yearlySalary;
	}

	public long getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(long taxAmount) {
		this.taxAmount = taxAmount;
	}

	public long getCessAmount() {
		return cessAmount;
	}

	public void setCessAmount(long cessAmount) {
		this.cessAmount = cessAmount;
	}

	public EmployeeTax() {}

	public EmployeeTax(long employeeId, String firstName, String lastName,long yearlySalary,long taxAmount,long cessAmount) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearlySalary = yearlySalary;
		this.taxAmount = taxAmount;
		this.cessAmount = cessAmount;
	}

	@Override
	public String toString() {
		return String.format(
				"Employee[id=%d, firstName='%s', lastName='%s', tax='%s']",
				employeeId, firstName, lastName,taxAmount);
	}

}
