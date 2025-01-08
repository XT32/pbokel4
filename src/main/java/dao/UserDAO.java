package dao;

import model.Ikan;
import model.Pembelian;
import model.User;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Login pengguna
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
        return null; // Jika login gagal
    }

    // Registrasi pengguna baru
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

    // Periksa apakah username sudah ada
    public boolean isUsernameExists(String username) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM users WHERE username = ?";
        try (Connection connection = DatabaseConnection.connectDB();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        }
        return false;
    }

    // Periksa apakah email sudah ada
    public boolean isEmailExists(String email) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM users WHERE email = ?";
        try (Connection connection = DatabaseConnection.connectDB();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        }
        return false;
    }

    // Update profil pengguna
    public void updateUserProfile(User user) throws SQLException {
        String query = "UPDATE users SET email = ?, alamat = ? WHERE id_user = ?";
        try (Connection connection = DatabaseConnection.connectDB();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getAlamat());
            stmt.setInt(3, user.getIdUser());
            stmt.executeUpdate();
        }
    }

    // Ambil semua data ikan dari database
    public List<Ikan> getAllIkan() throws SQLException {
        String query = "SELECT * FROM ikan";
        List<Ikan> ikanList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.connectDB();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                ikanList.add(new Ikan(
                        rs.getInt("id_ikan"),
                        rs.getString("nama_ikan"),
                        rs.getDouble("harga"),
                        rs.getString("gambar_ikan"),
                        rs.getInt("stok"),
                        rs.getInt("id_nelayan")
                ));
            }
        }
        return ikanList;
    }

    // Ambil riwayat pembelian pengguna berdasarkan id_user
    public List<Pembelian> getPurchaseHistory(int userId) throws SQLException {
        String query = """
                SELECT p.id_pembelian, 
                       i.nama_ikan, 
                       p.jumlah, 
                       p.total_harga, 
                       p.tanggal_pembelian
                FROM pembelian p
                JOIN ikan i ON p.id_ikan = i.id_ikan
                WHERE p.id_user = ?
                ORDER BY p.tanggal_pembelian DESC
                """;
        List<Pembelian> history = new ArrayList<>();
        try (Connection connection = DatabaseConnection.connectDB();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                history.add(new Pembelian(
                        rs.getInt("id_pembelian"),
                        rs.getString("nama_ikan"),
                        rs.getInt("jumlah"),
                        rs.getDouble("total_harga"),
                        rs.getTimestamp("tanggal_pembelian").toLocalDateTime()
                ));
            }
        }
        return history;
    }
}
