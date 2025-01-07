package test;

import utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class to test database connection.
 */
public class testDatabaseConnection {

    public static void main(String[] args) {
        // Test database connection
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Database connection test successful!");
        } catch (SQLException e) {
            System.err.println("Database connection test failed!");
            e.printStackTrace();
        }
    }
}
