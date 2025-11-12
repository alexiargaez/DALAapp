package edu.utsa.cs3443.dalaapp;

import edu.utsa.cs3443.dalaapp.model.Affirmation;
import edu.utsa.cs3443.dalaapp.model.AffirmationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DALAappController {

    private final AffirmationManager manager = new AffirmationManager();

    @FXML
    public void initialize(){
        manager.loadAffirmations("/edu/utsa/cs3443/dalaapp/data/affirmations.csv");

        Affirmation a = manager.getRandomAffirmation();
        if (a != null) affirmation.setText(a.getQuote());
    }
    @FXML
    private Label affirmation;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
