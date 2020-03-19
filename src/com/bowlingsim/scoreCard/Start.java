package com.bowlingsim.scoreCard;

import com.bowlingsim.foodMenu.generateItems.OnStartGenerate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


    public class Start extends Application {

        public Scene scene;
        public Stage window;

        public static void main(String[] args) {
            launch(args);
        }


        @Override
        public void start(Stage primaryStage) throws Exception {

            Parent root = FXMLLoader.load(getClass().getResource("../../fxml/scoreboard.fxml"));
            scene = new Scene(root, 750, 310);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Bowling Score Card");
            primaryStage.getIcons().add(new Image("/com/bowlingsim/res/bowlingIcon.png"));
            window = primaryStage;
            window.show();
            OnStartGenerate.generateMenuItems();
        }

    }


