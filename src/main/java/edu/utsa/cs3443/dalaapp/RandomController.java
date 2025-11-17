package edu.utsa.cs3443.dalaapp;

import edu.utsa.cs3443.dalaapp.model.Affirmation;
import edu.utsa.cs3443.dalaapp.model.AffirmationManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.Random;

public class RandomController {

    @FXML
    private TextArea randomAffirmationLabel;

    private AffirmationManager affirmationManager;
    private final Random random = new Random();

    // Called from your Main/Menu controller after loading the FXML
    public void setAffirmationManager(AffirmationManager manager) {
        this.affirmationManager = manager;
        showRandomAffirmation();
    }

    @FXML
    private void handleNewRandom(ActionEvent event) {
        showRandomAffirmation();
    }

    private void showRandomAffirmation() {
        if (affirmationManager == null) {
            randomAffirmationLabel.setText("AffirmationManager not set.");
            return;
        }

        int count = affirmationManager.getAffirmationCount();   // you implement this in AffirmationManager
        if (count == 0) {
            randomAffirmationLabel.setText("No affirmations in the CSV.");
            return;
        }

        // IDs in CSV start at 1 and go up to count
        int randomId = random.nextInt(count) + 1; // 1..count

        Affirmation randomAffirmation = affirmationManager.getAffirmationById(randomId); // you implement this too
        if (randomAffirmation == null) {
            randomAffirmationLabel.setText("No affirmation found for id " + randomId);
            return;
        }

        // Use your Affirmation model's getQuote()
        randomAffirmationLabel.setText(randomAffirmation.getQuote());
    }

    @FXML
    private void handleBackToMenu(ActionEvent event) {
        System.out.println("Back to menu clicked.");
    }
}


