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

@WebServlet("/forget")
public class UpdatePassword extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String url = "jdbc:postgresql://localhost:5432/HR";
        String dbUsername = "postgres";
        String dbPassword = "root";
        String query = "UPDATE Employees SET password=? WHERE name=?";

        String name = req.getParameter("name");
        String password = req.getParameter("password");
       

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, password);
            pst.setString(2, name);
           
            System.out.println(name);
            System.out.println(password);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
               
                res.setContentType("text/html");
                res.getWriter().println("success");
            } else {
                
                res.setContentType("text/html");
                res.getWriter().println("failed");
            }

        } catch (Exception e) {
            e.printStackTrace();

            
            res.setContentType("text/html");
            res.getWriter().write("error");
        }
    }
}
