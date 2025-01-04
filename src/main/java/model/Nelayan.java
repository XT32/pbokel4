package model;

public class Nelayan {
    private int id;
    private String namaNelayan;
    private String nomorTelepon;

    public Nelayan(int id, String namaNelayan, String nomorTelepon) {
        this.id = id;
        this.namaNelayan = namaNelayan;
        this.nomorTelepon = nomorTelepon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaNelayan() {
        return namaNelayan;
    }

    public void setNamaNelayan(String namaNelayan) {
        this.namaNelayan = namaNelayan;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }
}
