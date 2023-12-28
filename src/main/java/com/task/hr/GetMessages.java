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
import com.task.model.Message;

@WebServlet("/getmessages")
public class GetMessages extends HttpServlet {
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		
	
		ArrayList<Message> MessageList=new ArrayList<Message>();
		 	String url = "jdbc:postgresql://localhost:5432/HR";
		    String dbUsername = "postgres";
		    String dbPassword = "root";
		    String query="SELECT * FROM Message where toname=?";
		    String tname=req.getParameter("tname");
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, tname);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				String fname=rs.getString("fromname");
				String toname=rs.getString("toname");
				String message=rs.getString("message");
				
				
				MessageList.add((new Message(fname,toname,message)));
			}
			   Gson gson = new Gson();
	           String json = gson.toJson(MessageList);
	           res.setContentType("application/json");
	            res.getWriter().write(json);
	       
		}catch (Exception e) {
			// TODO: handle exception
			System.out.print("some error");
		}
		
		
	}

}
