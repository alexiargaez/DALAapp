package edu.utsa.cs3443.dalaapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DALAappController {


    private final AffirmationManager manager = new AffirmationManager();

    @FXML
    public void initialize(){
        manager.loadAffirmations("/edu/utsa/cs3443/dalaapp/data/affirmations.csv");

        Affirmation a = manager.getRandomAffirmation();
        if (a != null) affirmation.setText(a.getQuote());
    }
    @FXML
    private void onWelcome(ActionEvent e) {
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/dalaapp/layouts/menu.fxml"));
        Scene menu = new Scene(fx.load());
        stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(menu);
        stage.setTitle("DALA - Menu");
    }
}
