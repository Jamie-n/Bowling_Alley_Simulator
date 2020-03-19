package com.bowlingsim.foodMenu;

import com.bowlingsim.foodMenu.menuItem.MenuItem;
import com.bowlingsim.foodMenu.generateItems.OnStartGenerate;
import com.bowlingsim.msgBox.MsgBox;
import com.bowlingsim.scoreCard.player.BowlingPlayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FoodMenuController {


    public ComboBox<BowlingPlayer> playerNameComboBox;
    public ComboBox<MenuItem> foodComboBox;
    public ComboBox<MenuItem> drinkComboBox;

    public ListView<MenuItem> basketListView;

    public Label basketTotal;

    public Button addItemButton;
    public Button orderFoodBtn;
    public Button removeItemBtn;

    Double shoppingBasketTotal = 0.00;
    NumberFormat cf = NumberFormat.getCurrencyInstance(new Locale("en", "GB"));
    private Stage root;

    private Boolean foodTabFocus;
    private int listViewIndex = 0;

    private ObservableList<MenuItem> foodObservable = FXCollections.observableArrayList();
    private ObservableList<MenuItem> drinksObservable = FXCollections.observableArrayList();
    private ObservableList<BowlingPlayer> bowlingPlayerObservableList = FXCollections.observableArrayList();


    private ArrayList<MenuItem> foodShoppingBasket = new ArrayList<>();


    public void onStart(ArrayList<BowlingPlayer> tempList, Stage stage) {

        drinksObservable.addAll(OnStartGenerate.getDrinksItem());
        foodObservable.addAll(OnStartGenerate.getFoodItem());
        bowlingPlayerObservableList.addAll(tempList);


        drinkComboBox.setItems(drinksObservable);
        foodComboBox.setItems(foodObservable);
        playerNameComboBox.setItems(bowlingPlayerObservableList);

        root = stage;
        foodTabFocus = true;
    }

    public void selectionMade() {
        addItemButton.setDisable(false);
    }

    public void allowOrderFood() {
        orderFoodBtn.setDisable(false);
    }


    public void addToOrder(ActionEvent actionEvent) {
        MenuItem itemToAdd;
        if (foodTabFocus) {
            itemToAdd = foodComboBox.getSelectionModel().getSelectedItem();
        } else {
            itemToAdd = drinkComboBox.getSelectionModel().getSelectedItem();
        }


        shoppingBasketTotal += itemToAdd.getPrice();
        basketListView.getItems().add(itemToAdd);
        foodShoppingBasket.add(itemToAdd);
        basketTotal.setText("Total " + cf.format(shoppingBasketTotal));

        removeItemBtn.setDisable(false);
    }


    public void makeOrder() {
        BowlingPlayer currTab = playerNameComboBox.getSelectionModel().getSelectedItem();
        if (playerNameComboBox.getSelectionModel().getSelectedItem() != null) {

            new MsgBox().showInfoBox("Order Placed", "Thank you for the order", "The sub total for your order " + cf.format(shoppingBasketTotal) + " has been placed on " + playerNameComboBox.getSelectionModel().getSelectedItem().getName() + "'s tab.", Alert.AlertType.CONFIRMATION);
            currTab.updateTab(shoppingBasketTotal);

            currTab.addAllItemsConsumed(foodShoppingBasket);
            root.close();

        } else {
            new MsgBox().showInfoBox("No Items Ordered", "Empty Selection", "Please select some items", Alert.AlertType.ERROR);
        }
    }


    public void foodMenuFocus() {
        foodTabFocus = true;
    }

    public void drinkTabFocus() {
        foodTabFocus = false;
    }

    public void removeFromOrder() {
        shoppingBasketTotal -= basketListView.getItems().get(listViewIndex).getPrice();
        basketListView.getItems().remove(listViewIndex);
        listViewIndex = 0;

        updateShoppingBasket();
    }


    public void updateShoppingBasket() {
        basketTotal.setText("Total " + cf.format(shoppingBasketTotal));

        if (basketListView.getItems().size() == 0) {
            removeItemBtn.setDisable(true);
        } else {
            removeItemBtn.setDisable(false);
        }
    }


    public void setNextIndex(MouseEvent mouseEvent) {
        listViewIndex = basketListView.getSelectionModel().getSelectedIndex();
    }
}

