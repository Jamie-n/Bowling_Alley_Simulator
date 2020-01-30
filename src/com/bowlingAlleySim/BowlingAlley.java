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

        } else {
            Alert gameOver = new Alert(Alert.AlertType.CONFIRMATION);
            gameOver.setTitle("Game Over");
            gameOver.setContentText("Player " + gameWinner().getName() + " Has won the game with a score of " + gameWinner().getTotalScore());
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
        Integer currentRound = player.getRoundsPlayed() - 1;
        System.out.println(player.getOnSpare());

        // if (currentRound >= 0) {

        if (currentRound >= 1 || player.getThrow2(currentRound) + player.getThrow1(currentRound) != 10) {

            System.out.println(player.getThrow1(currentRound)+player.getThrow2(currentRound));

            if (player.throw1Array.size() > 1 && (player.getThrow2(currentRound-1) + player.getThrow1(currentRound -1 )) == 10 && (player.getThrow1(currentRound)+player.getThrow2(currentRound))==10) {
                player.setOnSpare(true);
            }else {
                player.setOnSpare(false);
            }

                if (player.getOnSpare() || player.getThrow1(currentRound-1) + player.getThrow2(currentRound-1) == 10) {
                    if (!player.getOnSpare() || player.getThrow1(currentRound) + player.getThrow2(currentRound) != 10){
                        bonusPoints = 10 + player.getThrow1(currentRound);
                    player.setTotalScore(player.getTotalScore() + bonusPoints + (player.getThrow1(currentRound) + player.getThrow2(currentRound)));
                        System.out.println("oasfjhofashjfiosf");
                }else{
                        bonusPoints = player.getThrow1(currentRound);
                        player.setTotalScore(player.getTotalScore() + bonusPoints + (player.getThrow1(currentRound) + player.getThrow2(currentRound)));
                        player.setOnSpare(false);
                        System.out.println("called");
                    }

/*}
                } else if (currentRound > 1 && ((player.getThrow1(currentRound) + player.getThrow2(currentRound)) != 10)&& !player.getOnSpare()) {
                    bonusPoints = 10 + player.getThrow1(currentRound) + player.getThrow1(currentRound) + player.getThrow2(currentRound);
                    System.out.println(bonusPoints);
                    player.setTotalScore(player.getTotalScore() + bonusPoints);
                    System.out.println("cal spare");

                }*/
                } else if(player.getThrow1(currentRound) + player.getThrow2(currentRound) != 10){
                    player.setTotalScore(player.getTotalScore()+ (player.getThrow1(currentRound)+ player.getThrow2(currentRound)));
                    System.out.println("addd");
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



