package model;

public class User {

    private String namaLengkap;
    private String username;
    private String alamat;
    private String email;
    private String password;

    public User(String namaLengkap, String username, String alamat, String email, String password) {
        this.namaLengkap = namaLengkap;
        this.username = username;
        this.alamat = alamat;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public String getNamaLengkap() {
        return namaLengkap;
    }

    public String getUsername() {
        return username;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
