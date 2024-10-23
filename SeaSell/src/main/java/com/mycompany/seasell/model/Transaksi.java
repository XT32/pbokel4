/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.seasell.model;

/**
 *
 * @author alich
 */
public class Transaksi {
    private int idTransaksi;
    private int idUser;
    private int idIkan;
    private int jumlahHarga;

    public int getJumlahHarga() {
        return jumlahHarga;
    }

    public void setJumlahHarga(int jumlahHarga) {
        this.jumlahHarga = jumlahHarga;
    }

    public Transaksi(int idTransaksi, int idUser, int idIkan, int jumlah) {
        this.idTransaksi = idTransaksi;
        this.idUser = idUser;
        this.idIkan = idIkan;
        this.jumlahHarga = jumlah;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdIkan() {
        return idIkan;
    }

    public void setIdIkan(int idIkan) {
        this.idIkan = idIkan;
    }


    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

}
