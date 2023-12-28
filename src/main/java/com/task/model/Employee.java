package com.task.model;

public class Employee {
	int EmpId;
	String name,role,Joining,dob,phoneno,email,manager;
	public Employee(int empId, String name, String role, String joining, String dob, String phoneno, String email,String manager) {
		super();
		EmpId = empId;
		this.name = name;
		this.role = role;
		Joining = joining;
		this.dob = dob;
		this.phoneno = phoneno;
		this.email = email;
		this.manager=manager;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEmpId() {
		return EmpId;
	}
	public void setEmpId(int empId) {
		EmpId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getJoining() {
		return Joining;
	}
	public void setJoining(String joining) {
		Joining = joining;
	}
	public Employee(int empId, String name, String role, String joining) {
		super();
		EmpId = empId;
		this.name = name;
		this.role = role;
		Joining = joining;
	}
	@Override
	public String toString() {
		return "{EmpId=" + EmpId + ", name=" + name + ", role=" + role + ", Joining=" + Joining + "}";
	}
	
	
	

}
