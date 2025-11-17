package edu.utsa.cs3443.dalaapp;

import edu.utsa.cs3443.dalaapp.model.AffirmationManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeScreenController {
    @FXML
    private Button start;

    @FXML
    public void initialize() {
    AffirmationManager.getInstance();
    }

    @FXML
    private void onStart(ActionEvent event) {
        try {
            FXMLLoader fx = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/dalaapp/layouts/menu.fxml"));
            Parent root = fx.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 600));
            stage.setTitle("DALA — Menu");
            stage.show();
        }
        catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR,"Could not open Menu screen:\n" + ex.getMessage()).showAndWait();
            ex.printStackTrace();
        }
    }
}
