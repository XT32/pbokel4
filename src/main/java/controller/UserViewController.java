package controller;

import model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class UserViewController implements Initializable {

    @FXML
    private AnchorPane UserView;

    @FXML
    private Label namalengkap;

    @FXML
    private Button user_shopButton;

    @FXML
    private Button user_profileButton;

    @FXML
    private Button user_historyButton;

    @FXML
    private AnchorPane user_marketPlace;

    @FXML
    private ScrollPane menu_scrollPane;

    @FXML
    private GridPane ikan_gridPane;

    @FXML
    private TableView<?> shop_listBeli;

    @FXML
    private TableColumn<?, ?> shop_namaIkan;

    @FXML
    private TableColumn<?, ?> shop_kuantitas;

    @FXML
    private TableColumn<?, ?> shop_harga;

    @FXML
    private Button shop_bayar;

    @FXML
    private AnchorPane user_history;

    @FXML
    private AnchorPane user_profile;

    @FXML
    private TextField no_profile;

    @FXML
    private TextField email_profile;

    @FXML
    private TextField alamat_profile;

    @FXML
    private Button editProfile_bt;

    @FXML
    private Button logOut_Bt;

    @FXML
    private TextField alamat_profile1;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label alamatLabel;

    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
        updateProfileInfo();
    }

    private void updateProfileInfo() {
        if (currentUser != null) {
            namalengkap.setText(currentUser.getNamaLengkap());
            usernameLabel.setText(currentUser.getUsername());
            emailLabel.setText(currentUser.getEmail());
            alamatLabel.setText(currentUser.getAlamat());

            no_profile.setText(currentUser.getNoProfile());
            email_profile.setText(currentUser.getEmail());
            alamat_profile.setText(currentUser.getAlamat());
            alamat_profile1.setText(currentUser.getAlamat());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inisialisasi atau pemanggilan setUser jika pengguna sudah terautentikasi
        // Misalnya, mendapatkan pengguna yang sudah login dan kemudian memanggil setUser:
        // setUser(currentLoggedInUser);
    }

    @FXML
    public void handleEditProfile() {
        // Implementasi logika edit profil jika diperlukan
        // Memungkinkan pengguna untuk memperbarui informasi di TextField
        System.out.println("Editing profile...");
    }

    @FXML
    public void handleLogout() {
        System.out.println("Logging out...");
    }

    @FXML
    public void showMarketPlace() {
        user_marketPlace.setVisible(true);
        user_profile.setVisible(false);
        user_history.setVisible(false);
    }
}
