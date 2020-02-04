package com.bowlingsim.scorecard;

import com.bowlingsim.foodmenu.FoodMenuController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ScoreBoardController {


    public GridPane gridPane;
    public Button bowlBall;
    public MenuItem orderFoodMI;
    public MenuItem orderDrinkMI;
    private BowlingAlleyController alley = new BowlingAlleyController();




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
                FoodMenuController controller = fxmlLoader.getController();
                controller.generateList();
                controller.passArrayList(alley.playerList);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    public void addItem(){



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


    public void checkPlayers(Event event) {
        if (alley.playerList.size() == 0) {
            Alert noPlayers = new Alert(Alert.AlertType.WARNING);
            noPlayers.setTitle("Zero Players");
            noPlayers.setContentText("To open a tab create a player");
            noPlayers.setHeaderText("You cannot order food or drinks without a tab.");
            noPlayers.showAndWait();

            orderFoodMI.setVisible(false);
            orderDrinkMI.setVisible(false);

        }else{
            orderDrinkMI.setVisible(true);
            orderFoodMI.setVisible(true);
        }
    }
}
