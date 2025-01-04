package model;

import javafx.beans.property.*;

public class Ikan {
    private final IntegerProperty id;
    private final StringProperty nama;
    private final DoubleProperty harga;
    private final StringProperty gambarIkan;
    private final IntegerProperty stok;
    private final IntegerProperty idNelayan;

    public Ikan(int id, String nama, double harga, String gambarIkan, int stok, int idNelayan) {
        this.id = new SimpleIntegerProperty(id);
        this.nama = new SimpleStringProperty(nama);
        this.harga = new SimpleDoubleProperty(harga);
        this.gambarIkan = new SimpleStringProperty(gambarIkan);
        this.stok = new SimpleIntegerProperty(stok);
        this.idNelayan = new SimpleIntegerProperty(idNelayan);
    }

    // Getter untuk TableView Binding
    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public DoubleProperty hargaProperty() {
        return harga;
    }

    public StringProperty gambarIkanProperty() {
        return gambarIkan;
    }

    public IntegerProperty stokProperty() {
        return stok;
    }

    public IntegerProperty idNelayanProperty() {
        return idNelayan;
    }

    // Getter dan Setter
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public double getHarga() {
        return harga.get();
    }

    public void setHarga(double harga) {
        this.harga.set(harga);
    }

    public String getGambarIkan() {
        return gambarIkan.get();
    }

    public void setGambarIkan(String gambarIkan) {
        this.gambarIkan.set(gambarIkan);
    }

    public int getStok() {
        return stok.get();
    }

    public void setStok(int stok) {
        this.stok.set(stok);
    }

    public int getIdNelayan() {
        return idNelayan.get();
    }

    public void setIdNelayan(int idNelayan) {
        this.idNelayan.set(idNelayan);
    }
}
