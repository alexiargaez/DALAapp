package edu.utsa.cs3443.dalaapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class DALAappApplication extends Application {
    @Override
            public void start(Stage stage) throws Exception{
    Parent root = FXMLLoader.load(
            getClass().getResource("/edu/utsa/cs3443/dalaapp/layouts/welcome.fxml"));
        stage.setTitle("DALA — Daily Affirmations");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
}
    public static void main(String[] args){
        launch(args);
    }
}
