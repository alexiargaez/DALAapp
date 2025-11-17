package edu.utsa.cs3443.dalaapp;

import edu.utsa.cs3443.dalaapp.model.AffirmationManager;
import edu.utsa.cs3443.dalaapp.model.Affirmation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Funny affirmation screen controller for the funny screen view.
 * @author kll111
 */
public class FunnyScreenController {

    @FXML
    private Label funnyAffirmationLabel;

    /**
     * Method to initialize a new AffirmationManager object
     * & the displayed affirmation.
     */
    @FXML
    public void initialize() {
        loadAffirmation();
    }


    /**
     * Method to send the user back to the main screen
     * @param event
     */
    @FXML
    void backButtonClicked(ActionEvent event) {
        launchScreen("menu", "Main Menu Screen");
    }


    /**
     * Method to send user to the user.fxml screen
     * to create their new Funny Affirmation.
     * @param event
     */
    @FXML
    void writeFunnyClicked(ActionEvent event) {
        launchScreen("user", "User-made Affirmations");
    }

    /**
     * Loads in a random Affirmation from the Funny category
     */
    private void loadAffirmation() {
        Affirmation affirmation = AffirmationManager.getInstance().getRandomAffirmationByCategory("Funny");

        if (affirmation != null) {
            funnyAffirmationLabel.setText(affirmation.getQuote());
        }
        else {
            funnyAffirmationLabel.setText("No Funny affirmations available.");
        }
    }

    /**
     * Takes the given params and launches the
     * corresponding screen view
     * @param fxml
     * @param title
     */
    private void launchScreen(String fxml, String title){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layouts/" + fxml +".fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }//idk i lowkey took this from the bankSys app



    /**
     * Temporary Getter for the FunnyAffirmationLabel
     * @return Label
     */
    public Label getFunnyAffirmationLabel() {
        return funnyAffirmationLabel;
    }

    /**
     * Temp Setter for the funnyAffirmationLabel
     * @param funnyAffirmationLabel
     */
    public void setFunnyAffirmationLabel(Label funnyAffirmationLabel) {
        this.funnyAffirmationLabel = funnyAffirmationLabel;
    }
}
