package edu.utsa.cs3443.dalaapp;

import edu.utsa.cs3443.dalaapp.model.AffirmationManager;
import edu.utsa.cs3443.dalaapp.model.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeScreenController {

    @FXML private Button start;

    @FXML
    public void initialize() {
        // Initialize both managers
        AffirmationManager.getInstance();
        UserManager.getInstance();
    }

    @FXML
    private void onStart() {
        try {
            // Navigate to login screen
            Parent root = FXMLLoader.load(
                    getClass().getResource("/edu/utsa/cs3443/dalaapp/layouts/login.fxml")
            );
            Stage stage = (Stage) start.getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
            stage.setTitle("DALA — Login");
            stage.show();
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Could not open Login screen:\n" + ex.getMessage()).showAndWait();
            ex.printStackTrace();
        }
    }
}
