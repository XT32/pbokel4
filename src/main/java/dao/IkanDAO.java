package dao;

import model.Ikan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IkanDAO {
    private Connection connection;

    public IkanDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Ikan> getAllIkan() throws SQLException {
        List<Ikan> ikanList = new ArrayList<>();
        String query = "SELECT * FROM ikan";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
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

    public void addIkan(Ikan ikan) throws SQLException {
        String query = "INSERT INTO ikan (nama_ikan, harga, gambar_ikan, stok, id_nelayan) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ikan.getNama());
            stmt.setDouble(2, ikan.getHarga());
            stmt.setString(3, ikan.getGambarIkan());
            stmt.setInt(4, ikan.getStok());
            stmt.setInt(5, ikan.getIdNelayan());
            stmt.executeUpdate();
        }
    }

    public void updateIkan(Ikan ikan) throws SQLException {
        String query = "UPDATE ikan SET nama_ikan = ?, harga = ?, gambar_ikan = ?, stok = ?, id_nelayan = ? WHERE id_ikan = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ikan.getNama());
            stmt.setDouble(2, ikan.getHarga());
            stmt.setString(3, ikan.getGambarIkan());
            stmt.setInt(4, ikan.getStok());
            stmt.setInt(5, ikan.getIdNelayan());
            stmt.setInt(6, ikan.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteIkan(int id) throws SQLException {
        String query = "DELETE FROM ikan WHERE id_ikan = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
