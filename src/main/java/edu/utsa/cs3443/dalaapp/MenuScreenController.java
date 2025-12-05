package edu.utsa.cs3443.dalaapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * controls the main menu screen
 */
public class MenuScreenController {

    /**
     * switches to another screen
     * @param event button click event
     * @param fxmlPath FXML file path
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

    /**
     * opens the funny affirmations screen
     * @param event
     * @throws Exception
     */
    public void switchToFunnyScreenController(ActionEvent event) throws Exception {
        switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/funny.fxml", "DALA — Funny Affirmation");
    }

    /**
     * opens the motivational affirmations screen
     * @param event
     * @throws Exception
     */
    public void switchToMotivationalScreenController(ActionEvent event) throws Exception {
        switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/motivational.fxml", "DALA — Motivational Affirmation");
    }

    /**
     * opens the self-love affirmations screen
     * @param event
     * @throws Exception
     */
    public void switchToSelfLoveScreenController(ActionEvent event) throws Exception {
        switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/self-love.fxml", "DALA — Self-Love Affirmation");
    }

    /**
     * opens write your own affirmations screen
     * @param event
     * @throws Exception
     */
    public void switchToUserMadeScreenController(ActionEvent event) throws Exception {
        switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/user.fxml", "DALA — Write Your Own Affirmation!");
    }


    /**
     * opens the random affirmations screen
     * @param event
     * @throws Exception
     */
    public void switchToRandomScreenController(ActionEvent event) throws Exception {
        switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/random.fxml", "DALA — Random Affirmation");
    }
}


