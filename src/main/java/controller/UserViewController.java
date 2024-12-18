package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author imdaq
 */
public class UserViewController implements Initializable {

    // fx:id untuk AnchorPane setiap halaman
    @FXML
    private AnchorPane user_marketPlace;

    @FXML
    private AnchorPane user_profile;

    @FXML
    private AnchorPane user_history;

    // fx:id untuk tombol navigasi
    @FXML
    private Button user_shopButton;

    @FXML
    private Button user_profileButton;

    @FXML
    private Button user_historyButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set tampilan default ke halaman Marketplace
        showShop();
    }

    // Method untuk menampilkan halaman Shop
    @FXML
    private void showShop() {
        user_marketPlace.setVisible(true);
        user_profile.setVisible(false);
        user_history.setVisible(false);
    }

    // Method untuk menampilkan halaman Profile
    @FXML
    private void showProfile() {
        user_marketPlace.setVisible(false);
        user_profile.setVisible(true);
        user_history.setVisible(false);
    }

    // Method untuk menampilkan halaman History
    @FXML
    private void showHistory() {
        user_marketPlace.setVisible(false);
        user_profile.setVisible(false);
        user_history.setVisible(true);
    }
}
