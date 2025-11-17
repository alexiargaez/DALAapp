package edu.utsa.cs3443.dalaapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class DALAappApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome.fxml"));
        stage.setScene(new Scene(fx.load(), 1000,600));
        stage.setTitle("DALA");
        stage.show();
    }
    public static void main(String[] args){
        launch();
    }
}
