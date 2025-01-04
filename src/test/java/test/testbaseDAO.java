package test;

import utils.baseDAO;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class to test database connection.
 */
public class testbaseDAO {

    public static void main(String[] args) {
        // Test database connection
        try (Connection connection = baseDAO.getConnection()) {
            System.out.println("Database connection test successful!");
        } catch (SQLException e) {
            System.err.println("Database connection test failed!");
            e.printStackTrace();
        }
    }
}
