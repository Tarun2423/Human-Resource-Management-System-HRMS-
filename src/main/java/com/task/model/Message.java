package com.task.model;

public class Message {
String fromname,toname,message;

public String getFromname() {
	return fromname;
}

public void setFromname(String fromname) {
	this.fromname = fromname;
}

public String getToname() {
	return toname;
}

public void setToname(String toname) {
	this.toname = toname;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

@Override
public String toString() {
	return "{fromname=" + fromname + ", toname=" + toname + ", message=" + message + "}";
}

public Message(String fromname, String toname, String message) {
	super();
	this.fromname = fromname;
	this.toname = toname;
	this.message = message;
}
}
