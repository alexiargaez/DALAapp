
package edu.utsa.cs3443.dalaapp;

import edu.utsa.cs3443.dalaapp.model.AffirmationManager;
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
        AffirmationManager.getInstance();
    }

    @FXML
    private void onStart() {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/edu/utsa/cs3443/dalaapp/layouts/menu.fxml")
            );
            Stage stage = (Stage) start.getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
            stage.setTitle("DALA — Menu");
            stage.show();
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Could not open Menu:\n" + ex.getMessage()).showAndWait();
            ex.printStackTrace();
        }
    }
}
