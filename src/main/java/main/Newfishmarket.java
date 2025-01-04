package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;

/**
 * Aplikasi utama
 */

public class Newfishmarket extends Application {

    private static final Logger LOGGER = Logger.getLogger(Newfishmarket.class.getName());

    /**
     * Method untuk menjalankan aplikasi JavaFX.
     *
     * @param stage Stage utama untuk aplikasi.
     */
    
    @Override
    public void start(Stage stage) {
        try {
            String fxmlPath = "view/loginRegisterView.fxml";

            URL fxmlURL = getClass().getClassLoader().getResource(fxmlPath);
            if (fxmlURL == null) {
                LOGGER.log(Level.SEVERE, "File FXML tidak ditemukan di classpath: {0}", fxmlPath);
                throw new IOException("File FXML tidak ditemukan di classpath: " + fxmlPath);
            }

            LOGGER.log(Level.INFO, "Memuat file FXML dari: {0}", fxmlURL);

            FXMLLoader loader = new FXMLLoader(fxmlURL);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Fish Market Login");
            stage.show();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Gagal memuat file FXML", e);
            showErrorAlert("Error", "Gagal memuat file FXML: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Kesalahan tidak terduga", e);
            showErrorAlert("Error", "Terjadi kesalahan tidak terduga: " + e.getMessage());
        }
    }

    /**
     * Menampilkan dialog error kepada pengguna.
     *
     * @param title Judul dialog error.
     * @param message Pesan error yang ditampilkan.
     */
    
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Method utama untuk menjalankan aplikasi.
     *
     * @param args Parameter untuk aplikasi (tidak digunakan di sini).
     */
    
    public static void main(String[] args) {
        launch(args);
    }
}
