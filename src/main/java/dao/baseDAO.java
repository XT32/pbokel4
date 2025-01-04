package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alich
 */
public class baseDAO {

    private static final String URL = "jdbc:mysql://localhost:3307/fishmarket";
    private static final String USER = "root";
    private static final String PASSWORD = "adminxt";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            return null;
        }
    }
}
