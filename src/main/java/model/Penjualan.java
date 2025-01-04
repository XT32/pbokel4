package model;

import javafx.beans.property.*;

public class Penjualan {
    private final IntegerProperty idTransaksi = new SimpleIntegerProperty();
    private final IntegerProperty idUser = new SimpleIntegerProperty();
    private final IntegerProperty idIkan = new SimpleIntegerProperty();
    private final IntegerProperty kuantitas = new SimpleIntegerProperty();
    private final StringProperty tanggal = new SimpleStringProperty();
    private final StringProperty alamat = new SimpleStringProperty();
    private final DoubleProperty total = new SimpleDoubleProperty();

    // Constructor
    public Penjualan(int idTransaksi, int idUser, int idIkan, int kuantitas, String tanggal, String alamat, double total) {
        this.idTransaksi.set(idTransaksi);
        this.idUser.set(idUser);
        this.idIkan.set(idIkan);
        this.kuantitas.set(kuantitas);
        this.tanggal.set(tanggal);
        this.alamat.set(alamat);
        this.total.set(total);
    }

    // Getters
    public int getIdTransaksi() {
        return idTransaksi.get();
    }

    public IntegerProperty idTransaksiProperty() {
        return idTransaksi;
    }

    public int getIdUser() {
        return idUser.get();
    }

    public IntegerProperty idUserProperty() {
        return idUser;
    }

    public int getIdIkan() {
        return idIkan.get();
    }

    public IntegerProperty idIkanProperty() {
        return idIkan;
    }

    public int getKuantitas() {
        return kuantitas.get();
    }

    public IntegerProperty kuantitasProperty() {
        return kuantitas;
    }

    public String getTanggal() {
        return tanggal.get();
    }

    public StringProperty tanggalProperty() {
        return tanggal;
    }

    public String getAlamat() {
        return alamat.get();
    }

    public StringProperty alamatProperty() {
        return alamat;
    }

    public double getTotal() {
        return total.get();
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    // Setters
    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi.set(idTransaksi);
    }

    public void setIdUser(int idUser) {
        this.idUser.set(idUser);
    }

    public void setIdIkan(int idIkan) {
        this.idIkan.set(idIkan);
    }

    public void setKuantitas(int kuantitas) {
        this.kuantitas.set(kuantitas);
    }

    public void setTanggal(String tanggal) {
        this.tanggal.set(tanggal);
    }

    public void setAlamat(String alamat) {
        this.alamat.set(alamat);
    }

    public void setTotal(double total) {
        this.total.set(total);
    }
}
