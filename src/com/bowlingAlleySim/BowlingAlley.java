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
    private int currentRound;


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
            calculateScore(playerList.get(currentPlayer), gridPane);

            currentPlayer += 1;
            currentRound += 1;


            if (gridPane.getRowCount() == currentPlayer) {
                currentPlayer = 0;
            }
            
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
        }
    }


    public void calculateScore(BowlingPlayer player, GridPane gridPane) {
        Integer bonusPoints = 0;
        Integer currentRound = player.getRoundsPlayed() - 1;


        if (currentRound >= 1 || player.getThrow2(currentRound) + player.getThrow1(currentRound) != 10) {


            //Checks for a double spare at the start of the game
            if (player.throw1Array.size() > 1 && (player.getThrow2(currentRound - 1) + player.getThrow1(currentRound - 1)) == 10 && (player.getThrow1(currentRound) + player.getThrow2(currentRound)) == 10) {
                player.setOnSpare(true);
            } else {
                player.setOnSpare(false);
            }

            if (player.getOnSpare() || player.getThrow1(currentRound - 1) + player.getThrow2(currentRound - 1) == 10) {
                //If only 1 spare has been thrown
                if (!player.getOnSpare() || player.getThrow1(currentRound) + player.getThrow2(currentRound) != 10) {
                    bonusPoints = 10 + player.getThrow1(currentRound);
                    player.setTotalScore(player.getTotalScore() + bonusPoints + (player.getThrow1(currentRound) + player.getThrow2(currentRound)));

                    //If a double spare or more was thrown
                } else {
                    bonusPoints = player.getThrow1(currentRound);
                    player.setTotalScore(player.getTotalScore() + bonusPoints + (player.getThrow1(currentRound) + player.getThrow2(currentRound)));
                    player.setOnSpare(false);

                }
                //If only a single was thrown
            } else if (player.getThrow1(currentRound) + player.getThrow2(currentRound) != 10) {
                player.setTotalScore(player.getTotalScore() + (player.getThrow1(currentRound) + player.getThrow2(currentRound)));
            }

        }

        ObservableList<Node> children = gridPane.getChildrenUnmodifiable();
        for (Node node : children) {
            if (node instanceof Label && gridPane.getRowIndex(node) == currentPlayer && gridPane.getColumnIndex(node) == 11) {
                ((Label) node).setText(String.valueOf(this.getScore(currentPlayer)));
            }


        }
    }
}




