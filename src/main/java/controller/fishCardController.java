package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import model.Ikan;

public class fishCardController {

    @FXML
    private Button addButton;

    @FXML
    private AnchorPane card_form;

    @FXML
    private Label fishName;

    @FXML
    private Label fishPrice;

    @FXML
    private Spinner<Integer> fishSpinner;

    private Ikan ikan;
    private Runnable onAdd;

    public void setFishData(Ikan ikan, Runnable onAdd) {
        this.ikan = ikan;
        this.onAdd = onAdd;

        // Set data ke komponen kartu
        fishName.setText(ikan.getNamaIkan());
        fishPrice.setText("Price: Rp" + ikan.getHarga());
        fishSpinner.getValueFactory().setValue(1); // Default value
    }

    @FXML
    private void handleAddButton() {
        if (onAdd != null) {
            onAdd.run(); // Jalankan callback jika diperlukan
        }
        System.out.println("Added " + fishSpinner.getValue() + "x " + ikan.getNamaIkan());
    }
}
