package edu.utsa.cs3443.dalaapp;

import edu.utsa.cs3443.dalaapp.model.AffirmationManager;
import edu.utsa.cs3443.dalaapp.model.Affirmation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * User-made Affirmation Screen Controller for the User-made screen view.
 */
public class UserMadeScreenController {

        @FXML private TextArea txtAffirmation;      // fx:id in Scene Builder
        @FXML private ComboBox<String> cmbCategory; // optional; if you didn’t add it, remove uses
        @FXML private Button save;
        @FXML private Button cancel;

        @FXML
        private TextField userInputTextField;

        @FXML
        void backButtonClicked(ActionEvent event) {
            launchScreen("menu", "DALA — Menu");
        }

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
            launchScreen("menu", "DALA — Menu");
        }


        private void goToCategory(String category) {
            String fxml, title;
            switch (category) {
                case "Self-Love" -> { fxml = "/edu/utsa/cs3443/dalaapp/layouts/self-love.fxml"; title = "DALA — Self-Love"; }
                case "Funny"     -> { fxml = "/edu/utsa/cs3443/dalaapp/layouts/funny.fxml";     title = "DALA — Funny"; }
                default          -> { fxml = "/edu/utsa/cs3443/dalaapp/layouts/motivational.fxml"; title = "DALA — Motivational"; }
            }
            launchScreen(fxml, title);
        }

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
    }
}
