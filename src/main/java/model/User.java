package model;

import javafx.beans.property.*;

public class User {
    private final StringProperty namaLengkap;
    private final IntegerProperty idUser;
    private final StringProperty username;
    private final StringProperty email;
    private final StringProperty password;
    private final StringProperty alamat;
    

    // Konstruktor
    public User(int idUser, String username, String email, String password, String alamat, String namaLengkap) {
        this.namaLengkap = new SimpleStringProperty(namaLengkap);
        this.idUser = new SimpleIntegerProperty(idUser);
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.alamat = new SimpleStringProperty(alamat);
        
    }

    // Properti untuk binding dengan TableView
    public IntegerProperty idUserProperty() {
        return idUser;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty alamatProperty() {
        return alamat;
    }

    public StringProperty namaLengkapProperty() {
        return namaLengkap;
    }

    // Getter
    public int getIdUser() {
        return idUser.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPassword() {
        return password.get();
    }

    public String getAlamat() {
        return alamat.get();
    }

    public String getNamaLengkap() {
        return namaLengkap.get();
    }

    // Setter
    public void setIdUser(int idUser) {
        this.idUser.set(idUser);
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setAlamat(String alamat) {
        this.alamat.set(alamat);
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap.set(namaLengkap);
    }
}
