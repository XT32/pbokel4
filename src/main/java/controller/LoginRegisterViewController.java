package controller;

import service.UserService;
import model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginRegisterViewController implements Initializable {

    private final UserService userService = new UserService();

    @FXML
    private Button si_loginButton;

    @FXML
    private AnchorPane si_loginForm;

    @FXML
    private PasswordField si_password;

    @FXML
    private TextField si_username;

    @FXML
    private TextField su_alamat;

    @FXML
    private TextField su_confirmPass;

    @FXML
    private TextField su_email;

    @FXML
    private TextField su_namaLengkap;

    @FXML
    private TextField su_password;

    @FXML
    private Button su_registerButton;

    @FXML
    private AnchorPane su_signupForm;

    @FXML
    private TextField su_username;

    @FXML
    private Button side_alreadyHave;

    @FXML
    private Button side_createButton;

    @FXML
    private Button side_alreadyHave1;

    @FXML
    private Button side_createButton1;

    @FXML
    private AnchorPane side_formLeft;

    @FXML
    private AnchorPane side_formRight;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set initial visibility of forms
        si_loginForm.setVisible(false);
        su_signupForm.setVisible(true);
        side_formRight.setVisible(true);
        side_formLeft.setVisible(false);
    }

    @FXML
    public void handleLogin(ActionEvent event) {
        try {
            // Ambil input dari form login
            String username = si_username.getText();
            String password = si_password.getText();

            // Validasi input
            if (username.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Username and password cannot be empty.");
                return;
            }

            // Cek jika admin login
            if ("admin".equals(username) && "admin123".equals(password)) {
                showAlert(Alert.AlertType.INFORMATION, "Login Success", "Welcome, Admin!");
                loadAdminView();
                return;
            }

            // Login sebagai pengguna biasa
            User user = userService.loginUser(username, password); // Memeriksa login melalui service
            if (user != null) {
                showAlert(Alert.AlertType.INFORMATION, "Login Success", "Welcome, " + user.getUsername());
                loadUserView(user); // Memuat tampilan pengguna dengan data pengguna
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRegister(ActionEvent event) {
        try {
            // Ambil input dari form register
            String namaLengkap = su_namaLengkap.getText();
            String username = su_username.getText();
            String alamat = su_alamat.getText();
            String email = su_email.getText();
            String password = su_password.getText();
            String confirmPass = su_confirmPass.getText();

            // Validasi input
            if (namaLengkap.isEmpty() || username.isEmpty() || alamat.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Registration Failed", "All fields must be filled.");
                return;
            }

            // Validasi password dan konfirmasi password
            if (!password.equals(confirmPass)) {
                showAlert(Alert.AlertType.ERROR, "Registration Failed", "Passwords do not match.");
                return;
            }

            // Validasi format email dan kekuatan password
            if (!isInputValid(email, password)) {
                return;
            }

            // Buat objek User baru
            User user = new User(0, username, email, password, alamat, namaLengkap); // ID user diatur otomatis di database

            // Lakukan registrasi melalui UserService
            boolean registrationSuccess = userService.registerUser(user);

            if (registrationSuccess) {
                showAlert(Alert.AlertType.INFORMATION, "Registration Success", "Account created successfully!");
                switchFormToLogin(); // Pindah ke form login setelah registrasi berhasil
            } else {
                showAlert(Alert.AlertType.ERROR, "Registration Failed", "An error occurred. Try again.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    public void switchForm(ActionEvent event) {
        if (event.getSource() == side_alreadyHave1 || event.getSource() == side_createButton1) {
            su_signupForm.setVisible(false);
            si_loginForm.setVisible(true);
            side_formRight.setVisible(false);
            side_formLeft.setVisible(true);
        } else if (event.getSource() == side_alreadyHave || event.getSource() == side_createButton) {
            si_loginForm.setVisible(false);
            su_signupForm.setVisible(true);
            side_formRight.setVisible(true);
            side_formLeft.setVisible(false);
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.show();
    }

    private boolean isInputValid(String email, String password) {
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            showAlert(Alert.AlertType.ERROR, "Invalid Email", "Please enter a valid email address.");
            return false;
        }
        if (password.length() < 6) {
            showAlert(Alert.AlertType.ERROR, "Weak Password", "Password must be at least 6 characters long.");
            return false;
        }
        return true;
    }

    private void loadUserView(User user) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/userView.fxml"));
        Parent root = loader.load();

        // Set ukuran stage yang sesuai dengan file FXML
        Stage stage = (Stage) si_loginButton.getScene().getWindow();
        Scene scene = new Scene(root, 1100, 600); // Sesuaikan ukuran dengan prefWidth dan prefHeight di FXML
        stage.setResizable(true); // Memungkinkan resize window
        stage.setMinWidth(1100); // Ukuran minimal
        stage.setMinHeight(600); // Ukuran minimal
        stage.setScene(scene);
        stage.show();

        // Set data pengguna ke UserViewController
        UserViewController userViewController = loader.getController();
        userViewController.setUser(user);
    } catch (IOException e) {
        showAlert(Alert.AlertType.ERROR, "Error", "Unable to load user view: " + e.getMessage());
    }
}


    private void loadAdminView() {
        try {
            // Validasi apakah path resource FXML benar
            System.out.println("Path to adminView.fxml: " + getClass().getResource("/view/adminView.fxml"));

            // Pastikan path FXML sesuai dengan lokasi file sebenarnya
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/adminView.fxml"));

            // Load file FXML
            Parent root = loader.load();

            // Dapatkan stage dari tombol login
            Stage stage = (Stage) si_loginButton.getScene().getWindow();
            Scene scene = new Scene(root);

            // Set scene baru dan tampilkan
            stage.setScene(scene);
            stage.show();
        } catch (NullPointerException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "File adminView.fxml tidak ditemukan di path yang diberikan.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Gagal memuat file admin view: " + e.getMessage());
        }
    }

    private void switchFormToLogin() {
        su_signupForm.setVisible(false);
        si_loginForm.setVisible(true);
        side_formRight.setVisible(false);
        side_formLeft.setVisible(true);
    }
}
