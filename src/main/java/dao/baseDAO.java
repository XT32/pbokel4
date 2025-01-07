package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class baseDAO {

    private static final String URL = "http://34.44.81.201/phpmyadmin/index.php?route=/database/sql&db=fishmarket";
    private static final String USER = "xt";
    private static final String PASSWORD = "adminxt";

    public static Connection connectDB() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi berhasil!");
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
        return connect;
    }
}
