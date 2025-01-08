package controller;

import dao.AdminDAO;
import dao.IkanDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Ikan;
import model.Pembelian;
import model.Nelayan;
import utils.DatabaseConnection;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

<<<<<<< HEAD:src/main/java/controller/adminViewController.java
=======
    // Dashboard Form
>>>>>>> bd75d9630d8ecb8c33188c8f6fd4cabdb6c341df:src/main/java/controller/AdminViewController.java
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

<<<<<<< HEAD:src/main/java/controller/adminViewController.java
=======
    // Inventory Form
>>>>>>> bd75d9630d8ecb8c33188c8f6fd4cabdb6c341df:src/main/java/controller/AdminViewController.java
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
    private Button inventory_deleteButton;
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

<<<<<<< HEAD:src/main/java/controller/adminViewController.java
=======
    // Data Jual Form
>>>>>>> bd75d9630d8ecb8c33188c8f6fd4cabdb6c341df:src/main/java/controller/AdminViewController.java
    @FXML
    private AnchorPane datajual_form;
    @FXML
    private TableView<Pembelian> datajual_table;

<<<<<<< HEAD:src/main/java/controller/adminViewController.java
=======
    // Data Beli Form
>>>>>>> bd75d9630d8ecb8c33188c8f6fd4cabdb6c341df:src/main/java/controller/AdminViewController.java
    @FXML
    private AnchorPane databeli_form;
    @FXML
    private TableView<Pembelian> databeli_table;

<<<<<<< HEAD:src/main/java/controller/adminViewController.java
=======
    // Data Customer Form
>>>>>>> bd75d9630d8ecb8c33188c8f6fd4cabdb6c341df:src/main/java/controller/AdminViewController.java
    @FXML
    private AnchorPane datacustomer_form;
    @FXML
    private TableView<Nelayan> datacustomer_table;

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
        try {
            // Initialize DAO and Database Connection
            Connection connection = DatabaseConnection.connectDB();
            adminDAO = new AdminDAO(connection);

<<<<<<< HEAD:src/main/java/controller/adminViewController.java
=======
            // Bind TableColumns
>>>>>>> bd75d9630d8ecb8c33188c8f6fd4cabdb6c341df:src/main/java/controller/AdminViewController.java
            inventory_tablenamaIkan.setCellValueFactory(cellData -> cellData.getValue().namaIkanProperty());
            inventory_tableHarga.setCellValueFactory(cellData -> cellData.getValue().hargaProperty().asObject());
            inventory_tableStok.setCellValueFactory(cellData -> cellData.getValue().stokProperty().asObject());
            inventory_tableIDNelayan.setCellValueFactory(cellData -> cellData.getValue().idNelayanProperty().asObject());

<<<<<<< HEAD:src/main/java/controller/adminViewController.java
            inventory_clearButton.setOnAction(event -> clearInventoryForm());

            handleDashboardButtonAction();

=======
            // Load Initial Data
            handleDashboardButtonAction();

            // Button Actions
>>>>>>> bd75d9630d8ecb8c33188c8f6fd4cabdb6c341df:src/main/java/controller/AdminViewController.java
            dashboard_button.setOnAction(event -> handleDashboardButtonAction());
            inventory_button.setOnAction(event -> handleInventoryButtonAction());
            dataJual_button.setOnAction(event -> handleDataJualButtonAction());
            dataBeli_button.setOnAction(event -> handleDataBeliButtonAction());
            dataCustomer_button.setOnAction(event -> handleDataCustomerButtonAction());
            logout_button.setOnAction(event -> handleLogoutButtonAction());
            inventory_importButton.setOnAction(event -> handleImportImage());
            inventory_addButton.setOnAction(event -> handleAddIkan());
<<<<<<< HEAD:src/main/java/controller/adminViewController.java

            loadDashboardData();
            loadInventoryData();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Initialization Error", "Failed to initialize the controller: " + e.getMessage());
        }
=======
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Initialization Error", "Failed to initialize the controller: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDashboardButtonAction() {
        setVisibleForm(Dashboard_form);
>>>>>>> bd75d9630d8ecb8c33188c8f6fd4cabdb6c341df:src/main/java/controller/AdminViewController.java
    }

    @FXML
    private void handleInventoryButtonAction() {
        setVisibleForm(inventory_form);
        try {
            List<Ikan> ikanList = adminDAO.getAllIkan();
            inventory_table.setItems(FXCollections.observableArrayList(ikanList));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load inventory data: " + e.getMessage());
        }
    }

    @FXML
<<<<<<< HEAD:src/main/java/controller/adminViewController.java
    private void handleDashboardButtonAction() {
        setVisibleForm(Dashboard_form);
        loadDashboardData();
    }

    @FXML
    private void handleAddIkan() {
        try {
            String namaIkan = inventory_namaIkan.getText();
            int stok = Integer.parseInt(inventory_stok.getText());
            double harga = Double.parseDouble(inventory_harga.getText());
            int idNelayan = Integer.parseInt(inventory_IDNelayan.getText());
            String gambarIkan = inventory_image_view.getImage() != null
                    ? "images/" + new File(inventory_image_view.getImage().getUrl()).getName()
                    : null;

            if (namaIkan.isEmpty() || stok <= 0 || harga <= 0 || idNelayan <= 0) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields must be filled with valid data.");
                return;
            }

            Ikan ikan = new Ikan(0, namaIkan, harga, gambarIkan, stok, idNelayan);
            adminDAO.addIkan(ikan);
            handleInventoryButtonAction();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Fish data successfully added.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Invalid input format: " + e.getMessage());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add fish data: " + e.getMessage());
        }
    }
=======
    private void handleAddIkan() {
        try {
            // Validate and Parse Inputs
            String namaIkan = inventory_namaIkan.getText();
            int stok = Integer.parseInt(inventory_stok.getText());
            double harga = Double.parseDouble(inventory_harga.getText());
            int idNelayan = Integer.parseInt(inventory_IDNelayan.getText());
            String gambarIkan = inventory_image_view.getImage() != null
                    ? "images/" + new File(inventory_image_view.getImage().getUrl()).getName()
                    : null;

            if (namaIkan.isEmpty() || stok <= 0 || harga <= 0 || idNelayan <= 0) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields must be filled with valid data.");
                return;
            }

            // Create and Save Ikan
            Ikan ikan = new Ikan(0, namaIkan, harga, gambarIkan, stok, idNelayan);
            adminDAO.addIkan(ikan);

            // Refresh Table
            handleInventoryButtonAction();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Fish data successfully added.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Invalid input format: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add fish data: " + e.getMessage());
            e.printStackTrace();
        }
    }
>>>>>>> bd75d9630d8ecb8c33188c8f6fd4cabdb6c341df:src/main/java/controller/AdminViewController.java

    @FXML
    private void handleDataJualButtonAction() {
        setVisibleForm(datajual_form);
<<<<<<< HEAD:src/main/java/controller/adminViewController.java
=======
        try {
            List<Pembelian> penjualanList = adminDAO.getAllPenjualan();
            datajual_table.setItems(FXCollections.observableArrayList(penjualanList));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load sales data: " + e.getMessage());
        }
>>>>>>> bd75d9630d8ecb8c33188c8f6fd4cabdb6c341df:src/main/java/controller/AdminViewController.java
    }

    @FXML
    private void handleDataBeliButtonAction() {
        setVisibleForm(databeli_form);
<<<<<<< HEAD:src/main/java/controller/adminViewController.java
=======
        try {
            List<Pembelian> pembelianList = adminDAO.getAllPembelian();
            databeli_table.setItems(FXCollections.observableArrayList(pembelianList));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load purchase data: " + e.getMessage());
        }
>>>>>>> bd75d9630d8ecb8c33188c8f6fd4cabdb6c341df:src/main/java/controller/AdminViewController.java
    }

    @FXML
    private void handleDataCustomerButtonAction() {
        setVisibleForm(datacustomer_form);
<<<<<<< HEAD:src/main/java/controller/adminViewController.java
=======
        try {
            List<Nelayan> pelangganList = adminDAO.getAllPelanggan();
            datacustomer_table.setItems(FXCollections.observableArrayList(pelangganList));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load customer data: " + e.getMessage());
        }
>>>>>>> bd75d9630d8ecb8c33188c8f6fd4cabdb6c341df:src/main/java/controller/AdminViewController.java
    }

    @FXML
    private void handleLogoutButtonAction() {
        try {
            logout_button.getScene().getWindow().hide();
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

    private void clearInventoryForm() {
        inventory_IDIkan.clear();
        inventory_namaIkan.clear();
        inventory_stok.clear();
        inventory_harga.clear();
        inventory_IDNelayan.clear();
        inventory_image_view.setImage(null);
    }

    private void loadDashboardData() {
        try {
            int totalCustomers = adminDAO.getTotalCustomers();
            Numberof_customer.setText(String.valueOf(totalCustomers));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load dashboard data: " + e.getMessage());
        }
    }

    private void loadInventoryData() {
        try {
            List<Ikan> ikanList = adminDAO.getAllIkan();
            inventory_table.setItems(FXCollections.observableArrayList(ikanList));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load inventory data: " + e.getMessage());
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
