package dao;

import model.Ikan;
import model.Nelayan;
import model.Pembelian;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private final Connection connection;

    public AdminDAO(Connection connection) {
        this.connection = connection;
    }

    // Ambil semua data ikan
    public List<Ikan> getAllIkan() throws SQLException {
        String query = "SELECT * FROM ikan";
        List<Ikan> ikanList = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
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

    // Ambil semua data penjualan
    public List<Pembelian> getAllPenjualan() throws SQLException {
        String query = """
            SELECT p.id_order AS id_penjualan, 
                   k.id_ikan, 
                   u.id_user AS id_pelanggan, 
                   k.jumlah AS kuantitas, 
                   p.total_pembelian AS harga_total, 
                   p.tanggal_transaksi AS tanggal_penjualan
            FROM pesanan p
            JOIN keranjang k ON p.id_keranjang = k.id_keranjang
            JOIN users u ON p.id_user = u.id_user
        """;
        List<Pembelian> penjualanList = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                penjualanList.add(new Pembelian(
                        rs.getInt("id_penjualan"),
                        rs.getInt("id_ikan"),
                        rs.getInt("id_pelanggan"),
                        rs.getInt("kuantitas"),
                        rs.getDouble("harga_total"),
                        rs.getTimestamp("tanggal_penjualan").toLocalDateTime()
                ));
            }
        }
        return penjualanList;
    }

    // Ambil semua data pembelian
    public List<Pembelian> getAllPembelian() throws SQLException {
        String query = """
            SELECT id_pembelian, 
                   id_ikan, 
                   id_nelayan AS id_supplier, 
                   jumlah_beli AS kuantitas, 
                   harga_total, 
                   tanggal_pembelian
            FROM pembelian
        """;
        List<Pembelian> pembelianList = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                pembelianList.add(new Pembelian(
                        rs.getInt("id_pembelian"),
                        rs.getInt("id_ikan"),
                        rs.getInt("id_supplier"),
                        rs.getInt("kuantitas"),
                        rs.getDouble("harga_total"),
                        rs.getTimestamp("tanggal_pembelian").toLocalDateTime()
                ));
            }
        }
        return pembelianList;
    }

    // Ambil semua data pelanggan
    public List<Nelayan> getAllPelanggan() throws SQLException {
        String query = """
            SELECT id_user AS id_pelanggan, 
                   nama_lengkap AS nama_pelanggan, 
                   nomor_telepon AS kontak
            FROM users
        """;
        List<Nelayan> pelangganList = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                pelangganList.add(new Nelayan(
                        rs.getInt("id_pelanggan"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("kontak")
                ));
            }
        }
        return pelangganList;
    }
}
