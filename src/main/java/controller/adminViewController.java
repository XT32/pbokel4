/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author imdaq
 */
public class adminViewController implements Initializable {

    @FXML
    private AnchorPane Dashboard_form;

    @FXML
    private Button Inventory_deleteButton;

    @FXML
    private AnchorPane Numberof_customer;

    @FXML
    private AnchorPane adminView;

    @FXML
    private Button dashboard_button;

    @FXML
    private Button dataBeli_button;

    @FXML
    private Button dataCustomer_button;

    @FXML
    private Button dataJual_button;

    @FXML
    private AnchorPane databeli_form;

    @FXML
    private AnchorPane datacustomer_form;

    @FXML
    private AnchorPane datajual_form;

    @FXML
    private Button display_button;

    @FXML
    private TableColumn<?, ?> inventory_IDikan;

    @FXML
    private Button inventory_annButton;

    @FXML
    private Button inventory_button;

    @FXML
    private Button inventory_clearButton;

    @FXML
    private AnchorPane inventory_form;

    @FXML
    private ImageView inventory_image_view;

    @FXML
    private Button inventory_importButton;

    @FXML
    private TableColumn<?, ?> inventory_jenisIkan;

    @FXML
    private TableColumn<?, ?> inventory_namaIkan;

    @FXML
    private TableColumn<?, ?> inventory_status;

    @FXML
    private TableColumn<?, ?> inventory_stok;

    @FXML
    private TableView<?> inventory_table;

    @FXML
    private TableColumn<?, ?> inventory_tanggalDitambahkan;

    @FXML
    private Button inventory_updateButton;

    @FXML
    private Button logout_button;

    @FXML
    private AnchorPane numberofsold;

    @FXML
    private AnchorPane scroll_dashboardForm;

    @FXML
    private TableView<?> tabeldataPenjualan;

    @FXML
    private AnchorPane todays_income;

    @FXML
    private AnchorPane total_income;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Tampilkan halaman Dashboard sebagai default saat aplikasi dibuka
        showDashboard();
    }

    // Method untuk menampilkan halaman Dashboard
    @FXML
    private void showDashboard() {
        setVisiblePage(Dashboard_form);
    }

    // Method untuk menampilkan halaman Inventory
    @FXML
    private void showInventory() {
        setVisiblePage(inventory_form);
    }

    // Method untuk menampilkan halaman Data Jual
    @FXML
    private void showDataJual() {
        setVisiblePage(datajual_form);
    }

    // Method untuk menampilkan halaman Data Beli
    @FXML
    private void showDataBeli() {
        setVisiblePage(databeli_form);
    }

    // Method untuk menampilkan halaman Data Customer
    @FXML
    private void showDataCustomer() {
        setVisiblePage(datacustomer_form);
    }

    // Utility method untuk menampilkan halaman tertentu dan menyembunyikan yang lain
    private void setVisiblePage(AnchorPane visiblePage) {
        // Sembunyikan semua halaman
        Dashboard_form.setVisible(false);
        inventory_form.setVisible(false);
        datajual_form.setVisible(false);
        databeli_form.setVisible(false);
        datacustomer_form.setVisible(false);

        // Tampilkan halaman yang dipilih
        visiblePage.setVisible(true);
    }
}
