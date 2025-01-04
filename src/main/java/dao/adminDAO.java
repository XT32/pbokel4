package dao;

import model.Ikan;
import model.Nelayan;
import model.Pembelian;
import model.Pesanan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private final Connection connection;

    public AdminDAO(Connection connection) {
        this.connection = connection;
    }

    // ------------------ CRUD IKAN ------------------

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

    public void deleteIkan(int idIkan) throws SQLException {
        String query = "DELETE FROM ikan WHERE id_ikan = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idIkan);
            stmt.executeUpdate();
        }
    }

    // ------------------ CRUD NELAYAN ------------------

    public List<Nelayan> getAllNelayan() throws SQLException {
        List<Nelayan> nelayanList = new ArrayList<>();
        String query = "SELECT * FROM nelayan";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                nelayanList.add(new Nelayan(
                        rs.getInt("id_nelayan"),
                        rs.getString("nama_nelayan"),
                        rs.getString("nomor_telepon")
                ));
            }
        }
        return nelayanList;
    }

    public void addNelayan(Nelayan nelayan) throws SQLException {
        String query = "INSERT INTO nelayan (nama_nelayan, nomor_telepon) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nelayan.getNamaNelayan());
            stmt.setString(2, nelayan.getNomorTelepon());
            stmt.executeUpdate();
        }
    }

    public void updateNelayan(Nelayan nelayan) throws SQLException {
        String query = "UPDATE nelayan SET nama_nelayan = ?, nomor_telepon = ? WHERE id_nelayan = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nelayan.getNamaNelayan());
            stmt.setString(2, nelayan.getNomorTelepon());
            stmt.setInt(3, nelayan.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteNelayan(int idNelayan) throws SQLException {
        String query = "DELETE FROM nelayan WHERE id_nelayan = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idNelayan);
            stmt.executeUpdate();
        }
    }

    // ------------------ CRUD PEMBELIAN ------------------

    public List<Pembelian> getAllPembelian() throws SQLException {
        List<Pembelian> pembelianList = new ArrayList<>();
        String query = "SELECT * FROM pembelian";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                pembelianList.add(new Pembelian(
                        rs.getInt("id_pembelian"),
                        rs.getInt("id_ikan"),
                        rs.getInt("id_nelayan"),
                        rs.getInt("jumlah_beli"),
                        rs.getDouble("harga_total"),
                        rs.getTimestamp("tanggal_pembelian").toLocalDateTime()
                ));
            }
        }
        return pembelianList;
    }

    public void addPembelian(Pembelian pembelian) throws SQLException {
        String query = "INSERT INTO pembelian (id_ikan, id_nelayan, jumlah_beli, harga_total, tanggal_pembelian) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, pembelian.getIdIkan());
            stmt.setInt(2, pembelian.getIdNelayan());
            stmt.setInt(3, pembelian.getJumlahBeli());
            stmt.setDouble(4, pembelian.getHargaTotal());
            stmt.setTimestamp(5, Timestamp.valueOf(pembelian.getTanggalPembelian()));
            stmt.executeUpdate();
        }
    }

    public void deletePembelian(int idPembelian) throws SQLException {
        String query = "DELETE FROM pembelian WHERE id_pembelian = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idPembelian);
            stmt.executeUpdate();
        }
    }

    // ------------------ CRUD PESANAN ------------------

    public List<Pesanan> getAllPesanan() throws SQLException {
        List<Pesanan> pesananList = new ArrayList<>();
        String query = "SELECT * FROM pesanan";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                pesananList.add(new Pesanan(
                        rs.getInt("id_order"),
                        rs.getInt("id_user"),
                        rs.getDouble("total_pembelian"),
                        rs.getTimestamp("tanggal_transaksi").toLocalDateTime(),
                        rs.getString("status")
                ));
            }
        }
        return pesananList;
    }

    public void updatePesananStatus(int idOrder, String status) throws SQLException {
        String query = "UPDATE pesanan SET status = ? WHERE id_order = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, idOrder);
            stmt.executeUpdate();
        }
    }
}
