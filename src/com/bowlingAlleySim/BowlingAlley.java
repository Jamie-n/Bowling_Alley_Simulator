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
        if(playerList.get(currentPlayer).getGameOver()|| playerList.get(currentPlayer).getRoundsPlayed() ==11){
            System.out.println("Game over!");
        }else {

            int currentPlayerRound = playerList.get(currentPlayer).getRoundsPlayed();
            if (currentPlayerRound < (this.playerList.size() * 11) || playerList.get(currentPlayerRound).getThrow2(currentPlayerRound - 1) + playerList.get(currentPlayer).getThrow1(currentPlayerRound - 1) == 10) {

                ball.bowlBall(this.playerList.get(currentPlayer));
                calculateScore(playerList.get(currentPlayer), gridPane);

                currentPlayer += 1;
                currentRound += 1;
            }
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
            gridPane.add(new Label("0"), 12, rowCount);

            for (int i = 0; i < 11 + 1; i++) {
                gridPane.add(new Label(" "), i, rowCount);

            }
            this.addPlayer(rowCount.toString());
        }

    }


    public void calculateScore(BowlingPlayer player, GridPane gridPane) {
        Integer bonusPoints = 0;
        Integer currentRound = player.getRoundsPlayed() - 1;
        Integer totalForFrame = 0;
        Integer totalLastFrame = 0;
        Integer extraFrameSpare = 0;
        Integer extraFrameStrike = 0;



        if (currentRound >= 1 || player.getThrow2(currentRound) + player.getThrow1(currentRound) != 10) {

                totalForFrame = player.getThrow1(currentRound) + player.getThrow2(currentRound);

                if(currentRound >= 1) {
                    totalLastFrame = player.getThrow1(currentRound - 1) + player.getThrow2(currentRound - 1);

                    if(player.getThrow1(currentRound-1)==10){
                        player.setOnStrike(true);
                    }else{
                        player.setOnStrike(false);
                    }
                }
             if(currentRound >=9) {

                 //Gets the value for the bonus frame caused by a strike/spare
                extraFrameSpare = player.getThrow1(currentRound);
                extraFrameStrike = player.getThrow1(currentRound)+player.getThrow2(currentRound);

            }
             if(currentRound == 9 && totalForFrame != 10){
                 System.out.println("Game Over");
                 player.setGameOver(true);
                 player.throw1Array.add(currentRound + 1, 0);
                 player.throw2Array.add(currentRound + 1, 0);

             }
            System.out.println(totalForFrame);
            System.out.println(totalLastFrame);

            //Checks for a double spare at the start of the game
            if (player.throw1Array.size() >= 1 && (totalLastFrame) == 10 && (totalForFrame) == 10) {
                player.setOnSpare(true);
            } else {
                player.setOnSpare(false);
            }


            if(player.getOnStrike()) {
                bonusPoints = 10 + totalForFrame;
                player.setTotalScore(player.getTotalScore()+bonusPoints+totalForFrame);


            } else if (player.getOnSpare() || totalLastFrame == 10) {

                //Final round spare
                if (currentRound == 10 && totalLastFrame == 10) {
                    bonusPoints = 10 + extraFrameSpare;
                    player.setTotalScore(player.getTotalScore() + bonusPoints);
                    player.throw2Array.add(currentRound + 1, 0);
                    System.out.println(player.throw2Array.toString());
                } else {
                    //If only 1 spare has been thrown
                    if (!player.getOnSpare() && totalForFrame != 10) {
                        bonusPoints = 10 + player.getThrow1(currentRound);
                        System.out.println(bonusPoints);
                        player.setTotalScore(player.getTotalScore() + bonusPoints + (totalForFrame));
                        System.out.println("1");

                        //If a double spare or more was thrown
                    } else {
                        bonusPoints = 10 + player.getThrow1(currentRound);
                        player.setTotalScore(player.getTotalScore() + bonusPoints);
                        player.setOnSpare(false);
                        System.out.println("2");
                    }

                }
                //If only a single was thrown
            } else if (totalForFrame != 10) {
                player.setTotalScore(player.getTotalScore() + totalForFrame);

            }

        }



        ObservableList<Node> children = gridPane.getChildrenUnmodifiable();
        for (Node node : children) {
            if (node instanceof Label && gridPane.getRowIndex(node) == currentPlayer && gridPane.getColumnIndex(node) == 12) {
                ((Label) node).setText(String.valueOf(this.getScore(currentPlayer)));
            }
        }

        gridPane.add(new Label(new CalculateScoreSymbols().calculateSymbols(player.getThrow1(currentRound), player.getThrow2(currentRound), currentRound)), currentRound + 1, currentPlayer);


    }
}




