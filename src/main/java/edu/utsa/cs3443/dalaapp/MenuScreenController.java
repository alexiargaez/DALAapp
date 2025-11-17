package edu.utsa.cs3443.dalaapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
/**
 * Controller class to handle the Menu screen view
 * @author
 */
public class MenuScreenController {
    @FXML
    private Button start;

    @FXML
    public void initialized(){
        appState.ensureLoaded();
    }

    @FXML private void onStart(){
        SceneNav.go(start,"/edu/utsa/cs3443/dalaapp/layouts/menu.fxml", "DALA — Menu" )
    }
}
