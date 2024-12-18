package dao;

import model.User;
import java.sql.*;

public class UserDAO {

    public boolean registerUser(User user) {
        String query = "INSERT INTO users (nama_lengkap, username, alamat, email, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = baseDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Mengisi parameter query
            stmt.setString(1, user.getNamaLengkap());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getAlamat());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            return stmt.executeUpdate() > 0; // Return true jika data berhasil disimpan
        } catch (SQLException e) {
            System.err.println("Error during user registration: " + e.getMessage());
            return false; // Return false jika terjadi kesalahan
        }
    }

    // Method untuk login user
    public User loginUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = baseDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Mengisi parameter query
            stmt.setString(1, username);
            stmt.setString(2, password); // Password langsung dibandingkan
            ResultSet rs = stmt.executeQuery();

            // Jika username dan password cocok, buat objek User
            if (rs.next()) {
                return new User(
                        rs.getString("nama_lengkap"),
                        rs.getString("username"),
                        rs.getString("alamat"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error during user login: " + e.getMessage());
        }
        return null; // Return null jika login gagal
    }

    public boolean addUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
