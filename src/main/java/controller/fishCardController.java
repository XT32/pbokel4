package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    @FXML
    private ImageView fishImage;

    private Ikan ikan;
    private Runnable onAdd;

    public void setFishData(Ikan ikan, Runnable onAdd) {
    this.ikan = ikan;
    this.onAdd = onAdd;

    // Set data ke komponen kartu
    fishName.setText(ikan.getNamaIkan());
    fishPrice.setText("Rp " + String.format("%,.0f", ikan.getHarga())); // Format harga
    fishSpinner.getValueFactory().setValue(1); // Default value

    // Set gambar jika ada
    if (ikan.getGambarIkan() != null && !ikan.getGambarIkan().isEmpty()) {
        Image image = new Image(getClass().getResourceAsStream("/images/" + ikan.getGambarIkan()));
        fishImage.setImage(image);
    }
}


    @FXML
    private void handleAddButton() {
        if (onAdd != null) {
            onAdd.run(); // Jalankan callback jika diperlukan
        }
        System.out.println("Added " + fishSpinner.getValue() + "x " + ikan.getNamaIkan());
    }
    
    
}
