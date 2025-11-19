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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class UserMadeScreenController {
    private AffirmationManager affirmationManager;
    private String selectedCategory = "";
    private ToggleGroup categoryGroup;

    @FXML private TextField inputAffirmationTextField;
    @FXML private Label userMessageLabel;
    @FXML private RadioButton funnyRB;
    @FXML private RadioButton selfLoveRB;
    @FXML private RadioButton motivationalRB;

    @FXML
    public void initialize() {
        affirmationManager = AffirmationManager.getInstance();
        categoryGroup = new ToggleGroup();

        funnyRB.setToggleGroup(categoryGroup);
        selfLoveRB.setToggleGroup(categoryGroup);
        motivationalRB.setToggleGroup(categoryGroup);

        userMessageLabel.setText("");
    }

    @FXML
    void backButtonClicked(ActionEvent event) throws Exception{
        switchScene(event,"/edu/utsa/cs3443/dalaapp/layouts/menu.fxml");
    }

    @FXML
    void onCancelClicked(ActionEvent event) throws Exception {
        switchScene(event,"/edu/utsa/cs3443/dalaapp/layouts/menu.fxml");
    }

    @FXML
    void onSaveClicked(ActionEvent event) {
        String affirmationText = inputAffirmationTextField.getText();

        if (affirmationText == null || affirmationText.trim().isEmpty()) {
            userMessageLabel.setText("Please enter an affirmation below.");
            return;
        }

        if (selectedCategory.isEmpty()) {
            userMessageLabel.setText("Please select a category.");
            return;
        }

        boolean success = affirmationManager.addUserAffirmation(affirmationText, selectedCategory);
        if (success) {
            userMessageLabel.setText("New Affirmation Added Successfully!");
            inputAffirmationTextField.clear();

            selectedCategory = "";
            if (categoryGroup != null) {
                categoryGroup.selectToggle(null);
            }
        } else {
            userMessageLabel.setText("New Affirmation Could Not Be Added.");
        }
    }

    @FXML
    void selfLoveRBClicked(ActionEvent event) {
        selectedCategory = "Self-Love";
        userMessageLabel.setText("");
    }

    @FXML
    void funnyRBClicked(ActionEvent event) {
        selectedCategory = "Funny";
        userMessageLabel.setText("");
    }

    @FXML
    void motivationalRBClicked(ActionEvent event) {
        selectedCategory = "Motivational";
        userMessageLabel.setText("");
    }

    private void switchScene(ActionEvent e, String fxml) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
