package com.bowlingAlleySim;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.NotActiveException;

public class ScoreBoard extends Application {

    public GridPane gridPane;
    private BorderPane root;



    private MenuBar menuBar;
    private Menu players;
    private MenuItem addPlayers;


    private Button bowlBall;
    private Button strikeButton;



    private final Integer NUMBER_OF_ROUNDS = 10;
    private final Integer MAX_PLAYERS = 8;

    private BowlingAlley alley = new BowlingAlley();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();

        bowlBall = new Button("Bowl The Ball");
        gridPane = new GridPane();
        menuBar = new MenuBar();
        players = new Menu("Players");
        addPlayers = new MenuItem("Add Player");




        //Formatting the scoreboard
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(50);
        gridPane.setPadding(new Insets(10,30,10,30));
        gridPane.setMaxWidth(1000);

        menuBar.getMenus().add(players);
        players.getItems().add(addPlayers);

        //Adding nodes to different parts of the borderPane
        root.setCenter(gridPane);
        root.setBottom(bowlBall);
        root.setTop(menuBar);


        //Setting Alignment
        root.setAlignment(bowlBall, Pos.BOTTOM_LEFT);
        root.setAlignment(gridPane, Pos.CENTER);




        addPlayers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                alley.addRows(gridPane);
            }
        });


        bowlBall.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    alley.updateScore(gridPane);
                }
        });



        Scene scene = new Scene(root, 1000, 300);

        // Show the scene.
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bowling Score Card");
        primaryStage.show();

    }



}


