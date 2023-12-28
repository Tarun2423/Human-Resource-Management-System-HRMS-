package com.task.model;

public class Leave {

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	String name,reason,dates,status,manager;

	public Leave(String name, String reason, String dates,String status,String manager) {
		super();
		this.name = name;
		this.reason = reason;
		this.dates = dates;
		this.status=status;	
		this.manager=manager;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	

	@Override
	public String toString() {
		return "{name=" + name + ", reason=" + reason + ", dates=" + dates + ", status=" + status + ", manager="
				+ manager + "}";
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}
	
	
}
