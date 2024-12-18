package controller;

import service.UserService;
import model.User;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginRegisterViewController implements Initializable {

    private final UserService userService = new UserService();

    @FXML
    private TextField si_username, si_password, su_namaLengkap, su_username, su_alamat, su_email, su_password, su_confirmPass;

    @FXML
    private Button si_loginButton, side_createButton, side_alreadyHave;

    @FXML
    private AnchorPane si_loginForm, su_signupForm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Atur posisi awal kedua form
        si_loginForm.setTranslateX(0); // Login form ada di posisi awal (tengah)
        su_signupForm.setTranslateX(600); // Register form berada di luar layar kanan

        // Atur visibilitas tombol
        side_alreadyHave.setVisible(false); // Tombol "Already Have an Account" tidak terlihat saat pertama kali
    }

    @FXML
    public void handleLogin(ActionEvent event) {
        try {
            String username = si_username.getText();
            String password = si_password.getText();

            User user = userService.loginUser(username, password);
            if (user != null) {
                showAlert(Alert.AlertType.INFORMATION, "Login Success", "Welcome, " + user.getNamaLengkap());
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    public void handleRegister(ActionEvent event) {
        try {
            String namaLengkap = su_namaLengkap.getText();
            String username = su_username.getText();
            String alamat = su_alamat.getText();
            String email = su_email.getText();
            String password = su_password.getText();
            String confirmPass = su_confirmPass.getText();

            if (!password.equals(confirmPass)) {
                showAlert(Alert.AlertType.ERROR, "Registration Failed", "Passwords do not match.");
                return;
            }

            if (!isInputValid(email, password)) {
                return;
            }

            User user = new User(namaLengkap, username, alamat, email, password);
            if (userService.registerUser(user)) {
                showAlert(Alert.AlertType.INFORMATION, "Registration Success", "Account created successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Registration Failed", "An error occurred. Try again.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    public void switchForm(ActionEvent event) {
        if (event.getSource() == side_createButton) {
            // Pindah dari Login ke Register
            animateSwitch(si_loginForm, su_signupForm);
            toggleButtonVisibility(true); // Tampilkan tombol "Already Have an Account"
        } else if (event.getSource() == side_alreadyHave) {
            // Pindah dari Register ke Login
            animateSwitch(su_signupForm, si_loginForm);
            toggleButtonVisibility(false); // Tampilkan tombol "Create New Account"
        }
    }

    private void animateSwitch(AnchorPane currentForm, AnchorPane targetForm) {
        // Animasi menggeser currentForm keluar ke kiri
        TranslateTransition hideTransition = new TranslateTransition(Duration.seconds(0.5), currentForm);
        hideTransition.setToX(-600); // Geser currentForm ke luar layar kiri
        hideTransition.setOnFinished(event -> {
            currentForm.setTranslateX(600); // Reset posisi currentForm ke luar layar kanan
        });

        // Animasi menggeser targetForm masuk dari kanan
        TranslateTransition showTransition = new TranslateTransition(Duration.seconds(0.5), targetForm);
        showTransition.setFromX(600); // Pastikan targetForm mulai dari luar layar kanan
        showTransition.setToX(0); // Geser targetForm ke posisi tengah

        // Jalankan kedua animasi
        hideTransition.play();
        showTransition.play();
    }

    private void toggleButtonVisibility(boolean isRegisterVisible) {
        side_createButton.setVisible(!isRegisterVisible); // Tampilkan tombol "Create New Account" hanya di login
        side_alreadyHave.setVisible(isRegisterVisible);  // Tampilkan tombol "Already Have an Account" hanya di register
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
}
