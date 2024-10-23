/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.seasell.model;

/**
 *
 * @author alich
 */
public class Ikan {
    private int idIkan;
    private String jenisIkan;
    private double harga;

    public Ikan(int idIkan, String jenisIkan, double harga) {
        this.idIkan = idIkan;
        this.jenisIkan = jenisIkan;
        this.harga = harga;
    }

    public int getIdIkan() {
        return idIkan;
    }

    public void setIdIkan(int idIkan) {
        this.idIkan = idIkan;
    }

    public String getJenisIkan() {
        return jenisIkan;
    }

    public void setJenisIkan(String jenisIkan) {
        this.jenisIkan = jenisIkan;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
    
}
