package com.mindex.challenge.data;

import java.util.List;


/**
 * 
 * @author Dan
 *
 */
public class ReportingStructure {
	private Employee employee;
	private int numberOfReports;
	
	public ReportingStructure() {
	}
	
	/**
	 * 
	 * @param employee
	 * 
	 * Constructor that builds ReportingStructure from employee object.
	 */
	public ReportingStructure(Employee employee){
		this.employee = employee;
		this.numberOfReports = this.calculateNumberOfReports(employee);
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getNumberOfReports() {
		return numberOfReports;
	}
	
	public void setNumberOfReports(int numberOfReports) {
		this.numberOfReports = numberOfReports;
	}
	
	/**
	 * 
	 * @param employee
	 * @return int
	 * Using recursion to total the numberOfReports.
	 */
	private int calculateNumberOfReports(Employee employee) {
		List<Employee> directReports = employee.getDirectReports();
		if(directReports == null || directReports.isEmpty()) {
			return 0;
		}
		for(Employee e: directReports) {
			numberOfReports += directReports.size() + calculateNumberOfReports(e);
		}
		return numberOfReports;
		
	}
	
	
}
