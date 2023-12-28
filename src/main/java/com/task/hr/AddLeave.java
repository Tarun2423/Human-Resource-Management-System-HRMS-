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

@WebServlet("/addLeave")
public class AddLeave extends HttpServlet {
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		

		 	String url = "jdbc:postgresql://localhost:5432/HR";
		    String dbUsername = "postgres";
		    String dbPassword = "root";
		    String query="INSERT INTO Leave(name,reason,dates,manager) VALUES(?,?,?,?)";
		    String name=req.getParameter("name");
		    String reason=req.getParameter("reason");
		    String dates=req.getParameter("dates");
		    String manager=req.getParameter("manager");
		    System.out.println(name);
		    System.out.println(reason);
		    System.out.println(dates);
		    System.out.println(manager);
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, reason);
			pst.setString(3, dates);
			pst.setString(4, manager);
			pst.executeUpdate();
			System.out.println("Successfully added");
			 String logMessage = String.format("Leave added - Name: %s, Reason: %s, dates: %s, Status: %s",
                     name, reason, dates, "Waiting for Approval");
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
