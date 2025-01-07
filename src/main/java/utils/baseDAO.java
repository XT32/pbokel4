package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections.
 *
 * @author alich
 */
public class baseDAO {

    // Database credentials
    private static final String JDBC_URL = "jdbc:mysql://34.44.81.201:3306/fishmarket";
    private static final String USERNAME = "xt";
    private static final String PASSWORD = "adminxt";

    /**
     * Establishes and returns a database connection.
     *
     * @return Connection object if the connection is successful
     * @throws SQLException if a database access error occurs
     */
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    /**
     * Main method for testing the database connection.
     *
     * @param args Command-line arguments
     */
    
    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            System.out.println("Database connection successful!");
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
        }
    }
}
