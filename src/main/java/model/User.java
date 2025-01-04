package model;

public class User {
    private String username;
    private String namaLengkap;
    private String email;
    private String alamat;
    private String noProfile;

    public User(String username, String namaLengkap, String email, String alamat, String noProfile) {
        this.username = username;
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.alamat = alamat;
        this.noProfile = noProfile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoProfile() {
        return noProfile;
    }

    public void setNoProfile(String noProfile) {
        this.noProfile = noProfile;
    }

    public String getPassword() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
