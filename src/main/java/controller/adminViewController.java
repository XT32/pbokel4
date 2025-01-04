package controller;

import dao.AdminDAO;
import model.Ikan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class adminViewController implements Initializable {

    @FXML
    private AnchorPane Dashboard_form, inventory_form;

    @FXML
    private TableView<Ikan> inventory_table;

    @FXML
    private TableColumn<Ikan, Number> inventory_IDikan; // Menggunakan Number untuk IntegerProperty
    @FXML
    private TableColumn<Ikan, String> inventory_namaIkan, inventory_gambarIkan;
    @FXML
    private TableColumn<Ikan, Number> inventory_hargaIkan, inventory_stokIkan, inventory_idNelayan;

    @FXML
    private TextField inventory_namaField, inventory_hargaField, inventory_gambarField, inventory_stokField, inventory_idNelayanField;

    @FXML
    private Button inventory_annButton, inventory_updateButton, inventory_deleteButton, inventory_clearButton;

    @FXML
    private ImageView inventory_image_view;

    private Connection connection;
    private AdminDAO adminDAO;
    private ObservableList<Ikan> ikanList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectToDatabase();
        adminDAO = new AdminDAO(connection);

        // Set kolom tabel inventory
        inventory_IDikan.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        inventory_namaIkan.setCellValueFactory(cellData -> cellData.getValue().namaProperty());
        inventory_hargaIkan.setCellValueFactory(cellData -> cellData.getValue().hargaProperty());
        inventory_gambarIkan.setCellValueFactory(cellData -> cellData.getValue().gambarIkanProperty());
        inventory_stokIkan.setCellValueFactory(cellData -> cellData.getValue().stokProperty());
        inventory_idNelayan.setCellValueFactory(cellData -> cellData.getValue().idNelayanProperty());

        loadInventoryData();

        // CRUD Button Actions
        inventory_annButton.setOnAction(e -> addIkan());
        inventory_updateButton.setOnAction(e -> updateIkan());
        inventory_deleteButton.setOnAction(e -> deleteIkan());
        inventory_clearButton.setOnAction(e -> clearInventoryFields());
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fishmarket", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadInventoryData() {
        try {
            List<Ikan> list = adminDAO.getAllIkan();
            ikanList = FXCollections.observableArrayList(list);
            inventory_table.setItems(ikanList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addIkan() {
        try {
            String nama = inventory_namaField.getText();
            double harga = Double.parseDouble(inventory_hargaField.getText());
            String gambar = inventory_gambarField.getText();
            int stok = Integer.parseInt(inventory_stokField.getText());
            int idNelayan = Integer.parseInt(inventory_idNelayanField.getText());

            Ikan ikan = new Ikan(0, nama, harga, gambar, stok, idNelayan);
            adminDAO.addIkan(ikan);
            loadInventoryData();
            clearInventoryFields();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Data ikan berhasil ditambahkan.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan saat menambahkan data ikan.");
        }
    }

    private void updateIkan() {
        try {
            Ikan selectedIkan = inventory_table.getSelectionModel().getSelectedItem();
            if (selectedIkan == null) {
                showAlert(Alert.AlertType.ERROR, "Selection Error", "Pilih ikan yang ingin diperbarui.");
                return;
            }

            String nama = inventory_namaField.getText();
            double harga = Double.parseDouble(inventory_hargaField.getText());
            String gambar = inventory_gambarField.getText();
            int stok = Integer.parseInt(inventory_stokField.getText());
            int idNelayan = Integer.parseInt(inventory_idNelayanField.getText());

            selectedIkan.setNama(nama);
            selectedIkan.setHarga(harga);
            selectedIkan.setGambarIkan(gambar);
            selectedIkan.setStok(stok);
            selectedIkan.setIdNelayan(idNelayan);

            adminDAO.updateIkan(selectedIkan);
            loadInventoryData();
            clearInventoryFields();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Data ikan berhasil diperbarui.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan saat memperbarui data ikan.");
        }
    }

    private void deleteIkan() {
        try {
            Ikan selectedIkan = inventory_table.getSelectionModel().getSelectedItem();
            if (selectedIkan == null) {
                showAlert(Alert.AlertType.ERROR, "Selection Error", "Pilih ikan yang ingin dihapus.");
                return;
            }

            adminDAO.deleteIkan(selectedIkan.getId());
            loadInventoryData();
            clearInventoryFields();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Data ikan berhasil dihapus.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan saat menghapus data ikan.");
        }
    }

    private void clearInventoryFields() {
        inventory_namaField.clear();
        inventory_hargaField.clear();
        inventory_gambarField.clear();
        inventory_stokField.clear();
        inventory_idNelayanField.clear();
        inventory_image_view.setImage(null);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
