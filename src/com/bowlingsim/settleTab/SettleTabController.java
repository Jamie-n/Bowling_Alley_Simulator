package com.bowlingsim.settleTab;

import com.bowlingsim.scoreCard.player.BowlingPlayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


public class SettleTabController {

    public ComboBox<BowlingPlayer> tabSelectComboBox;
    public Label tabTotalLbl;
    public Button cardBtn;
    public Button cashBtn;

    private ObservableList<BowlingPlayer> bowlingPlayerObservableList = FXCollections.observableArrayList();
    private BowlingPlayer currentlySelected;


    public void onStart(ArrayList<BowlingPlayer> playerArrayList){

        bowlingPlayerObservableList.addAll(playerArrayList);

        tabSelectComboBox.setItems(bowlingPlayerObservableList);

    }

    public void tabSelected() {
        currentlySelected= tabSelectComboBox.getValue();
        updateTotal();
    }

    private void updateTotal(){
        tabTotalLbl.setText("Total: "+ NumberFormat.getCurrencyInstance(new Locale("en","GB")).format(currentlySelected.getCurrentTab()));

        if(currentlySelected.getCurrentTab() > 0) {
            cardBtn.setDisable(false);
            cashBtn.setDisable(false);
        }else{
            cardBtn.setDisable(true);
            cashBtn.setDisable(true);
        }
    }

    public void settleTabCash() {
        if (currentlySelected.getCurrentTab() > 0) {
            currentlySelected.settleTab();
            updateTotal();
            playCashSound();
        }
    }

    public void settleTabCard() {
        if (currentlySelected.getCurrentTab() > 0) {
            currentlySelected.settleTab();
            updateTotal();
            playCashSound();
        }
    }

    private void playCashSound(){
        AudioClip paySound;
        paySound = new AudioClip(this.getClass().getResource("cashSound.wav").toString());
        paySound.play();
    }
}
