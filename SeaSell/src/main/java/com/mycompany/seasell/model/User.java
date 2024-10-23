/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.seasell.model;

/**
 *
 * @author alich
 */
public class User {
    public int idUser;
    public String nama;
    public String username;
    public String email;
    public String password;
    public String alamat;

    public User(String nama, String username, String email, String password) {
        this.idUser = idUser;
        this.nama = nama;
        this.username = username;
        this.email = email;
        this.password = password;
        this.alamat = alamat;
    }

    public User(String nama, String username, String email, String password, String alamat) {
        this.nama = nama;
        this.username = username;
        this.email = email;
        this.password = password;
        this.alamat = alamat;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
}
