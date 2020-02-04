package com.bowlingsim.foodmenu;

import com.bowlingsim.scorecard.BowlingPlayer;
import com.bowlingsim.scorecard.ScoreBoardController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class FoodMenuController {


    public ComboBox<String> playerNameComboBox;
    public ComboBox<String> foodComboBox;
    public ListView<Label> basketListView;
    public Label basketTotal;
    public Button addItemButton;
    public Button orderFoodBtn;
    public ComboBox<String> drinkComboBox;

    Double shoppingBasketTotal = 0.00;
    private static DecimalFormat formatter = new DecimalFormat("0.00");
    private Stage root;

    private Boolean foodTabFocus;
    private Boolean drinkTabFocus;


    ScoreBoardController parentController = new ScoreBoardController();

    ArrayList<BowlingPlayer> playerList;
    ArrayList<CafeItem> foodShoppingBasket = new ArrayList<>();


    public void onStart(ArrayList<BowlingPlayer> tempList, Stage stage) {
        root = stage;
        GenerateMenuItems.createFoodItems();
        playerList = tempList;

        for (BowlingPlayer bowlingPlayer : playerList) {
            playerNameComboBox.getItems().add(bowlingPlayer.getName());
        }

        for (int i = 0; i < GenerateMenuItems.cafeItemList.size(); i++) {
            if (GenerateMenuItems.cafeItemList.get(i).getTypeOfItem().equals("food")) {
                foodComboBox.getItems().add(GenerateMenuItems.cafeItemList.get(i).getNameOfItem());
            }
        }
        for (int i = 0; i < GenerateMenuItems.cafeItemList.size(); i++) {
            if(GenerateMenuItems.cafeItemList.get(i).getTypeOfItem().equals("drink"))
            drinkComboBox.getItems().add(GenerateMenuItems.cafeItemList.get(i).getNameOfItem());
        }
    }

    public void addToOrder(ActionEvent actionEvent) {
        Boolean selection = true;
        if(foodTabFocus){
            C
        }


    }

    public void selectionMade(ActionEvent actionEvent) {
        addItemButton.setDisable(false);
    }

    public void allowOrderFood(ActionEvent actionEvent) {
        orderFoodBtn.setDisable(false);
    }

    public void makeOrder(ActionEvent actionEvent) {
        if (shoppingBasketTotal > 0) {
            for (BowlingPlayer player : playerList) {
                if (player.getName().equals(playerNameComboBox.getValue())) {
                    player.setCurrentTab(player.getCurrentTab() + shoppingBasketTotal);
                    player.itemsConsumed.addAll(foodShoppingBasket);
                    Alert noSelection = new Alert(Alert.AlertType.CONFIRMATION);
                    noSelection.setTitle("Order Placed");
                    noSelection.setContentText("The sub total for your order £" + formatter.format(shoppingBasketTotal) + " has been placed on " + player.getName() + "'s tab.");
                    noSelection.setHeaderText("Thank you for the order");
                    Toolkit.getDefaultToolkit().beep();
                    noSelection.showAndWait();
                    root.close();
                }
            }
        }
    }

    public void foodMenuFocus(MouseEvent mouseEvent) {
        foodTabFocus = true;
        drinkTabFocus = false;
    }

    public void drinkTabFocus(MouseEvent mouseEvent) {
        foodTabFocus = false;
        drinkTabFocus = true;
    }
}
