package edu.utsa.cs3443.dalaapp;

import edu.utsa.cs3443.dalaapp.model.Affirmation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserMadeScreenController {

    @FXML private TextField inputAffirmationTextField;

    @FXML
    public void initialize() {

        /*
        if (cmbCategory != null) {
            cmbCategory.getItems().setAll("Motivational", "Self-Love", "Funny");
            cmbCategory.getSelectionModel().select("Motivational");
        }
        */
    }

    @FXML
    void backButtonClicked(ActionEvent e) throws Exception{
        switchScene(e,"/edu/utsa/cs3443/dalaapp/layouts/menu.fxml");
    }

    @FXML
    void onCancelClicked(ActionEvent e) throws Exception {
        switchScene(e,"/edu/utsa/cs3443/dalaapp/layouts/menu.fxml");
    }

    @FXML
    void onSaveClicked(ActionEvent event) {
        /*
        String text = inputAffirmationTextField.getText() == null ? "" : inputAffirmationTextField.getText().trim();
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
         */
    }


    /*
    private void goToCategory(String category) {
        String fxml = switch (category) {
            case "Self-Love" -> "/edu/utsa/cs3443/dalaapp/layouts/self-love.fxml";
            case "Funny"     -> "/edu/utsa/cs3443/dalaapp/layouts/funny.fxml";
            default          -> "/edu/utsa/cs3443/dalaapp/layouts/motivational.fxml";
        };
        switchScene(fxml);
    }
     */

    private void addNewAffirmation(Affirmation a){
        int id = a.getId() + 1;

        a.setId(id);
        a.setCategory("");
        a.setQuote("");
        a.setUserMade(true);
    }

    private void switchScene(ActionEvent e, String fxml) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

/*
    private void switchScene(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) (saveButton != null ? saveButton.getScene().getWindow()
                    : cancelButton.getScene().getWindow());
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Navigation error:\n" + ex.getMessage()).showAndWait();
            ex.printStackTrace();
        }

 */
}
