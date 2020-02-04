package com.bowlingsim.foodmenu;

import com.bowlingsim.scorecard.BowlingPlayer;
import com.bowlingsim.scorecard.ScoreBoardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class FoodMenuController {

    public ListView lightSnackList;
    public Button passValueBack;
    ScoreBoardController parentController = new ScoreBoardController();

    ArrayList<BowlingPlayer> playerList;

    private String[] foodItems = {"Chips","Garlic Bread","Halloumi Fries","Crisps(Various Flavours)"};

    public void generateList() {
        for (int i = 0; i < foodItems.length; i++) {
            lightSnackList.getItems().addAll(new Label(foodItems[i]), new Button("Order"));
        }
    }

    public void returnValue(ActionEvent actionEvent) {
        lightSnackList.getItems().add(new Label(playerList.get(0).getName()));
    }

    public void passArrayList(ArrayList<BowlingPlayer> tempList){
        playerList = tempList;
        System.out.println(playerList.size());

    }

}
