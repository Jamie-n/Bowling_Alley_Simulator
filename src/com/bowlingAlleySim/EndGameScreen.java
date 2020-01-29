package com.bowlingAlleySim;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

class EndGameScreen extends Stage {

    private Label gameOverLabel;
    private Label scoreLabel;

    private HBox bottomBox;
    private HBox middleBox;
    private HBox topBox;

    private VBox boxContainer;

    private BorderPane mainBorderBox;


    public EndGameScreen() {

        topBox = new HBox();
        topBox.setAlignment(Pos.CENTER);
        middleBox = new HBox();
        middleBox.setAlignment(Pos.CENTER);
        bottomBox = new HBox();
        bottomBox.setAlignment(Pos.CENTER);


        boxContainer = new VBox();

        gameOverLabel = new Label("Game Over");
        gameOverLabel.setAlignment(Pos.CENTER);
        gameOverLabel.setFont(new Font("Arial", 30));



        topBox.getChildren().add(gameOverLabel);
        boxContainer.getChildren().addAll(topBox, middleBox, bottomBox);


        Scene bowlBall = new Scene(boxContainer, 200, 200);
        this.setScene(bowlBall);

    }

}