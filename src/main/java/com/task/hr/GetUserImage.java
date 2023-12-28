package com.task.hr;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getUserImage")
public class GetUserImage extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("name");

        String url = "jdbc:postgresql://localhost:5432/HR";
        String dbUsername = "postgres";
        String dbPassword = "root";
        String query = "SELECT user_image FROM Employees WHERE name = ?";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                
                response.setContentType("image/jpeg");

                byte[] imageData = rs.getBytes("user_image");

                try (OutputStream outputStream = response.getOutputStream()) {
                    outputStream.write(imageData);
                }
            } else {
               
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
