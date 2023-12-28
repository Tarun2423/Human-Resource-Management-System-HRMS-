package com.task.model;

public class ProjectModel {
public ProjectModel(String toname, String pname, String pdesc) {
		super();
		this.toname = toname;
		this.pname = pname;
		this.pdesc = pdesc;
	}



@Override
public String toString() {
	return "{toname=" + toname + ", pname=" + pname + ", pdesc=" + pdesc + "}";
}



String toname, pname,pdesc;

public String getToname() {
	return toname;
}

public void setToname(String toname) {
	this.toname = toname;
}

public String getPname() {
	return pname;
}

public void setPname(String pname) {
	this.pname = pname;
}

public String getPdesc() {
	return pdesc;
}



public void setPdesc(String pdesc) {
	this.pdesc = pdesc;
}


}
