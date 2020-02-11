package com.bowlingsim.scoreCard;

import com.bowlingsim.foodMenu.FoodMenuController;
import com.bowlingsim.msgBox.MsgBox;
import com.bowlingsim.settleTab.SettleTabController;
import javafx.application.Platform;
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


public class ScoreBoardController {


    public GridPane gridPane;
    public Button bowlBall;
    public MenuItem orderFoodMI;
    public MenuItem addPlayerMi;
    private BowlingAlleyController alley = new BowlingAlleyController();
    final Image APP_ICON = new Image("/com/bowlingsim/res/bowlingIcon.png");


    public void addRow(String playerName) {
        alley.addRows(gridPane, playerName);
        gridPane.setGridLinesVisible(true);
        bowlBall.setDisable(false);
    }

    public void bowlBall() {
        alley.updateScore(gridPane);
    }


    public void openFoodMenu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../fxml/foodMenu.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(APP_ICON);
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

    public void settleTab() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../fxml/settleTab.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.getIcons().add(APP_ICON);
            stage.setTitle("Settle Tab");
            stage.initModality(Modality.APPLICATION_MODAL);
            SettleTabController controller = fxmlLoader.getController();
            controller.onStart(alley.playerList);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void checkPlayers() {
        if (alley.playerList.size() == 0) {
            new MsgBox().showInfoBox("Zero Players", "You cannot order food or drinks without a tab", "To open a tab add a player", Alert.AlertType.WARNING);
            orderFoodMI.setVisible(false);


        } else {
            orderFoodMI.setVisible(true);
        }
    }

    public void addPlayer() {
        TextInputDialog playerAdd = new TextInputDialog("Enter Player Name");
        playerAdd.setHeaderText("");
        Stage playerAddBox = (Stage) playerAdd.getDialogPane().getScene().getWindow();
        playerAddBox.getIcons().add(APP_ICON);
        playerAdd.setTitle("Add Player");
        playerAdd.showAndWait();
        String userName = playerAdd.getEditor().getText();

        if (userName.equals("Enter Player Name") || userName.length() == 0) {
            new MsgBox().showInfoBox("No Name", "Please Add Player", "You must add the name of a player", Alert.AlertType.ERROR);

        } else {
            addRow(userName);
        }
    }

    public void help() {
        new MsgBox().showInfoBox("Help", "Start a game by adding players.", "For more info see: https://en.wikipedia.org/wiki/Ten-pin_bowling", Alert.AlertType.INFORMATION);
    }

    public void versionDisplay() {
        new MsgBox().showInfoBox("Version", "V0.8.9", "For Full Github listing see: https://github.com/Jamie-n/Bowling_Alley_Simulator", Alert.AlertType.INFORMATION);
    }

    public void exitApp() {
        Platform.exit();
    }
}
