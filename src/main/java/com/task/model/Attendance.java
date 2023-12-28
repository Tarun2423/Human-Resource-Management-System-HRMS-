package com.task.model;

public class Attendance {

	String name,checkin,checkout,manager;

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "{name=" + name + ", checkin=" + checkin + ", checkout=" + checkout + ", manager=" + manager
				+ "}";
	}

	public Attendance(String name, String checkin, String checkout, String manager) {
		super();
		this.name = name;
		this.checkin = checkin;
		this.checkout = checkout;
		this.manager = manager;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	
	
	
}
