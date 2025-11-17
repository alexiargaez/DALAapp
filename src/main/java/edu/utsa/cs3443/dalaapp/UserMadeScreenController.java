package edu.utsa.cs3443.dalaapp;

import edu.utsa.cs3443.dalaapp.model.AffirmationManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * User-made Affirmation Screen Controller for the User-made screen view.
 */


    public class UserMadeScreenController {
        package edu.utsa.cs3443.dalaapp;

import edu.utsa.cs3443.dalaapp.model.AffirmationManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

    public class UserMadeScreenController {

        @FXML private TextArea txtAffirmation;
        @FXML private ComboBox<String> cmbCategory;
        @FXML private Button save;
        @FXML private Button cancel;

        @FXML
        public void initialize() {
            if (cmbCategory != null) {
                cmbCategory.getItems().setAll("Motivational", "Self-Love", "Funny");
                cmbCategory.getSelectionModel().select("Motivational");
            }
        }

        @FXML
        private void onSave() {
            String text = txtAffirmation.getText() == null ? "" : txtAffirmation.getText().trim();
            String cat  = (cmbCategory != null && cmbCategory.getValue() != null)
                    ? cmbCategory.getValue() : "Motivational";

            if (text.isBlank()) {
                new Alert(Alert.AlertType.WARNING, "Please type an affirmation.").showAndWait();
                return;
            }

            boolean ok = AffirmationManager.getInstance().addUserAffirmation(text, cat);
            if (!ok) {
                new Alert(Alert.AlertType.ERROR, "Could not save.").showAndWait();
                return;
            }
            goToCategory(cat);
        }

        @FXML
        private void onCancel() {
            switchScene("/edu/utsa/cs3443/dalaapp/layouts/menu.fxml");
        }

        private void goToCategory(String category) {
            String fxml = switch (category) {
                case "Self-Love" -> "/edu/utsa/cs3443/dalaapp/layouts/self-love.fxml";
                case "Funny"     -> "/edu/utsa/cs3443/dalaapp/layouts/funny.fxml";
                default          -> "/edu/utsa/cs3443/dalaapp/layouts/motivational.fxml";
            };
            switchScene(fxml);
        }

        private void switchScene(String fxmlPath) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
                Stage stage = (Stage) (save != null ? save.getScene().getWindow()
                        : cancel.getScene().getWindow());
                stage.setScene(new Scene(root, 1000, 600));
                stage.show();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Navigation error:\n" + ex.getMessage()).showAndWait();
                ex.printStackTrace();
            }
        }
    }



}
