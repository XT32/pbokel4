package dao;

import model.User;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void registerUser(User user) throws SQLException {
        String query = "INSERT INTO users (nama_lengkap, username, email, password, alamat) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.connectDB();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getNamaLengkap());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getAlamat());
            stmt.executeUpdate();
        }
    }

    public User loginUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseConnection.connectDB();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id_user"),
                        rs.getString("nama_lengkap"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("alamat")
                );
            }
        }
        return null;
    }

    public boolean isEmailExists(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean isUsernameExists(String username) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
