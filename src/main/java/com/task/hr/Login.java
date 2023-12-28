package com.task.hr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.*;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet {
	
	private static final String secretKey = "0123456789012345678901234567890123456789012345678901234567890123";
	public String getSecretKey() {
		
		return secretKey;
	}
	
	public static String generateJwtToken(String name) {
		
        
        try {
			JWSSigner signer = new MACSigner(secretKey);
			JWTClaimsSet claimsSet=new JWTClaimsSet.Builder().subject(name).issuer("Admin").expirationTime(new Date(new Date().getTime()+3600000)).build();
			 SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
			 signedJWT.sign(signer);
			 String jwtString = signedJWT.serialize();
		     System.out.println("JWT: " + jwtString);
		     return jwtString;
		} catch (Exception e) {
		
			e.printStackTrace();
			return "error";
		}
        
		

	}
	private void sendTokenAsCookie(HttpServletResponse response, String token) {
  
         Cookie cookie = new Cookie("jwtToken", token);
       
         cookie.setHttpOnly(true);
         response.addCookie(cookie);
    }
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
		
		   res.setHeader("Access-Control-Allow-Origin", "*");
		    res.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		   
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
		String username=req.getParameter("name");
		String password=req.getParameter("password");
		
		
		try {
		    Class.forName("org.postgresql.Driver");
		    String url = "jdbc:postgresql://localhost:5432/HR";
		    String dbUsername = "postgres";
		    String dbPassword = "root";
		    
		    try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword)) {
		        String query = "SELECT role FROM Employees WHERE name=? AND password=?";
		        
		        try (PreparedStatement pst = con.prepareStatement(query)) {
		            pst.setString(1, username);
		            pst.setString(2, password);
		            
		            try (ResultSet rs = pst.executeQuery()) {
		                if (rs.next()) {
		                	String role=rs.getString("role");
		                    String token = generateJwtToken(username);
		                    sendTokenAsCookie(res, token);
		                   System.out.println(token);
		                    out.println(role);
		                } else {
		                    out.println("Invalid credentials");
		                }
		            }
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    out.println("Error from DB");
		}
	

		
		
		
	
		
		
	}
	
}
