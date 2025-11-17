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

import java.util.Random;

public class RandomScreenController {

    @FXML
    private Label randomAffirmationLabel;

    private AffirmationManager affirmationManager;
    private final Random random = new Random();

    @FXML
    private void backButtonClicked(ActionEvent event) throws Exception {
        switchScene(event, "/edu/utsa/cs3443/dalaapp/layouts/menu.fxml");
    }

    @FXML
    private void writeRandomClicked(ActionEvent event) throws Exception{
        switchScene(event, "/edu/utsa/cs3443/dalaapp/layouts/user.fxml");
    }

    @FXML
    public void initialize() {
        loadAffirmation();
    }

    // Called from your Main/Menu controller after loading the FXML
    public void setAffirmationManager(AffirmationManager manager) {
        this.affirmationManager = manager;
        showRandomAffirmation();
    }

    /*
    @FXML
    private void handleNewRandom(ActionEvent event) {
        showRandomAffirmation();
    }
    */


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

    private void loadAffirmation() {
        Affirmation a = AffirmationManager.getInstance().getRandomAffirmationByCategory("Random");
        randomAffirmationLabel.setText(a != null ? a.getQuote() : "No Random affirmations available.");
    }

    private void switchScene(ActionEvent e, String fxml) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}


