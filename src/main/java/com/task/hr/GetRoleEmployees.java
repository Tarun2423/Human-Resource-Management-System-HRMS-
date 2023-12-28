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

@WebServlet("/getEmpr")
public class GetRoleEmployees extends HttpServlet {
	
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		
		ArrayList<Employee> employeesList=new ArrayList<Employee>();
		 	String url = "jdbc:postgresql://localhost:5432/HR";
		    String dbUsername = "postgres";
		    String dbPassword = "root";
		    String query="SELECT * FROM Employees where manager=?";
		    String manager=req.getParameter("name");
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, manager);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("empId");
				String name=rs.getString("name");
				String role=rs.getString("role");
				String j=rs.getString("joining");
				String dob=rs.getString("dob");
				String email=rs.getString("email");
				String ph=rs.getString("phoneno");
				String m=rs.getString("manager");
				employeesList.add((new Employee(id,name,role,j,dob,ph,email,m)));
			}
			   Gson gson = new Gson();
	           String json = gson.toJson(employeesList);
	           res.setContentType("application/json");
	            res.getWriter().write(json);
	       
		}catch (Exception e) {
			// TODO: handle exception
			System.out.print("some error");
		}
		
		
	}

}
