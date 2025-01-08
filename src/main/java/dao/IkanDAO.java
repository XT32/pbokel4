package dao;

import model.Ikan;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IkanDAO {

    public void addIkan(Ikan ikan) throws SQLException {
    String query = "INSERT INTO ikan (nama_ikan, harga, gambar_ikan, stok, id_nelayan) VALUES (?, ?, ?, ?, ?)";
    try (Connection connection = DatabaseConnection.connectDB();
         PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, ikan.getNamaIkan());
        stmt.setDouble(2, ikan.getHarga());
        stmt.setString(3, ikan.getGambarIkan());
        stmt.setInt(4, ikan.getStok());
        stmt.setInt(5, ikan.getIdNelayan());
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Failed to add fish data: " + e.getMessage());
        throw e;
    }
}


    public List<Ikan> getAllIkan() throws SQLException {
        String query = "SELECT * FROM ikan";
        List<Ikan> ikanList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.connectDB();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Ikan ikan = new Ikan(
                        rs.getInt("id_ikan"),
                        rs.getString("nama_ikan"),
                        rs.getDouble("harga"),
                        rs.getString("gambar_ikan"),
                        rs.getInt("stok"),
                        rs.getInt("id_nelayan")
                );
                ikanList.add(ikan);
            }
        }
        return ikanList;
    }

    public void deleteIkan(int idIkan) throws SQLException {
        String query = "DELETE FROM ikan WHERE id_ikan = ?";
        try (Connection connection = DatabaseConnection.connectDB();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idIkan);
            stmt.executeUpdate();
        }
    }

    public void updateIkan(Ikan ikan) throws SQLException {
        String query = "UPDATE ikan SET nama_ikan = ?, harga = ?, gambar_ikan = ?, stok = ?, id_nelayan = ? WHERE id_ikan = ?";
        try (Connection connection = DatabaseConnection.connectDB();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ikan.getNamaIkan());
            stmt.setDouble(2, ikan.getHarga());
            stmt.setString(3, ikan.getGambarIkan());
            stmt.setInt(4, ikan.getStok());
            stmt.setInt(5, ikan.getIdNelayan());
            stmt.setInt(6, ikan.getIdIkan());
            stmt.executeUpdate();
        }
    }
}
