package model;

import java.time.LocalDateTime;

public class Pesanan {
    private int idOrder;
    private int idUser;
    private double totalPembelian;
    private LocalDateTime tanggalTransaksi;
    private String status;

    public Pesanan(int idOrder, int idUser, double totalPembelian, LocalDateTime tanggalTransaksi, String status) {
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.totalPembelian = totalPembelian;
        this.tanggalTransaksi = tanggalTransaksi;
        this.status = status;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getTotalPembelian() {
        return totalPembelian;
    }

    public void setTotalPembelian(double totalPembelian) {
        this.totalPembelian = totalPembelian;
    }

    public LocalDateTime getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(LocalDateTime tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
