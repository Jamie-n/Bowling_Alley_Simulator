package com.bowlingsim.scorecard;

import com.bowlingsim.foodmenu.FoodMenuController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ScoreBoard extends Application {


    public Scene scene;
    public Stage window;


    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("../../fxml/scoreboard.fxml"));
        scene = new Scene(root, 750, 310);
        window.setScene(scene);
        window.setTitle("Bowling Score Card");
        window.show();


    }

}




