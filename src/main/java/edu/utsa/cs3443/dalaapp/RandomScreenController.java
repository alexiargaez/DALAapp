package edu.utsa.cs3443.dalaapp;

import edu.utsa.cs3443.dalaapp.model.Affirmation;
import edu.utsa.cs3443.dalaapp.model.AffirmationManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//import java.util.Random;

public class RandomScreenController {
    private AffirmationManager affirmationManager;
    //private final Random random = new Random();

    @FXML private Label randomAffirmationLabel;

    @FXML
    public void initialize() {
        affirmationManager = AffirmationManager.getInstance();
        showRandomAffirmation();
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws Exception {
        switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/menu.fxml", "DALA — Menu");
    }

    @FXML
    private void writeRandomClicked(ActionEvent event) throws Exception{
        switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/user.fxml", "DALA — Write Your Own Affirmation!");
    }

    private void showRandomAffirmation() {
        if (affirmationManager == null) {
            randomAffirmationLabel.setText("AffirmationManager not set.");
            return;
        }

        int count = affirmationManager.getAffirmationCount();
        if (count == 0) {
            randomAffirmationLabel.setText("No affirmations available.");
            return;
        }

        Affirmation randomAffirmation = affirmationManager.getRandomAffirmation();

        if (randomAffirmation == null) {
            randomAffirmationLabel.setText("No affirmation found.");
            return;
        }

        randomAffirmationLabel.setText(randomAffirmation.getQuote());
    }

    private void switchTo(ActionEvent event, String fxmlPath, String title) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle(title);
        stage.show();
    }
}


