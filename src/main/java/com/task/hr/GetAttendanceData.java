package com.task.hr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nimbusds.jose.shaded.gson.Gson;
import com.task.model.Attendance;

@WebServlet("/getattendance")
public class GetAttendanceData extends HttpServlet {
	
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		
		ArrayList<Attendance> AttendanceList=new ArrayList<Attendance>();
	 	String url = "jdbc:postgresql://localhost:5432/HR";
	    String dbUsername = "postgres";
	    String dbPassword = "root";
	  
	    String manager=req.getParameter("manager");
	    
	    if(manager.equals("Admin")) {
	    	try {
	    		  String query="SELECT * FROM Attendance";
	    		Class.forName("org.postgresql.Driver");
	    		Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
	    		PreparedStatement pst=con.prepareStatement(query);
	    		ResultSet rs=pst.executeQuery();
	    		while(rs.next()) {
	    			String name=rs.getString("name");
	    			String ci=rs.getString("checkin");
	    			String co=rs.getString("checkout");
	    			String m=rs.getString("manager");
	    			AttendanceList.add((new Attendance(name,ci,co,m)));
	    		}
	    		   Gson gson = new Gson();
	               String json = gson.toJson(AttendanceList);
	               res.setContentType("application/json");
	                res.getWriter().write(json);
	           
	    	}catch (Exception e) {
	    	
	    		System.out.print("some error");
	    	}
	    	
	    		
	    	
	    }
	    else {  
	try {
		  String query="SELECT * FROM Attendance where manager=?";
		Class.forName("org.postgresql.Driver");
		Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, manager);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			String name=rs.getString("name");
			String ci=rs.getString("checkin");
			String co=rs.getString("checkout");
			String m=rs.getString("manager");
			AttendanceList.add((new Attendance(name,ci,co,m)));
		}
		   Gson gson = new Gson();
           String json = gson.toJson(AttendanceList);
           res.setContentType("application/json");
            res.getWriter().write(json);
       
	}catch (Exception e) {
	
		System.out.print("some error");
	}
	
	    }
		
	}

}
