package com.bowlingAlleySim;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


import java.awt.*;
import java.util.ArrayList;

public class BowlingAlley {

    public ArrayList<BowlingPlayer> playerList = new ArrayList<BowlingPlayer>();
    public int currentPlayer;
    public int currentRound;

    BowlingBall ball = new BowlingBall();

    public void addPlayer(String name) {
        playerList.add(new BowlingPlayer(name));
    }

    public String getName(Integer index) {
        return playerList.get(index).getName();
    }

    public Integer getScore(Integer index) {
        return playerList.get(index).getTotalScore();
    }

    public Integer getRoundsPlayed(Integer index) {
        return playerList.get(index).getRoundsPlayed();
    }

    public void setName(Integer index, String name) {
        playerList.get(index).setName(name);
    }

    public void setRoundsPlayed(Integer index, Integer round) {
        playerList.get(index).setRoundsPlayed(round);
    }


    public void updateScore(GridPane gridPane) {


        if (currentRound < (this.playerList.size() * 10)) {
            gridPane.add(new Label(ball.bowlBall(this.playerList.get(currentPlayer))), this.getRoundsPlayed(currentPlayer), Integer.parseInt(this.getName(currentPlayer)));
            calculateScore(playerList.get(currentPlayer),gridPane);

            currentPlayer += 1;
            currentRound += 1;

            if (gridPane.getRowCount() == currentPlayer) {
                currentPlayer = 0;
            }

        }
         else {
            Alert gameOver = new Alert(Alert.AlertType.CONFIRMATION);
            gameOver.setTitle("Game Over");
            gameOver.setContentText("Player "+ gameWinner().getName() +" Has won the game with a score of "+ gameWinner().getTotalScore());
            gameOver.setHeaderText("The Game Has Ended");
            Toolkit.getDefaultToolkit().beep();
            gameOver.showAndWait();



        }
    }

    public void addRows(GridPane gridPane) {
        Integer rowCount = gridPane.getRowCount();
        int numberOfPlayers = gridPane.getRowCount();
        if (numberOfPlayers != 8 + 1) {
            gridPane.add(new Label("Player " + rowCount), 0, rowCount);
            gridPane.add(new Label("0"), 11, rowCount);

            for (int i = 0; i < 10 + 1; i++) {
                gridPane.add(new Label(" "), i, rowCount);
            }
            this.addPlayer(rowCount.toString());

        } else {
            Alert tooManyPlayers = new Alert(Alert.AlertType.ERROR);
            tooManyPlayers.setTitle("Too Many Players");
            tooManyPlayers.setContentText("You can only have a maximum of 8 players");
            tooManyPlayers.setHeaderText("Remove Players");
            Toolkit.getDefaultToolkit().beep();
            tooManyPlayers.showAndWait();
        }
    }

    public BowlingPlayer gameWinner() {
        BowlingPlayer topScorer = new BowlingPlayer(null);

        for (BowlingPlayer item : playerList) {
            if (item.getTotalScore() > topScorer.getTotalScore()) {
                topScorer = item;
            }
        }

        System.out.println(topScorer.getName());
        return topScorer;
    }

    public void calculateScore(BowlingPlayer player, GridPane gridPane) {
        Integer bonusPoints = 0;

        if(player.getStrikeScored() >= 1 && currentRound != 0){
            bonusPoints = ((10+(player.getBowl1()+ player.getBowl2()))+ (player.getBowl1()+player.getBowl2()));
            player.setTotalScore(player.getTotalScore()+bonusPoints);
            player.setStrikeScored(player.getStrikeScored()-1);
        }
        else if (player.getStrikeScored() >=1) {
            player.setTotalScore(player.getTotalScore() + 10);
            player.setStrikeScored(0);
        }
        else if(player.getSpareScored() >=2 ){
            if(currentRound ==10){
                bonusPoints = 10;
                player.setTotalScore(player.getTotalScore() + bonusPoints);
                player.setSpareScored(player.getSpareScored() - 1);
            }
            else {
                bonusPoints = (10 + player.getBowl1() + (player.getBowl1() + player.getBowl2()));
                player.setTotalScore(player.getTotalScore() + bonusPoints);
                player.setSpareScored(player.getSpareScored() - 1);
            }
        }
/*
        if(player.getStrikeScored() > 0 && player.getSpareScored()> 0){
            if (player.getStrikeScored()==1 && player.getSpareScored()==1){
                player.setTotalScore(player.getTotalScore()+20);
                player.setSpareScored(0);
                player.setStrikeScored(0);
            }
        }
       else if(player.getStrikeScored() > 0) {
            bonusPoints = player.getStrikeScored()*10;
            if (player.getStrikeScored() > 2) {
                bonusPoints = 30;
                if (currentRound < 3 || currentRound < 3 * playerList.size()) {
                    player.setTotalScore(bonusPoints);
                }
                else {
                    player.setTotalScore(player.getTotalScore() + bonusPoints);
                }
                player.setStrikeScored(player.getStrikeScored() - 1);
            } else if (player.getStrikeScored() >= 1) {
                if(player.getSpareScored()>=1){
                }
                player.setTotalScore(player.getTotalScore() + player.getStrikeScored() * 10);

            }

        }
        else if(player.getSpareScore() == 0){
            player.setTotalScore(player.getTotalScore());
            System.out.println("No");
        }
        else if(player.getSpareScored() >= 2){
            bonusPoints = 10 + player.getSpareScore();
            player.setTotalScore(player.getTotalScore()+bonusPoints);
            player.setSpareScored(player.getSpareScored()-1);

        }
        else if(player.getSpareScored() == 1) {
            if (player.getSpareScore() == 0) {
                player.setTotalScore(player.getTotalScore() + 10);
            } else {
                bonusPoints = player.getSpareScore();
                player.setTotalScore(player.getTotalScore() + bonusPoints);
                player.setSpareScored(player.getSpareScored()-1);

        }
            }*/



            ObservableList<Node> children = gridPane.getChildrenUnmodifiable();
            for (Node node : children) {
                if (node instanceof Label && gridPane.getRowIndex(node) == currentPlayer && gridPane.getColumnIndex(node) == 11) {
                    ((Label) node).setText(String.valueOf(this.getScore(currentPlayer)));
                }


        }
    }
}

