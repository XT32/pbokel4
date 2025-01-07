package controller;

import dao.AdminDAO;
import dao.baseDAO; // Pastikan class Database diimport dengan benar
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Ikan;

public class adminViewController implements Initializable {

    @FXML
    private AnchorPane Dashboard_form;

    @FXML
    private Button Inventory_deleteButton;

    @FXML
    private Label Numberof_customer;

    @FXML
    private AnchorPane adminView;

    @FXML
    private TableColumn<?, ?> dartacustomer_alamat;

    @FXML
    private Button dashboard_button;

    @FXML
    private BarChart<?, ?> dashboard_customerChart;

    @FXML
    private AreaChart<?, ?> dashboard_incomechart;

    @FXML
    private Button dataBeli_button;

    @FXML
    private Button dataCustomer_button;

    @FXML
    private Button dataJual_button;

    @FXML
    private AnchorPane databeli_form;

    @FXML
    private TableColumn<?, ?> databeli_idtransaksi;

    @FXML
    private TableView<?> databeli_kuantitas;

    @FXML
    private TableColumn<?, ?> databeli_tanggal;

    @FXML
    private TableColumn<?, ?> databeli_total;

    @FXML
    private TableColumn<?, ?> datacustomer_IDuser;

    @FXML
    private AnchorPane datacustomer_form;

    @FXML
    private TableColumn<?, ?> datacustomer_namauser;

    @FXML
    private TableColumn<?, ?> datacustomer_notelp;

    @FXML
    private TableColumn<?, ?> datajual_alamat;

    @FXML
    private AnchorPane datajual_form;

    @FXML
    private TableColumn<?, ?> datajual_idtrans;

    @FXML
    private TableColumn<?, ?> datajual_iduser;

    @FXML
    private TableColumn<?, ?> datajual_kuantitas;

    @FXML
    private TableColumn<?, ?> datajual_tanggal;

    @FXML
    private TableColumn<?, ?> datajual_total;

    @FXML
    private TableColumn<?, ?> datauser_idikan;

    @FXML
    private TableColumn<?, ?> datauser_totalbelanja;

    @FXML
    private TextField inventory_IDIkan;

    @FXML
    private TextField inventory_IDNelayan;

    @FXML
    private Button inventory_addButton;

    @FXML
    private Button inventory_button;

    @FXML
    private Button inventory_clearButton;

    @FXML
    private AnchorPane inventory_form;

    @FXML
    private TextField inventory_harga;

    @FXML
    private ImageView inventory_image_view;

    @FXML
    private Button inventory_importButton;

    @FXML
    private TextField inventory_namaIkan;

    @FXML
    private TextField inventory_stok;

    @FXML
    private TableView<?> inventory_table;

    @FXML
    private TableColumn<?, ?> inventory_tableHarga;

    @FXML
    private TableColumn<?, ?> inventory_tableIDNelayan;

    @FXML
    private TableColumn<?, ?> inventory_tableIDikan;

    @FXML
    private TableColumn<?, ?> inventory_tableStok;

    @FXML
    private TableColumn<?, ?> inventory_tablenamaIkan;

    @FXML
    private Button inventory_updateButton;

    @FXML
    private Button logout_button;

    @FXML
    private Label numberofsold;

    @FXML
    private AnchorPane scroll_dashboardForm;

    @FXML
    private TableView<?> tabeldataPenjualan;

    @FXML
    private Label todays_income;

    @FXML
    private Label total_income;

    private Alert alert;
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Menampilkan halaman default (Dashboard)
        showDashboard();
                // Menghubungkan tombol Import dengan fungsi handleImportImage
        inventory_importButton.setOnAction(event -> handleImportImage());

        // Menghubungkan tombol Add dengan fungsi handleAddFish
        inventory_addButton.setOnAction(event -> handleAddFish());
        // Menghubungkan tombol dengan metode untuk mengganti halaman
        inventory_clearButton.setOnAction(event -> handleClearForm());
        logout_button.setOnAction(event -> handleLogout());


        dashboard_button.setOnAction(event -> showDashboard());
        inventory_button.setOnAction(event -> showInventory());
        dataJual_button.setOnAction(event -> showDataJual());
        dataBeli_button.setOnAction(event -> showDataBeli());
        dataCustomer_button.setOnAction(event -> showDataCustomer());
    }

    @FXML
    private void showDashboard() {
        setVisiblePage(Dashboard_form);
    }

    @FXML
    private void showInventory() {
        setVisiblePage(inventory_form);
    }

    @FXML
    private void showDataJual() {
        setVisiblePage(datajual_form);
    }

    @FXML
    private void showDataBeli() {
        setVisiblePage(databeli_form);
    }

    @FXML
    private void showDataCustomer() {
        setVisiblePage(datacustomer_form);
    }
    
    private AdminDAO adminDAO;


    private void setVisiblePage(AnchorPane visiblePage) {
        // Menyembunyikan semua halaman
        Dashboard_form.setVisible(false);
        inventory_form.setVisible(false);
        datajual_form.setVisible(false);
        databeli_form.setVisible(false);
        datacustomer_form.setVisible(false);

        // Menampilkan halaman yang dipilih
        visiblePage.setVisible(true);
    }
    
     private void initializeDAO() {
        try (Connection connection = baseDAO.connectDB()) {
            adminDAO = new AdminDAO(connection);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gagal menghubungkan ke database: " + e.getMessage());
        }
    }

    private void initializeButtonActions() {
        inventory_addButton.setOnAction(event -> handleAddFish());
    }


    @FXML
private void handleAddFish() {
    try {
        // Debugging untuk memeriksa data input
        System.out.println("Nama Ikan: " + inventory_namaIkan.getText());
        System.out.println("Harga: " + inventory_harga.getText());
        System.out.println("Stok: " + inventory_stok.getText());
        System.out.println("ID Nelayan: " + inventory_IDNelayan.getText());

        // Validasi input
        if (inventory_namaIkan.getText().isEmpty() || inventory_harga.getText().isEmpty() || 
            inventory_stok.getText().isEmpty() || inventory_IDNelayan.getText().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error", "Harap isi semua field yang kosong.");
            return;
        }

        // Ambil data dari form
        String namaIkan = inventory_namaIkan.getText();
        double harga = Double.parseDouble(inventory_harga.getText());
        int stok = Integer.parseInt(inventory_stok.getText());
        int idNelayan = Integer.parseInt(inventory_IDNelayan.getText());
        String gambarIkan = "default.jpg"; // Placeholder path untuk gambar

        // Debugging untuk memastikan data sudah benar
        System.out.println("Data yang diambil:");
        System.out.println("Nama: " + namaIkan);
        System.out.println("Harga: " + harga);
        System.out.println("Stok: " + stok);
        System.out.println("ID Nelayan: " + idNelayan);

        // Buat objek ikan
        Ikan ikan = new Ikan(0, namaIkan, harga, gambarIkan, stok, idNelayan);

        // Query untuk menambahkan data ikan ke database
        String insertQuery = "INSERT INTO ikan (nama_ikan, harga, stok, id_nelayan, gambar_ikan) VALUES (?, ?, ?, ?, ?)";

        // Debugging untuk koneksi database
        System.out.println("Mencoba koneksi ke database...");

        // Koneksi ke database
        try (Connection connect = baseDAO.connectDB();
             PreparedStatement prepare = connect.prepareStatement(insertQuery)) {

            // Set parameter query
            prepare.setString(1, namaIkan);
            prepare.setDouble(2, harga);
            prepare.setInt(3, stok);
            prepare.setInt(4, idNelayan);
            prepare.setString(5, gambarIkan);

            // Eksekusi query
            prepare.executeUpdate();
            System.out.println("Data berhasil ditambahkan ke database.");

            showAlert(Alert.AlertType.INFORMATION, "Success", "Ikan berhasil ditambahkan!");

        } catch (Exception e) {
            System.out.println("Error saat menambahkan data ke database: " + e.getMessage());
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + e.getMessage());
        }

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
        showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + e.getMessage());
    }
}

    
    private void handleImportImage() {
    // Membuat file chooser
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Pilih Gambar Ikan");
    
    // Filter file hanya untuk gambar
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
    );

    // Menampilkan file manager
    File selectedFile = fileChooser.showOpenDialog(inventory_image_view.getScene().getWindow());

    if (selectedFile != null) {
        // Menampilkan gambar di ImageView
        Image image = new Image(selectedFile.toURI().toString());
        inventory_image_view.setImage(image);

        // Anda juga bisa menyimpan path gambar ini untuk diupload ke database jika diperlukan
        System.out.println("Gambar berhasil diimpor: " + selectedFile.getAbsolutePath());
    } else {
        System.out.println("Tidak ada gambar yang dipilih.");
    }
}
    @FXML
private void handleClearForm() {
    // Mengosongkan semua text field
    inventory_IDIkan.clear();
    inventory_namaIkan.clear();
    inventory_IDNelayan.clear();
    inventory_stok.clear();
    inventory_harga.clear();

    // Menghapus gambar pada ImageView
    inventory_image_view.setImage(null);
}
@FXML
private void handleLogout() {
    try {
        System.out.println("Log: Tombol logout ditekan.");

        // Tutup stage saat ini
        logout_button.getScene().getWindow().hide();

        // Buat stage baru untuk login
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loginRegister.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();

        System.out.println("Sign out berhasil, kembali ke halaman login.");
    } catch (Exception e) {
        e.printStackTrace();
        showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan saat logout: " + e.getMessage());
    }
}




    private void showAlert(Alert.AlertType alertType, String title, String message) {
        alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
