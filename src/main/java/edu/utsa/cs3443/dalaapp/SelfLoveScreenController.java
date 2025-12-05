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
 * Contains the logic behind the
 * self-love.fxml screen view.
 */
public class SelfLoveScreenController {

    @FXML private Label selfLoveAffirmationLabel; // fx:id

    /**
     * Initializes and loads a new
     * self-love affirmation.
     */
    @FXML
    public void initialize() { loadAffirmation(); }

    /**
     * Switches the screen to the menu.fxml screen
     * once the corresponding button is clicked.
     * @param e
     * @throws Exception
     */
    @FXML
    private void backButtonClicked(ActionEvent e) throws Exception {
        switchTo(e, "/edu/utsa/cs3443/dalaapp/layouts/menu.fxml", "DALA — Menu");
    }

    /**
     * Takes the user to the user.fxml screen
     * once the corresponding button is clicked.
     * @param e
     * @throws Exception
     */
    @FXML
    private void writeSelfLoveClicked(ActionEvent e) throws Exception {
        switchTo(e, "/edu/utsa/cs3443/dalaapp/layouts/user.fxml", "DALA — Write Your Own Affirmation!");
    }

    /**
     * Loads a new affirmation upon
     * next button interaction.
     */
    @FXML
    private void nextClicked() { loadAffirmation(); }

    /**
     * Handles the logic for loading a random
     * self-love affirmation
     */
    private void loadAffirmation() {
        Affirmation a = AffirmationManager.getInstance().getRandomAffirmationByCategory("Self-Love");
        selfLoveAffirmationLabel.setText(a != null ? a.getQuote() : "No Self-Love affirmations available.");
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
