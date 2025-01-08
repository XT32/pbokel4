package model;

import java.time.LocalDateTime;

public class Pembelian {
    private int idPembelian; // ID Transaksi pembelian
    private int idIkan; // ID Ikan yang dibeli
    private int idUser; // ID User/pembeli (bukan idNelayan)
    private int jumlahBeli; // Jumlah item yang dibeli
    private double hargaTotal; // Total harga pembelian
    private LocalDateTime tanggalPembelian; // Tanggal pembelian

    // Konstruktor utama
    public Pembelian(int idPembelian, String string, int idIkan, double hargaTotal, LocalDateTime tanggalPembelian) {
        this.idPembelian = idPembelian;
        this.idIkan = idIkan;
        this.idUser = idUser;
        this.jumlahBeli = jumlahBeli;
        this.hargaTotal = hargaTotal;
        this.tanggalPembelian = tanggalPembelian;
    }

    // Getter dan Setter
    public int getIdPembelian() {
        return idPembelian;
    }

    public void setIdPembelian(int idPembelian) {
        this.idPembelian = idPembelian;
    }

    public int getIdIkan() {
        return idIkan;
    }

    public void setIdIkan(int idIkan) {
        this.idIkan = idIkan;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getJumlahBeli() {
        return jumlahBeli;
    }

    public void setJumlahBeli(int jumlahBeli) {
        this.jumlahBeli = jumlahBeli;
    }

    public double getHargaTotal() {
        return hargaTotal;
    }

    public void setHargaTotal(double hargaTotal) {
        this.hargaTotal = hargaTotal;
    }

    public LocalDateTime getTanggalPembelian() {
        return tanggalPembelian;
    }

    public void setTanggalPembelian(LocalDateTime tanggalPembelian) {
        this.tanggalPembelian = tanggalPembelian;
    }

    // Overriding toString() untuk debugging mudah
    @Override
    public String toString() {
        return "Pembelian{" +
                "idPembelian=" + idPembelian +
                ", idIkan=" + idIkan +
                ", idUser=" + idUser +
                ", jumlahBeli=" + jumlahBeli +
                ", hargaTotal=" + hargaTotal +
                ", tanggalPembelian=" + tanggalPembelian +
                '}';
    }
}
