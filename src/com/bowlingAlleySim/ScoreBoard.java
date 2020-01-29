package com.bowlingAlleySim;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

public class ScoreBoard extends Application {

    public GridPane gridPane;
    private BorderPane root;


    private MenuBar menuBar;
    private Menu players;
    private MenuItem addPlayers;


    private Button bowlBall;

    private final Integer NUMBER_OF_ROUNDS = 10;
    private final Integer MAX_PLAYERS = 8;


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

        //Generating the top row of the scoreboard
        for (int i = 0; i < NUMBER_OF_ROUNDS + 1; i++) {
            if (i == 0) {
                gridPane.add(new Label("Player Name  "), i, 0);
            } else {
                gridPane.add(new Label(Integer.toString(i)), i, 0);
                gridPane.add(new Label(" "), i, 0);
            }
        }

        //Formatting the scoreboard
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(30);
        gridPane.setGridLinesVisible(true);
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
                // addPlayerRow();
                addPlayerRow();

            }
        });

        bowlBall.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                BowlBallDisplay dialog = new BowlBallDisplay();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(primaryStage);
                dialog.show();
            }
        });

        Scene scene = new Scene(root, 500, 300);

        // Show the scene.
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bowling Score Card");
        primaryStage.show();

    }

    public void addPlayerRow() {

        Integer rowCount = gridPane.getRowCount();
        int numberOfPlayers = this.gridPane.getRowCount();

        if (numberOfPlayers != 8 + 1) {
            gridPane.add(new Label("Player " + rowCount), 0, rowCount);
            for (int i = 0; i < 10 + 1; i++) {
                gridPane.add(new Label(" "), i, rowCount);
            }
        }
        else{
            Alert tooManyPlayers = new Alert(Alert.AlertType.ERROR);
            tooManyPlayers.setTitle("Too Many Players");
            tooManyPlayers.setContentText("You can only have a maximum of 8 players");
            tooManyPlayers.setHeaderText("Remove Players");
            Toolkit.getDefaultToolkit().beep();
            tooManyPlayers.showAndWait();
        }


    }
}
