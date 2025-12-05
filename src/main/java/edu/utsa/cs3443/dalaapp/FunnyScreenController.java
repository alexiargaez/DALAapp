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
 * controls the funny affirmation screen
 */
public class FunnyScreenController {

    @FXML private Label funnyAffirmationLabel;

    /**
     * runs when the screen is loaded(showing a funny affirmation)
     */
    @FXML
    public void initialize() {
        loadAffirmation();
    }

    /**
     * goes back to the main menu screen
     * @param e button click event
     * @throws Exception if the FXML cant be loaded
     */
    @FXML
    private void backButtonClicked(ActionEvent e) throws Exception {
        switchTo(e, "/edu/utsa/cs3443/dalaapp/layouts/menu.fxml", "DALA — Menu");
    }

    /**
     * opens the screen to write a custom funny affirmation
     * @param e button click event
     * @throws Exception if the FXML cant be loaded
     */
    @FXML
    private void writeFunnyClicked(ActionEvent e) throws Exception {
        switchTo(e, "/edu/utsa/cs3443/dalaapp/layouts/user.fxml", "DALA — Write Your Own Affirmation!");
    }

    /**
     * shows another random funny affirmation
     */
    @FXML
    private void nextClicked() { loadAffirmation(); }

    /**
     * loads a random funny affirmation and updates the label
     */
    private void loadAffirmation() {
        Affirmation a = AffirmationManager.getInstance().getRandomAffirmationByCategory("Funny");
        funnyAffirmationLabel.setText(a != null ? a.getQuote() : "No Funny affirmations available.");
    }

    /**
     *
     * @param event button click event
     * @param fxmlPath path to the FXML file
     * @param title window title
     * @throws Exception if the FXML cant be loaded
     */
    private void switchTo(ActionEvent event, String fxmlPath, String title) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle(title);
        stage.show();
    }
}
