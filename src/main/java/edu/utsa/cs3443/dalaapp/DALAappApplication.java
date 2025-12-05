package edu.utsa.cs3443.dalaapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

/**
 * starts the DALA application app
 */
public class DALAappApplication extends Application {
    /**
     * manages the main window
     * @param stage main application window
     * @throws Exception if the FXML cant be loaded
     */
    @Override
    public void start(Stage stage) throws Exception{
    Parent root = FXMLLoader.load(
            getClass().getResource("/edu/utsa/cs3443/dalaapp/layouts/welcome.fxml"));
        stage.setTitle("DALA — Daily Affirmations");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
}

    /**
     * launches the app
     * @param args command line arguments
     */
    public static void main(String[] args){
        launch(args);
    }
}
