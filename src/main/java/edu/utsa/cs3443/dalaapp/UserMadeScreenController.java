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

        @FXML private TextArea txtAffirmation;      // fx:id in Scene Builder
        @FXML private ComboBox<String> cmbCategory; // optional; if you didn’t add it, remove uses
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
            switchScene("/edu/utsa/cs3443/dalaapp/layouts/menu.fxml", "DALA — Menu");
        }


        private void goToCategory(String category) {
            String fxml, title;
            switch (category) {
                case "Self-Love" -> { fxml = "/edu/utsa/cs3443/dalaapp/layouts/self-love.fxml"; title = "DALA — Self-Love"; }
                case "Funny"     -> { fxml = "/edu/utsa/cs3443/dalaapp/layouts/funny.fxml";     title = "DALA — Funny"; }
                default          -> { fxml = "/edu/utsa/cs3443/dalaapp/layouts/motivational.fxml"; title = "DALA — Motivational"; }
            }
            switchScene(fxml, title);
        }

        private void switchScene(String fxmlPath, String title) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
                Stage stage = (Stage) (btnSave != null ? btnSave.getScene().getWindow()
                        : btnCancel.getScene().getWindow());
                stage.setScene(new Scene(root, 1000, 600));
                stage.setTitle(title);
                stage.show();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Navigation error:\n" + ex.getMessage()).showAndWait();
                ex.printStackTrace();
            }
        }
}
