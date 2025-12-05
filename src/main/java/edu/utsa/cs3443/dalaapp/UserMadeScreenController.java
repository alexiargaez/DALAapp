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

/**
 * Contains the logic behind the
 * user.fxml screen view
 */
public class UserMadeScreenController {
    private AffirmationManager affirmationManager;
    private String selectedCategory = "";
    private ToggleGroup categoryGroup;

    @FXML private TextField inputAffirmationTextField;
    @FXML private Label userMessageLabel;
    @FXML private RadioButton funnyRB;
    @FXML private RadioButton selfLoveRB;
    @FXML private RadioButton motivationalRB;

    /**
     * Initializes the necessary objects
     * and whatnot.
     */
    @FXML
    public void initialize() {
        affirmationManager = AffirmationManager.getInstance();
        categoryGroup = new ToggleGroup();

        funnyRB.setToggleGroup(categoryGroup);
        selfLoveRB.setToggleGroup(categoryGroup);
        motivationalRB.setToggleGroup(categoryGroup);

        userMessageLabel.setText("");
    }

    /**
     * Switches the screen to the menu.fxml screen
     * once the corresponding button is clicked.
     * @param event
     * @throws Exception
     */
    @FXML
    void backButtonClicked(ActionEvent event) throws Exception{
        switchTo(event,"/edu/utsa/cs3443/dalaapp/layouts/menu.fxml", "DALA — Menu");
    }

    /**
     * Switches the screen to the menu.fxml screen
     * once the corresponding button is clicked.
     * @param event
     * @throws Exception
     */
    @FXML
    void onCancelClicked(ActionEvent event) throws Exception {
        switchTo(event,"/edu/utsa/cs3443/dalaapp/layouts/menu.fxml", "DALA — Menu");
    }

    /**
     * Saves the user's input as a new
     * affirmation if every field required
     * is filled out.
     * @param event
     */
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

    /**
     * Assigns the newly made affirmation to
     * the self-love category.
     * @param event
     */
    @FXML
    void selfLoveRBClicked(ActionEvent event) {
        selectedCategory = "Self-Love";
        userMessageLabel.setText("");
    }

    /**
     * Assigns the newly made affirmation to
     * the funny category.
     * @param event
     */
    @FXML
    void funnyRBClicked(ActionEvent event) {
        selectedCategory = "Funny";
        userMessageLabel.setText("");
    }

    /**
     * Assigns the newly made affirmation to
     * the motivational category.
     * @param event
     */
    @FXML
    void motivationalRBClicked(ActionEvent event) {
        selectedCategory = "Motivational";
        userMessageLabel.setText("");
    }

    /**
     * Handles the logic for switching the
     * screen view.
     * @param event
     * @param fxmlPath
     * @param title
     * @throws Exception
     */
    private void switchTo(ActionEvent event, String fxmlPath, String title) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle(title);
        stage.show();
    }
}
