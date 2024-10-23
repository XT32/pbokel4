package com.mycompany.seasell.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {

    protected static final String DB_NAME = "seasell";
    protected static final String DB_HOST = "127.0.0.1";
    protected static final String DB_USER = "root";
    protected static final String DB_PASS = "adminxt32";
    protected static Connection conn;  // Use this instead of redeclaring locally

    public static Connection getCon() {
        try {
            // Initialize connection only if it's not already established
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":3306/" + DB_NAME, DB_USER, DB_PASS);
                System.out.println("Connection established successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to connect to database: " + e.getMessage());
        }
        return conn;
    }

    public static void closeCon() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to close connection: " + e.getMessage());
        }
    }
}
