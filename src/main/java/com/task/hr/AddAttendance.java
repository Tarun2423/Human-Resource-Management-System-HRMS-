package com.task.hr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addattendance")
public class AddAttendance extends HttpServlet {
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		

	 	String url = "jdbc:postgresql://localhost:5432/HR";
	    String dbUsername = "postgres";
	    String dbPassword = "root";
	    String query="INSERT INTO Attendance(name,checkin,checkout,manager) VALUES(?,?,?,?)";
	    String name=req.getParameter("name");
	    String checkin=req.getParameter("checkin");
	    String checkout=req.getParameter("checkout");
	    String manager=req.getParameter("manager");
	    System.out.println(name);

	try {
		Class.forName("org.postgresql.Driver");
		Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, name);
		pst.setString(2, checkin);
		pst.setString(3, checkout);
		pst.setString(4, manager);
		pst.executeUpdate();
		System.out.println("Successfully added");
		 String logMessage = String.format("Attendance added - Name: %s, Checkin: %s, Checkout: %s, Manager: %s",
                 name, checkin, checkout, manager);
         String logFilePath = "C:\\Users\\ADMIN\\eclipse-workspace\\hr\\logs\\log.txt";
         Logger.logToFile(logFilePath, logMessage);
		res.getWriter().println("success");
		con.close();
       
	}catch (Exception e) {
		// TODO: handle exception
		System.out.print("some error");
	}
	

		
	}

}
