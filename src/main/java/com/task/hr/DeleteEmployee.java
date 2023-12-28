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
@WebServlet("/deleteemployee")
public class DeleteEmployee extends HttpServlet {

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String url = "jdbc:postgresql://localhost:5432/HR";
        String dbUsername = "postgres";
        String dbPassword = "root";
        String query = "DELETE FROM Employees WHERE name=?";

        String tname = req.getParameter("name");

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, tname);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                res.setContentType("application/json");
                res.getWriter().write("{\"status\": \"success\"}");
            } else {
                res.setContentType("application/json");
                res.getWriter().write("{\"status\": \"failed\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.setContentType("application/json");
            res.getWriter().write("{\"status\": \"error\"}");
        }
    }
}
