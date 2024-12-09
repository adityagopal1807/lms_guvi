package com.lms;

import com.lms.dao.DBConnection;
import com.lms.dao.UserDAO;
import com.lms.model.User;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);

            // Add a sample user
            User user = new User();
            user.setName("John Doe");
            user.setEmail("john.doe@example.com");
            user.setRole("student");
            user.setPassword("password123");
            userDAO.addUser(user);

            // Fetch and display all users
            System.out.println("Users in the system:");
            userDAO.getAllUsers().forEach(u -> {
                System.out.println("ID: " + u.getId() + ", Name: " + u.getName() + ", Role: " + u.getRole());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
