package edu.utsa.cs3443.dalaapp;

import edu.utsa.cs3443.dalaapp.model.AffirmationManager;
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
    private AffirmationManager aManager;
    //private Stage funnyStage;

    /**
     * Method to initialize a new AffirmationManager object.
     */
    @FXML
    public void initialize() {
        aManager = AffirmationManager.getInstance();
    }

    @FXML
    private Label funnyAffirmationLabel;

    /**
     * Method to send the user back to the main screen
     * @param event
     */
    @FXML
    void backButtonClicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layouts/menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();


            MenuScreenController menuScreenController = fxmlLoader.getController();
            //menuScreenController.setMainStage(stage);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }



    /**
     * Method to send user to
     * @param event
     */
    @FXML
    void writeFunnyClicked(ActionEvent event) {

    }


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
