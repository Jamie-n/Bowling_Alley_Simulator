package com.bowlingsim.settletab;

import com.bowlingsim.scorecard.BowlingPlayer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


public class SettleTabController {

    public ComboBox tabSelectComboBox;
    public Label tabTotalLbl;
    public Button cardBtn;
    public Button cashBtn;
    private ArrayList<BowlingPlayer> playerList  = new ArrayList();
    BowlingPlayer currentlySelected;


    public void onStart(ArrayList<BowlingPlayer> playerArrayList){
        playerList = playerArrayList;

        for(BowlingPlayer player : playerList){
            tabSelectComboBox.getItems().add(player.getName());
        }

    }

    public void tabSelected(ActionEvent actionEvent) {
        for(BowlingPlayer player : playerList){
            if(player.getName().equals(tabSelectComboBox.getValue())){
                currentlySelected = player;
                updateTotal();
                break;
            }
        }
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

    public void settleTabCash(ActionEvent actionEvent) {
        if (currentlySelected.getCurrentTab() > 0) {
            currentlySelected.setCurrentTab(0.00);
            System.out.println("Tab Settled");
            updateTotal();
            playCashSound();
        }
    }

    public void settleTabCard(ActionEvent actionEvent) {
        if (currentlySelected.getCurrentTab() > 0) {
            currentlySelected.setCurrentTab(0.00);
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
