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

public class MotivationalScreenController {

    @FXML private Label motivationalAffirmationLabel;

    @FXML
    public void initialize() { loadAffirmation(); }

    @FXML
    private void backButtonClicked(ActionEvent e) throws Exception {
        switchTo(e, "/edu/utsa/cs3443/dalaapp/layouts/menu.fxml", "DALA — Menu");
    }

    @FXML
    private void writeMotivationalClicked(ActionEvent e) throws Exception {
        switchTo(e, "/edu/utsa/cs3443/dalaapp/layouts/user.fxml", "DALA — Write Your Own Affirmation!");
    }

    @FXML
    private void nextClicked() { loadAffirmation(); }

    private void loadAffirmation() {
        Affirmation a = AffirmationManager.getInstance().getRandomAffirmationByCategory("Motivational");
        motivationalAffirmationLabel.setText(a != null ? a.getQuote() : "No Motivational affirmations available.");
    }

    private void switchTo(ActionEvent event, String fxmlPath, String title) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle(title);
        stage.show();
    }


}
