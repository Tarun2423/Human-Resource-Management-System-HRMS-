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
@WebServlet("/updateEmployee")
public class UpdateEmployee extends HttpServlet {

    private static final String URL = "jdbc:postgresql://localhost:5432/HR";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "root";
    private static final String UPDATE_QUERY = "UPDATE Employees SET dob=?, role=?, phoneno=?, joining=? WHERE name=?";

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String dob = req.getParameter("dob");
        String role = req.getParameter("role");
        String phone = req.getParameter("phone");
        String joining = req.getParameter("joining");
        String name = req.getParameter("name");
        System.out.println(name);
        try (Connection con = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pst = con.prepareStatement(UPDATE_QUERY)) {

            pst.setString(1, dob);
            pst.setString(2, role);
            pst.setString(3, phone);
            pst.setString(4, joining);
            pst.setString(5, name);

            int rowsAffected = pst.executeUpdate();

            res.setContentType("application/json");
            if (rowsAffected > 0) {
                res.getWriter().write("{\"message\": \"Employee information updated successfully\"}");
            } else {
                res.getWriter().write("{\"message\": \"No matching employee record found\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();

            res.setContentType("application/json");
            res.getWriter().write("{\"error\": \"Error occurred while updating employee information\"}");
        }
    }
}
