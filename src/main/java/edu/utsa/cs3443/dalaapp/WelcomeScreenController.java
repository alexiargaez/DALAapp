package edu.utsa.cs3443.dalaapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeScreenController {
    @FXML private Button start;

    @FXML
    public void initialize() {
        AppState.ensureLoaded(); }

    @FXML
    private void onStart() {
        SceneNav.go(start, "/edu/utsa/cs3443/dalaapp/layouts/menu.fxml", "DALA — Menu");
    }
}
