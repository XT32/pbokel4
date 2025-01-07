package controller;

import dao.AdminDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.Ikan;
import model.Pembelian;
import model.Nelayan;
import utils.DatabaseConnection;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import model.User;

public class AdminViewController implements Initializable {
   // Form Dashboard
    @FXML
    private AnchorPane Dashboard_form;
    @FXML
    private Label todays_income;
    @FXML
    private Label total_income;
    @FXML
    private Label numberofsold;
    @FXML
    private Label Numberof_customer;

    // Form Inventory
    @FXML
    private AnchorPane inventory_form;
    @FXML
    private TextField inventory_IDIkan;
    @FXML
    private TextField inventory_IDNelayan;
    @FXML
    private TextField inventory_harga;
    @FXML
    private TextField inventory_namaIkan;
    @FXML
    private TextField inventory_stok;
    @FXML
    private Button inventory_addButton;
    @FXML
    private Button inventory_clearButton;
    @FXML
    private Button inventory_updateButton;
    @FXML
    private Button inventory_importButton;
    @FXML
    private Button Inventory_deleteButton;
    @FXML
    private ImageView inventory_image_view;
    @FXML
    private TableView<Ikan> inventory_table;
    @FXML
    private TableColumn<Ikan, String> inventory_tablenamaIkan;
    @FXML
    private TableColumn<Ikan, Double> inventory_tableHarga;
    @FXML
    private TableColumn<Ikan, Integer> inventory_tableStok;
    @FXML
    private TableColumn<Ikan, Integer> inventory_tableIDNelayan;

    // Form Data Jual
    @FXML
    private AnchorPane datajual_form;
    @FXML
    private TableView<Pembelian> datajual_table;

    // Form Data Beli
    @FXML
    private AnchorPane databeli_form;
    @FXML
    private TableView<Pembelian> databeli_table;

    // Form Data Customer
    @FXML
    private AnchorPane datacustomer_form;
    @FXML
    private TableView<Nelayan> datacustomer_table;

    // Buttons
    @FXML
    private Button dashboard_button;
    @FXML
    private Button inventory_button;
    @FXML
    private Button dataJual_button;
    @FXML
    private Button dataBeli_button;
    @FXML
    private Button dataCustomer_button;
    @FXML
    private Button logout_button;


    private AdminDAO adminDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the DAO
        try {
            Connection connection = DatabaseConnection.connectDB();
            adminDAO = new AdminDAO(connection);
            System.out.println("AdminDAO initialized successfully.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to connect to the database: " + e.getMessage());
            e.printStackTrace();
        }

        // Show the default form (Dashboard)
        handleDashboardButtonAction();

        // Bind buttons with respective methods
        dashboard_button.setOnAction(event -> handleDashboardButtonAction());
        inventory_button.setOnAction(event -> handleInventoryButtonAction());
        dataJual_button.setOnAction(event -> handleDataJualButtonAction());
        dataBeli_button.setOnAction(event -> handleDataBeliButtonAction());
        dataCustomer_button.setOnAction(event -> handleDataCustomerButtonAction());
        logout_button.setOnAction(event -> handleLogoutButtonAction());
        inventory_importButton.setOnAction(event -> handleImportImage());
        inventory_addButton.setOnAction(event -> handleAddIkan());

    }

    @FXML
    private void handleDashboardButtonAction() {
        setVisibleForm(Dashboard_form);
    }

    @FXML
    private void handleInventoryButtonAction() {
        setVisibleForm(inventory_form);

        // Load inventory data
        try {
            List<Ikan> ikanList = adminDAO.getAllIkan();
            inventory_table.setItems(FXCollections.observableArrayList(ikanList));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load inventory data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
private void handleAddIkan() {
    try {
        // Ambil data dari input field
        int idIkan = Integer.parseInt(inventory_IDIkan.getText());
        String namaIkan = inventory_namaIkan.getText();
        int stok = Integer.parseInt(inventory_stok.getText());
        double harga = Double.parseDouble(inventory_harga.getText());
        int idNelayan = Integer.parseInt(inventory_IDNelayan.getText());

        // Tambahkan ikan ke database
        Ikan ikan = new Ikan(idIkan, namaIkan, harga, null, stok, idNelayan); // Null untuk gambar sementara
        adminDAO.getAllIkan();

        // Refresh tabel
        handleInventoryButtonAction();

        // Beri konfirmasi sukses
        showAlert(Alert.AlertType.INFORMATION, "Success", "Data ikan berhasil ditambahkan.");
    } catch (Exception e) {
        showAlert(Alert.AlertType.ERROR, "Error", "Gagal menambahkan data ikan: " + e.getMessage());
        e.printStackTrace();
    }
}


    @FXML
    private void handleDataJualButtonAction() {
        setVisibleForm(datajual_form);

        // Load sales data
        try {
            List<Pembelian> penjualanList = adminDAO.getAllPenjualan();
            datajual_table.setItems(FXCollections.observableArrayList(penjualanList));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load sales data: " + e.getMessage());
        }
    }

    @FXML
    private void handleDataBeliButtonAction() {
        setVisibleForm(databeli_form);

        // Load purchase data
        try {
            List<Pembelian> pembelianList = adminDAO.getAllPembelian();
            databeli_table.setItems(FXCollections.observableArrayList(pembelianList));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load purchase data: " + e.getMessage());
        }
    }

    @FXML
    private void handleDataCustomerButtonAction() {
        setVisibleForm(datacustomer_form);

        // Load customer data
        try {
            List<Nelayan> pelangganList = adminDAO.getAllPelanggan();
            datacustomer_table.setItems(FXCollections.observableArrayList(pelangganList));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load customer data: " + e.getMessage());
        }
    }

    @FXML
    private void handleLogoutButtonAction() {
        showAlert(Alert.AlertType.INFORMATION, "Logout", "You have logged out.");
    }

    @FXML
    private void handleImportImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Fish Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(inventory_importButton.getScene().getWindow());

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            inventory_image_view.setImage(image);
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }

    private void setVisibleForm(AnchorPane form) {
        Dashboard_form.setVisible(false);
        inventory_form.setVisible(false);
        datajual_form.setVisible(false);
        databeli_form.setVisible(false);
        datacustomer_form.setVisible(false);

        form.setVisible(true);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
