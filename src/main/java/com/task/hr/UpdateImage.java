package com.task.hr;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.servlet.http.Part;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.MultipartConfigElement;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateimage")
@MultipartConfig
public class UpdateImage extends HttpServlet {
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		
	
		 	String url = "jdbc:postgresql://localhost:5432/HR";
		    String dbUsername = "postgres";
		    String dbPassword = "root";
		    String query="UPDATE Employees SET user_image=? WHERE name=?";
		    String name=req.getParameter("name");
	        Part filePart = req.getPart("file");
	        byte[] imageData = getByteArrayFromInputStream(filePart.getInputStream());
			System.out.println(name);
		
			
			try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
			PreparedStatement pst=con.prepareStatement(query);
			pst.setBytes(1, imageData);
			pst.setString(2, name);
			
			pst.executeUpdate();
			System.out.println("Successfully added");
			res.getWriter().println("success");

			con.close();
	       
		}catch (Exception e) {
			// TODO: handle exception
			System.out.print("some error");
		}
		
		
	}

	 private byte[] getByteArrayFromInputStream(InputStream inputStream) throws IOException {
		 try {
             byte[] buffer = new byte[inputStream.available()];
             inputStream.read(buffer);
             return buffer;
         } finally {
             inputStream.close();
         }
	}


}
