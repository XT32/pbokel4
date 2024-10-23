/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.mycompany.seasell.model.User;
import com.mycompany.seasell.utils.PembeliDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author alich
 */
public class RegisterController implements Initializable {
    @FXML
    private TextField namaField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button registerButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Tidak ada inisialisasi yang diperlukan di sini
    }    
    
    @FXML
    private void handleRegister(ActionEvent event) {
        // Ambil data dari TextField
        String nama = namaField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validasi password
        if (!password.equals(confirmPassword)) {
            System.out.println("Password dan konfirmasi password tidak sama!");
            return;
        }

        // Buat objek User baru dengan urutan parameter yang benar
        User pembeli = new User(nama, username, email, password);

        // Gunakan DAO untuk menyimpan ke database
        PembeliDAO pDAO = new PembeliDAO();
        boolean isAdded = pDAO.addPembeli(pembeli);

        // Cek apakah registrasi berhasil
        if (isAdded) {
            System.out.println("Registrasi berhasil untuk pengguna: " + username);
        } else {
            System.out.println("Gagal menyimpan data pengguna ke database.");
        }
    }
}