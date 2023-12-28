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
@WebServlet("/getImage")
public class GetImage extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String name = req.getParameter("name");

        byte[] imageData = getImageDataFromDatabase(name);

        if (imageData != null) {
            res.setContentType("image/jpeg");
            try (OutputStream out = res.getOutputStream()) {
                out.write(imageData);
            }
        } else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
          
        }
    }

    private byte[] getImageDataFromDatabase(String name) {
        byte[] imageData = null;
        String url = "jdbc:postgresql://localhost:5432/HR";
        String dbUsername = "postgres";
        String dbPassword = "root";
        String query = "SELECT user_image FROM Employees WHERE name=?";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, name);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            imageData = resultSet.getBytes("user_image");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
          
        }

        return imageData;
    }
}
