package com.bowlingAlleySim;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

class BowlBall extends Stage {

        private Button takeThrow;
        private Button cancelThrow;
        private HBox bottomBox;
        private BorderPane mainBorderBox;


        public BowlBall() {
            bottomBox = new HBox();
            mainBorderBox = new BorderPane();

            takeThrow = new Button("Take Throw");
            cancelThrow = new Button("Cancel Throw");


            bottomBox.getChildren().addAll(takeThrow,cancelThrow);
            bottomBox.setAlignment(Pos.BOTTOM_CENTER);

            mainBorderBox.setCenter(bottomBox);

            Scene bowlBall = new Scene(mainBorderBox,200,200);
            this.setScene(bowlBall);



            takeThrow.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println("Throw Taken");
                }
            });

            cancelThrow.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    BowlBall.this.close();
                }
            });


        }
    }