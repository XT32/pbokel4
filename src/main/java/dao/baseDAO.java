package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alich
 */
public class baseDAO {

    private static final String URL = "jdbc:mysql://192.168.1.6:3307/fishmarket";
    private static final String USER = "root";
    private static final String PASSWORD = "adminxt";

    // Method untuk mendapatkan koneksi
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
