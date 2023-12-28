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

@WebServlet("/addMessage")
public class AddMessages extends HttpServlet {
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		

		 	String url = "jdbc:postgresql://localhost:5432/HR";
		    String dbUsername = "postgres";
		    String dbPassword = "root";
		    String query="INSERT INTO Message(fromname,toname,message) VALUES(?,?,?)";
		    String fname=req.getParameter("fname");
		    String tname=req.getParameter("tname");
		    String msg=req.getParameter("msg");
		  
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, fname);
			pst.setString(2, tname);
			pst.setString(3, msg);
			pst.executeUpdate();
			System.out.println("Successfully added");
			res.getWriter().println("success");
			con.close();
	       
		}catch (Exception e) {
			// TODO: handle exception
			System.out.print("some error");
		}
		
		
	}

}
