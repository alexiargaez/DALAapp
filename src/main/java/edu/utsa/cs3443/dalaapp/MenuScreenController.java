package edu.utsa.cs3443.dalaapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller class to handle the Menu screen view
 * @author Alesia Tonegari
 */
public class MenuScreenController {
    private Stage stage;
    private Scene scene;
    private Parent root;




    public void switchToFunnyScreenController(ActionEvent event) throws IOException{
        root = FXMLLoader.Load(getClass().getResource("funny.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    public void switchToMotivationalScreenController(ActionEvent event) throws IOException{
        root = FXMLLoader.Load(getClass().getResource("motivational.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToSelfLoveScreenController(ActionEvent event) throws IOException{
        root = FXMLLoader.Load(getClass().getResource("self-love.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToRandomScreenController(ActionEvent event) throws IOException{
        root = FXMLLoader.Load(getClass().getResource("random.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //i will add to manager class


    //public void start(Stage stage){
    //       try {
    //          Parent root = FXMLLoader.Load(getClass().getResource("Menu.fxml"));
    //           Scene scene = new Scene(root);
    //           stage.setScene(scene);
    //           stage.show();
    //}


}


