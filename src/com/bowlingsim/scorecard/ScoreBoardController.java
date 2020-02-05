package com.bowlingsim.scorecard;

import com.bowlingsim.foodmenu.FoodMenuController;
import com.bowlingsim.msgbox.MsgBox;
import com.bowlingsim.settletab.SettleTabController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class ScoreBoardController {


    public GridPane gridPane;
    public Button bowlBall;
    public MenuItem orderFoodMI;
    public MenuItem orderDrinkMI;
    public MenuItem addPlayerMi;
    private BowlingAlleyController alley = new BowlingAlleyController();


    public void addRow(String playerName) {
        alley.addRows(gridPane, playerName);
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
            stage.getIcons().add(new Image("/com/bowlingsim/res/bowlingIcon.png"));
            stage.setTitle("Order Food");
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            FoodMenuController controller = fxmlLoader.getController();
            controller.onStart(alley.playerList, stage);
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
            stage.getIcons().add(new Image("/com/bowlingsim/res/bowlingIcon.png"));
            stage.setTitle("Settle Tab");
            stage.initModality(Modality.APPLICATION_MODAL);
            SettleTabController controller = fxmlLoader.getController();
            controller.onStart(alley.playerList);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void checkPlayers(Event event) {
        if (alley.playerList.size() == 0) {
            new MsgBox().showInfoBox("Zero Players","You cannot order food or drinks without a tab","To open a tab add a player", Alert.AlertType.WARNING);
            orderFoodMI.setVisible(false);
            orderDrinkMI.setVisible(false);

        } else {
            orderDrinkMI.setVisible(true);
            orderFoodMI.setVisible(true);
        }
    }

    public void addPlayer(ActionEvent actionEvent) {
        TextInputDialog playerAdd = new TextInputDialog("Enter Player Name");
        playerAdd.setHeaderText("");
        playerAdd.setTitle("Add Player");
        playerAdd.showAndWait();

        if (playerAdd.getEditor().getText().equals("Enter Player Name")) {
            new MsgBox().showInfoBox("No Name","Please Add Player","You must add the name of a player", Alert.AlertType.ERROR);

        } else {
            addRow(playerAdd.getEditor().getText());
        }
    }
}
