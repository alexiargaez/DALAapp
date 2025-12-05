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

/**
 * Controls the logic behind the
 * random.fxml screen view.
 */
public class RandomScreenController {
    private AffirmationManager affirmationManager;

    @FXML private Label randomAffirmationLabel;

    /**
     * Instantiates a new AffirmationManager object
     * and loads a random affirmation
     */
    @FXML
    public void initialize() {
        affirmationManager = AffirmationManager.getInstance();
        showRandomAffirmation();
    }

    /**
     * Switches the screen to the menu.fxml screen
     * once the corresponding button is clicked.
     * @param event
     * @throws Exception
     */
    @FXML
    private void backButtonClicked(ActionEvent event) throws Exception {
        switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/menu.fxml", "DALA — Menu");
    }

    /**
     * Takes the user to the user.fxml screen
     * once the corresponding button is clicked.
     * @param event
     * @throws Exception
     */
    @FXML
    private void writeRandomClicked(ActionEvent event) throws Exception{
        switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/user.fxml", "DALA — Write Your Own Affirmation!");
    }

    /**
     * Contains the logic for pulling a random
     * affirmation from the file and displaying
     * it on the screen.
     */
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

    /**
     * Handles the logic for switching the
     * screen view.
     * @param event
     * @param fxmlPath
     * @param title
     * @throws Exception
     */
    private void switchTo(ActionEvent event, String fxmlPath, String title) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle(title);
        stage.show();
    }
}


