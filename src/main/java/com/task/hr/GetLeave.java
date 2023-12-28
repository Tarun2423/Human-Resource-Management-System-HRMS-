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
import com.task.model.Leave;

@WebServlet("/getLeave")
public class GetLeave extends HttpServlet {
	
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		
		ArrayList<Leave> leaveList=new ArrayList<Leave>();
		 	String url = "jdbc:postgresql://localhost:5432/HR";
		    String dbUsername = "postgres";
		    String dbPassword = "root";
		    String managername=req.getParameter("manager");
		    String query="SELECT * FROM Leave where manager=?";
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, managername);
			System.out.println(managername);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {	
				String name=rs.getString("name");
				String reason=rs.getString("reason");
				String dates=rs.getString("dates");
				String status=rs.getString("status");
				String manager=rs.getString("manager");
				leaveList.add((new Leave(name,reason,dates,status,manager)));
			}
			   Gson gson = new Gson();
	           String json = gson.toJson(leaveList);
	           res.setContentType("application/json");
	            res.getWriter().write(json);
	       
		}catch (Exception e) {
			// TODO: handle exception
			System.out.print("some error");
		}
		
		
	}

}
