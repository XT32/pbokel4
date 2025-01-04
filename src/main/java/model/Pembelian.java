package model;

import java.time.LocalDateTime;

public class Pembelian {
    private int idPembelian;
    private int idIkan;
    private int idNelayan;
    private int jumlahBeli;
    private double hargaTotal;
    private LocalDateTime tanggalPembelian;

    public Pembelian(int idPembelian, int idIkan, int idNelayan, int jumlahBeli, double hargaTotal, LocalDateTime tanggalPembelian) {
        this.idPembelian = idPembelian;
        this.idIkan = idIkan;
        this.idNelayan = idNelayan;
        this.jumlahBeli = jumlahBeli;
        this.hargaTotal = hargaTotal;
        this.tanggalPembelian = tanggalPembelian;
    }

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

    public int getIdNelayan() {
        return idNelayan;
    }

    public void setIdNelayan(int idNelayan) {
        this.idNelayan = idNelayan;
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
}
