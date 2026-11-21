package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DBConnection class - manages connection to the MySQL database
public class DBConnection {
    // Database credentials and connection URL
    private static final String URL = "jdbc:mysql://localhost:3306/sports_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root123";

    // Method to create and return a database connection
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Register MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open connection to the database
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: MySQL JDBC Driver not found! Please check the lib/ folder for mysql connector jar.");
        } catch (SQLException e) {
            System.out.println("Error: Failed to connect to MySQL database!");
            System.out.println("Message: " + e.getMessage());
        }
        return conn;
    }
}
