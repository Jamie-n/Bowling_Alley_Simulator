package com.bowlingsim.foodMenu;

import com.bowlingsim.msgBox.MsgBox;
import com.bowlingsim.scoreCard.BowlingPlayer;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FoodMenuController {


    public ComboBox<String> playerNameComboBox;
    public ComboBox<String> foodComboBox;
    public ListView<Label> basketListView;
    public Label basketTotal;
    public Button addItemButton;
    public Button orderFoodBtn;
    public ComboBox<String> drinkComboBox;
    public Button removeItemBtn;

    Double shoppingBasketTotal;
    NumberFormat cf = NumberFormat.getCurrencyInstance(new Locale("en","GB"));
    private Stage root;

    private Boolean foodTabFocus;
    private Boolean drinkTabFocus;


    private ArrayList<BowlingPlayer> playerList;
    private ArrayList<CafeItem> foodShoppingBasket = new ArrayList<>();


    public void onStart(ArrayList<BowlingPlayer> tempList, Stage stage) {
        root = stage;
        GenerateMenuItems.createFoodItems();
        playerList = tempList;
        foodTabFocus = true;

        for (BowlingPlayer bowlingPlayer : playerList) {
            playerNameComboBox.getItems().add(bowlingPlayer.getName());
        }

        for (int i = 0; i < GenerateMenuItems.cafeItemList.size(); i++) {
            if (GenerateMenuItems.cafeItemList.get(i).getTypeOfItem().equals("food")) {
                foodComboBox.getItems().add(GenerateMenuItems.cafeItemList.get(i).getNameOfItem());
            }
        }
        for (int i = 0; i < GenerateMenuItems.cafeItemList.size(); i++) {
            if (GenerateMenuItems.cafeItemList.get(i).getTypeOfItem().equals("drink"))
                drinkComboBox.getItems().add(GenerateMenuItems.cafeItemList.get(i).getNameOfItem());
        }
    }

    public void addToOrder() {
        for (CafeItem item : GenerateMenuItems.cafeItemList) {
            if (foodTabFocus) {
                if (item.getNameOfItem().equals(foodComboBox.getValue())) {
                    foodShoppingBasket.add(item);
                    break;
                }
            } else if (drinkTabFocus) {
                if (drinkComboBox.getValue().equals(item.getNameOfItem())) {
                    foodShoppingBasket.add(item);
                    break;
                }
            }
        }
        updateShoppingBasket();
    }




    public void selectionMade() {
        addItemButton.setDisable(false);
    }

    public void allowOrderFood() {
            orderFoodBtn.setDisable(false);
    }

    public void makeOrder() {
        if (foodShoppingBasket.size() > 0) {
            for (BowlingPlayer player : playerList) {
                if (player.getName().equals(playerNameComboBox.getValue())) {
                    player.setCurrentTab(player.getCurrentTab() + shoppingBasketTotal);
                    player.addAllItemsConsumed(foodShoppingBasket);
                    new MsgBox().showInfoBox("Order Placed","Thank you for the order","The sub total for your order " + cf.format(shoppingBasketTotal) + " has been placed on " + player.getName() + "'s tab.", Alert.AlertType.CONFIRMATION);

                    root.close();
                }
            }
        }else{
            new MsgBox().showInfoBox("No Items Ordered","Empty Selection","Please select some items", Alert.AlertType.ERROR);
        }
    }

    public void foodMenuFocus() {
        foodTabFocus = true;
        drinkTabFocus = false;
    }

    public void drinkTabFocus() {
        foodTabFocus = false;
        drinkTabFocus = true;
    }

    public void removeFromOrder() {
       ObservableList<Label> tempList;
        tempList = basketListView.getItems();

        for(Label item : tempList){
            if(item.getText().equals(foodShoppingBasket.get(foodShoppingBasket.size()-1).getNameOfItem())){
                foodShoppingBasket.remove(foodShoppingBasket.size()-1);
                updateShoppingBasket();
                break;
            }
        }
    }


    public void updateShoppingBasket(){
        basketListView.getItems().clear();
        shoppingBasketTotal = 0.00;
        if(foodShoppingBasket.size() == 0){
            removeItemBtn.setDisable(true);
        }else {
            removeItemBtn.setDisable(false);
        }

        for(CafeItem foodItem : foodShoppingBasket){
            basketListView.getItems().add(new Label(foodItem.getNameOfItem()));
            shoppingBasketTotal += foodItem.getPriceOfItem();
        }
        basketTotal.setText("Total " + cf.format(shoppingBasketTotal));
    }

}

