/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.seasell.utils;

import com.mycompany.seasell.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author alich
 */
public class PembeliDAO extends BaseDAO {

    public boolean addPembeli(User pembeli) {
        String query = "INSERT INTO seasell.user (nama, alamat, username, email, password) VALUES (?, ?, ?, ?, ?)";

        if (conn == null) {
            System.out.println("Koneksi database tidak tersedia.");
            return false;
        }
        try (PreparedStatement pS = conn.prepareStatement(query)) {
            pS.setString(1, pembeli.getNama());
            pS.setString(2, pembeli.getAlamat());
            pS.setString(3, pembeli.getUsername());
            pS.setString(4, pembeli.getEmail());
            pS.setString(5, pembeli.getPassword());

            int rowsAffected = pS.executeUpdate();
            
            return rowsAffected > 0;  
        } catch (SQLException e) {
    System.out.println("Gagal memasukkan data ke tabel pembeli: " + e.getMessage());
    return false;}
    }
}
