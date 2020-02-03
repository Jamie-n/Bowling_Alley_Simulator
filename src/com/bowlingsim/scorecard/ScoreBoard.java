package com.bowlingsim.scorecard;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class ScoreBoard extends Application {
    @FXML
    public BorderPane root;
    public Button openWinner;
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

    public Scene scene;
    private Stage window;

        private ScoreBoardController alley = new ScoreBoardController();





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

    public void addRow(ActionEvent actionEvent) {
        alley.addRows(gridPane);
        gridPane.setGridLinesVisible(true);
        bowlBall.setDisable(false);
    }

    public void bowlBall(ActionEvent actionEvent) throws IOException {
        alley.updateScore(gridPane);
    }


    public void openFoodMenu(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../fxml/foodMenu.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void openDrinksMenu(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../fxml/drinkMenu.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void settleTab(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../fxml/settleTab.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




