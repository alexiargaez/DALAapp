package edu.utsa.cs3443.dalaapp;

/**
 * Controller class to handle the Motivational screen view
 * @author Diana Cardona
 */
import edu.utsa.cs3443.dalaapp.model.Affirmation;
import edu.utsa.cs3443.dalaapp.model.AffirmationManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MotivationalScreenController {
    @FXML
    private Label MotivationalAffirmationLabel;

    @FXML
    public void initialize() {
        loadAffirmation();
    }//end initialize method

    private void loadAffirmation() {
        Affirmation affirmation = AffirmationManager.getInstance().getRandomAffirmationByCategory("Motivational");

        if (affirmation != null) {
            MotivationalAffirmationLabel.setText(affirmation.getQuote());
        }
        else {
            MotivationalAffirmationLabel.setText("No Motivational affirmations available.");
        }
    }//end loadAffirmation method


}
