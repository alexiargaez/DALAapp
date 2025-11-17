package edu.utsa.cs3443.dalaapp;

/**
 * Controller class to handle the Self-Love screen view
 * @author Diana Cardona
 */
import edu.utsa.cs3443.dalaapp.model.Affirmation;
import edu.utsa.cs3443.dalaapp.model.AffirmationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SelfLoveScreenController {
    @FXML
    private Label SelfLoveAffirmationLabel;

    @FXML
    public void initialize() {
        loadAffirmation();
    }//end initialize method

    private void loadAffirmation() {
        Affirmation affirmation = AffirmationManager.getInstance().getRandomAffirmationByCategory("Self-Love");

        if (affirmation != null) {
            SelfLoveAffirmationLabel.setText(affirmation.getQuote());
        }
        else {
            SelfLoveAffirmationLabel.setText("No Self-Love affirmations available.");
        }
    }// end loadAffirmation method
}//end SelfLoveScreenController class
