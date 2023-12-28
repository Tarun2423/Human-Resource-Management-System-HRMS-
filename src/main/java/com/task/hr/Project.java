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
import com.task.model.Employee;
import com.task.model.ProjectModel;


@WebServlet("/addProject")
public class Project extends HttpServlet  {
	
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		
		String ename=req.getParameter("ename");
		String pname=req.getParameter("pname");
		String pdesc=req.getParameter("pdesc");
		String url = "jdbc:postgresql://localhost:5432/HR";
	    String dbUsername = "postgres";
	    String dbPassword = "root";
	    String query="INSERT INTO Project (name,projectname,projectdesc) VALUES(?,?,?)";
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, ename);
			pst.setString(2, pname);
			pst.setString(3, pdesc);
			pst.executeUpdate();
			System.out.println("Successfully added");
			res.getWriter().println("success");
			con.close();
		} catch (Exception e) {
			System.out.println("something wrong");
			
			e.printStackTrace();
		}
		
		
	}


}
