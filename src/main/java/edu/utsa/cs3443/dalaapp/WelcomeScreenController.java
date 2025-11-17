package edu.utsa.cs3443.dalaapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeScreenController {
    @FXML
    private Button start;

    @FXML
    public void initialize() {
        AffirmationManager.getInstance().loadAffirmations("/edu/utsa/cs3443/dalaapp/data/affirmations.csv");
    }

    @FXML
    private void onStart() {
    }
}
