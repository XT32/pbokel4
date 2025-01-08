package controller;

import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Ikan;
import model.Pembelian;
import model.User;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {

    @FXML
    private AnchorPane user_profile, user_marketPlace, user_history;

    @FXML
    private Label namalengkap, usernamelabel, emailLabel, alamatLabel;

    @FXML
    private Button user_shopButton, user_profileButton, user_historyButton, user_logout;

    @FXML
    private GridPane ikan_gridPane;

    @FXML
    private TableView<Pembelian> history_table;

    @FXML
    private TableColumn<Pembelian, String> history_namaIkan;

    @FXML
    private TableColumn<Pembelian, Integer> history_kuantitas;

    @FXML
    private TableColumn<Pembelian, Double> history_totalHarga;

    @FXML
    private TextField email_profile, alamat_profile;

    @FXML
    private Button editProfile_bt, saveProfile_bt;

    private User currentUser;
    private UserDAO userDAO;

    /**
     * Set data pengguna yang sedang login dan tampilkan pesan selamat datang.
     *
     * @param user Pengguna yang berhasil login.
     */
    public void setUser(User user) {
        this.currentUser = user;

        // Pastikan data pengguna benar
        System.out.println("User Data: " + user);

        updateProfileInfo();

        // Tampilkan alert selamat datang
        showAlert(Alert.AlertType.INFORMATION, "Welcome", "Welcome, " + user.getUsername() + "!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userDAO = new UserDAO();

            // Event handling untuk tombol
            user_shopButton.setOnAction(event -> showMarketPlace());
            user_profileButton.setOnAction(event -> showUserProfile());
            user_historyButton.setOnAction(event -> showUserHistory());
            user_logout.setOnAction(event -> handleLogout());
            editProfile_bt.setOnAction(event -> enableProfileEdit());
            saveProfile_bt.setOnAction(event -> saveUserProfile());

            // Tampilkan marketplace sebagai default
            showMarketPlace();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Initialization Error", "Failed to initialize UserViewController: " + e.getMessage());
        }
    }

    private void updateProfileInfo() {
        if (currentUser != null) {
            // Pastikan namalengkap menampilkan username, bukan alamat
            if (namalengkap != null) {
                namalengkap.setText("Welcome, " + currentUser.getUsername());
            }

            // Perbarui informasi lain
            usernamelabel.setText(currentUser.getUsername());
            emailLabel.setText(currentUser.getEmail());
            alamatLabel.setText(currentUser.getAlamat());
            email_profile.setText(currentUser.getEmail());
            alamat_profile.setText(currentUser.getAlamat());
        } else {
            System.err.println("Current user is null.");
        }
    }

    private void enableProfileEdit() {
        email_profile.setDisable(false);
        alamat_profile.setDisable(false);
        saveProfile_bt.setDisable(false);
    }

    private void saveUserProfile() {
        try {
            currentUser.setEmail(email_profile.getText());
            currentUser.setAlamat(alamat_profile.getText());
            userDAO.updateUserProfile(currentUser);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Profile updated successfully!");

            email_profile.setDisable(true);
            alamat_profile.setDisable(true);
            saveProfile_bt.setDisable(true);
            updateProfileInfo();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update profile: " + e.getMessage());
        }
    }

    private void showMarketPlace() {
        user_marketPlace.setVisible(true);
        user_profile.setVisible(false);
        user_history.setVisible(false);
        loadMarketplaceData();
    }

    private void showUserProfile() {
        user_marketPlace.setVisible(false);
        user_profile.setVisible(true);
        user_history.setVisible(false);
        updateProfileInfo();
    }

    private void showUserHistory() {
        user_marketPlace.setVisible(false);
        user_profile.setVisible(false);
        user_history.setVisible(true);
        loadPurchaseHistory();
    }

    @FXML
    private void handleLogout() {
        try {
            user_logout.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loginRegisterView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to return to login page: " + e.getMessage());
        }
    }

    private void loadMarketplaceData() {
        try {
            List<Ikan> ikanList = userDAO.getAllIkan();
            ikan_gridPane.getChildren().clear();

            for (Ikan ikan : ikanList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fishCard.fxml"));
                AnchorPane fishCard = loader.load();

                FishCardController controller = loader.getController();
                controller.setFishData(ikan, () -> {
                    System.out.println("Added to cart: " + ikan.getNamaIkan());
                });

                ikan_gridPane.add(fishCard, ikan_gridPane.getChildren().size() % 3, ikan_gridPane.getChildren().size() / 3);
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load marketplace data: " + e.getMessage());
        }
    }

    private void loadPurchaseHistory() {
        try {
            List<Pembelian> history = userDAO.getPurchaseHistory(currentUser.getIdUser());
            history_table.setItems(FXCollections.observableArrayList(history));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load purchase history: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
    