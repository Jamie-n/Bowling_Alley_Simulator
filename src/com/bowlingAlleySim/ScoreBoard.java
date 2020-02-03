package com.bowlingAlleySim;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.NotActiveException;

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
    private BowlingAlley alley = new BowlingAlley();



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("bowlingSimulator.fxml"));



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

    public void bowlBall(ActionEvent actionEvent) {
        alley.updateScore(gridPane);
    }
}


