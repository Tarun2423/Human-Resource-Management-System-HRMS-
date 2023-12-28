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

@WebServlet("/updateleavestatus")
public class UpdateLeaveStatus extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String url = "jdbc:postgresql://localhost:5432/HR";
        String dbUsername = "postgres";
        String dbPassword = "root";
     

        String name = req.getParameter("name");
        String reason = req.getParameter("reason");
        String dates = req.getParameter("dates");
        String status = req.getParameter("status"); 
        String user=req.getParameter("user");
        
               if(status.equals("Approved")&& user.equals("Admin")==false ) {
            	 
               	String query="UPDATE Leave Set manager=? where name=?";
               	try {
                Class.forName("org.postgresql.Driver");
                Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, "Admin");
                pst.setString(2, name);
                
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                	 String logMessage = String.format("Leave added - Name: %s, Reason: %s, dates: %s, Status: %s",
                             name, reason, dates, status);
                	String logFilePath = "C:\\Users\\ADMIN\\eclipse-workspace\\hr\\logs\\log.txt";
                    Logger.logToFile(logFilePath, logMessage);
                    res.setContentType("application/json");
                    res.getWriter().write("{\"message\": \"Leave status updated successfully\"}");
                } else {
                    res.setContentType("application/json");
                    res.getWriter().write("{\"error\": \"No matching leave record found\"}");
                }

            } catch (Exception e) {
                e.printStackTrace();
                res.setContentType("application/json");
                res.getWriter().write("{\"error\": \"Error occurred while updating leave status\"}");
            }
               }
               else {
               	 String query1 = "UPDATE Leave SET status=? WHERE name=? AND reason=? AND dates=?";
               	 	try {
                 Class.forName("org.postgresql.Driver");
                 Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
                 PreparedStatement pst = con.prepareStatement(query1);
                 pst.setString(1, status);
                 pst.setString(2, name);
                 pst.setString(3, reason);
                 pst.setString(4, dates);
                 
                 int rowsAffected = pst.executeUpdate();

                 if (rowsAffected > 0) {
                	 String logMessage = String.format("Leave added - Name: %s, Reason: %s, dates: %s, Status: %s",
                             name, reason, dates, status);
                     String logFilePath = "C:\\Users\\ADMIN\\eclipse-workspace\\hr\\logs\\log.txt";
                     Logger.logToFile(logFilePath, logMessage);
                     res.setContentType("application/json");
                     res.getWriter().write("{\"message\": \"Leave status updated successfully\"}");
                 } else {
                     
                     res.setContentType("application/json");
                     res.getWriter().write("{\"error\": \"No matching leave record found\"}");
                 }

             } catch (Exception e) {
                 e.printStackTrace();
                 res.setContentType("application/json");
                 res.getWriter().write("{\"error\": \"Error occurred while updating leave status\"}");
             }
               }

    }
}
