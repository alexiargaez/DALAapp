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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserMadeScreenController {
    private AffirmationManager affirmationManager;

    @FXML private TextField inputAffirmationTextField;
    @FXML private Label userMessageLabel;

    @FXML
    public void initialize() {
        /* need code here */
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
        if(inputAffirmationTextField == null){
           userMessageLabel.setText("Please enter an affirmation below.");
        }

        /* need code here */
    }

    //NOTE: this method does NAWT work yet.. but trust, it will.
    private void addNewAffirmation(Affirmation a){
        int id = a.getId() + 1;

        a.setId(id);
        a.setCategory("");
        a.setQuote("");
        a.setUserMade(true);

        if (affirmationManager.addUserAffirmation("","")){
            userMessageLabel.setText("New Affirmation Added Successfully!");
        } else {
            userMessageLabel.setText("New Affirmation Could Not Be Added.");
        }
    }

    private void switchScene(ActionEvent e, String fxml) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
