// Funny affirmation screen controller for the funny screen view.
// covered by Lauren
package edu.utsa.cs3443.dalaapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FunnyScreenController {
    @FXML
    private Label funnyAffirmationLabel;

    @FXML
    void backButtonClicked(ActionEvent event) {

    }

    @FXML
    void writeFunnyClicked(ActionEvent event) {

    }


    /**
     * Temporary Getter for the FunnyAffirmationLabel
     * @return Label
     */
    public Label getFunnyAffirmationLabel() {
        return funnyAffirmationLabel;
    }

    /**
     * Temp Setter for the funnyAffirmationLabel
     * @param funnyAffirmationLabel
     */
    public void setFunnyAffirmationLabel(Label funnyAffirmationLabel) {
        this.funnyAffirmationLabel = funnyAffirmationLabel;
    }
}
