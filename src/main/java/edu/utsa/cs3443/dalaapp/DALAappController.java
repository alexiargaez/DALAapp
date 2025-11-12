package edu.utsa.cs3443.dalaapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DALAappController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
