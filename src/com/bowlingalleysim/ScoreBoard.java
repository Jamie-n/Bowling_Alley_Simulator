package com.bowlingalleysim;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ScoreBoard extends Application {
    @FXML
    private BorderPane root;
    @FXML
    private GridPane gridPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private GridPane ScoreboardHeader;
    @FXML
    private Button bowlBall;
    @FXML
    private VBox menuBox;
    @FXML
    private HBox topHbox;
    @FXML
    private Menu addPlayerMenu;
    @FXML
    private MenuItem addPlayerMi;
    @FXML
    private MenuItem removePlayerMI;
    @FXML
    private Menu cafeMenu;
    @FXML
    private MenuItem orderFoodMI;
    @FXML
    private MenuItem orderDrinkMI;
    @FXML
    private SeparatorMenuItem cafeSeperator;
    @FXML
    private MenuItem settleTabMI;
    @FXML
    private VBox scoreBoardVbox;
    @FXML
    private VBox headerVbox;
    @FXML
    private Label scoreboardHeader;
    @FXML
    private VBox scoreBoardContainer;

    private ScoreBoardController alley = new ScoreBoardController();



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("scoreboard.fxml"));

        Scene scene = new Scene(root, 750, 310);

        // Show the scene.
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bowling Score Card");
        primaryStage.show();

    }


    public void addRow(ActionEvent actionEvent) {
        alley.addRows(gridPane);
        gridPane.setGridLinesVisible(true);
    }

    public void bowlBall(ActionEvent actionEvent) throws IOException {
        alley.updateScore(gridPane);
    }
}


