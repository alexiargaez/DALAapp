package edu.utsa.cs3443.dalaapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuScreenController {

    private void switchTo(ActionEvent event, String fxmlPath, String title) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle(title);
        stage.show();
    }

    public void switchToFunnyScreenController(ActionEvent event) throws Exception {
        switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/funny.fxml", "DALA — Funny Affirmation");
    }

    public void switchToMotivationalScreenController(ActionEvent event) throws Exception {
        switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/motivational.fxml", "DALA — Motivational Affirmation");
    }

    public void switchToSelfLoveScreenController(ActionEvent event) throws Exception {
        switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/self-love.fxml", "DALA — Self-Love Affirmation");
    }

    public void switchToUserMadeScreenController(ActionEvent event) throws Exception {
        switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/user.fxml", "DALA — Write Your Own Affirmation!");
    }

    public void switchToRandomScreenController(ActionEvent event) throws Exception {
        switchTo(event, "/edu/utsa/cs3443/dalaapp/layouts/random.fxml", "DALA — Random Affirmation");
    }
}


