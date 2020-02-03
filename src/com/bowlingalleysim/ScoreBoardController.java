package com.bowlingalleysim;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.util.ArrayList;



public class ScoreBoardController {

    public ArrayList<BowlingPlayer> playerList = new ArrayList<BowlingPlayer>();
    public int currentPlayer;
    private int currentRound;


    BowlingBall ball = new BowlingBall();

    public void addPlayer(String name) {
        playerList.add(new BowlingPlayer(name));
    }


    public Integer getScore(Integer index) {
        return playerList.get(index).getTotalScore();
    }




    public void updateScore(GridPane gridPane) throws IOException {
        if (playerList.get(currentPlayer).getGameOver()) {
            System.out.println(currentPlayer);
            if(currentPlayer == 0){
                System.out.println("Game over");
            }

        } else {

            int currentPlayerRound = playerList.get(currentPlayer).getRoundsPlayed();
            if (currentPlayerRound < (this.playerList.size() * 12) || playerList.get(currentPlayerRound).getThrow2(currentPlayerRound - 1) + playerList.get(currentPlayer).getThrow1(currentPlayerRound - 1) == 10) {

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
        Integer numberOfPlayers = playerList.size();
        if (numberOfPlayers != 8 + 1) {
            gridPane.add(new Label("Player " + numberOfPlayers), 0, numberOfPlayers);
            gridPane.add(new Label("0"), 13, numberOfPlayers);

            for (int i = 0; i < 11 + 1; i++) {
                gridPane.add(new Label(" "), i, numberOfPlayers);

            }
            this.addPlayer(numberOfPlayers.toString());
        }

    }



    public void calculateScore(BowlingPlayer player, GridPane gridPane) {
        Integer bonusPoints = 0;
        Integer currentRound = player.getRoundsPlayed() - 1;
        Integer totalForFrame = 0;
        Integer totalLastFrame = 0;
        Integer extraFrameSpare = 0;


        System.out.println(player.throw1Array.toString());

            if (player.getThrow1(currentRound) == 10) {
                player.setStrikeInRow(player.getStrikeInRow() + 1);
                System.out.println("strike!");
            }


        if (currentRound >= 1 || player.getThrow2(currentRound) + player.getThrow1(currentRound) != 10) {

            totalForFrame = player.getThrow1(currentRound) + player.getThrow2(currentRound);

            if (currentRound >= 1) {
                totalLastFrame = player.getThrow1(currentRound - 1) + player.getThrow2(currentRound - 1);

                if (player.getThrow1(currentRound - 1) == 10) {
                    player.setOnStrike(true);
                } else {
                    player.setOnStrike(false);
                }
            }
        }
            if (currentRound >= 9) {
                //Gets the value for the bonus frame caused by a strike/spare
                extraFrameSpare = player.getThrow1(currentRound);


            }
        System.out.println("the current round is "+ currentRound+ "total frame "+ totalForFrame+ " "+ player.getThrow2(currentRound));
        System.out.println(player.throw2Array.toString());
            if (currentRound >= 9) {
                if(currentRound == 9 && totalForFrame != 10) {
                    System.out.println("Game is finished");
                    player.setGameOver(true);
                } else if(currentRound == 11){
                    System.out.println("Game Over");
                    player.setGameOver(true);
                    player.throw1Array.add(currentRound + 1);
                }

            }


            System.out.println(totalForFrame);

            //Checks for a double spare at the start of the game
            if (player.throw1Array.size() >= 1 && (totalLastFrame) == 10 && (totalForFrame) == 10) {
                player.setOnSpare(true);
            } else {
                player.setOnSpare(false);
            }

            System.out.println(player.throw1Array.toString());
            System.out.println(player.getStrikeInRow());


            //More than 3 strikes in a row
            if (player.getOnStrike() && currentRound >= 2 && player.getStrikeInRow() >= 3) {
                System.out.println(player.getStrikeInRow()+ " strikes in row");
                player.setTotalScore(player.getTotalScore() + 10 * player.getStrikeInRow());
                player.setStrikeInRow(player.getStrikeInRow() - 1);
                System.out.println("Strike in Chain");

                //Spare after strike
            }else if(player.getOnStrike()&& player.getThrow1(currentRound) != 10 && totalForFrame == 10 && player.getStrikeInRow() < 2){
                bonusPoints = (10 + totalForFrame);
                player.setTotalScore(player.getTotalScore()+bonusPoints);
                System.out.println("Spare after strike");
                player.setStrikeInRow(player.getStrikeInRow()-1);

                //Single Strike
            }else if(player.getOnStrike()&& player.getThrow1(currentRound) != 10 && player.getStrikeInRow() == 1) {
                bonusPoints = 10 + (totalForFrame + totalForFrame);
                player.setTotalScore(player.getTotalScore() + bonusPoints);
                player.setStrikeInRow(player.getStrikeInRow() - 1);
                System.out.println("Regular Strike");

                //2 strikes
            } else if (player.getOnStrike() && player.getThrow1(currentRound-1) == 10 && player.getThrow1(currentRound) != 10) {
                while (player.getStrikeInRow() != 0){
                    bonusPoints += player.getThrow1(currentRound- player.getStrikeInRow())+ player.getThrow2(currentRound- player.getStrikeInRow());
                    bonusPoints += player.getThrow1(currentRound- player.getStrikeInRow())+ player.getThrow2(currentRound- player.getStrikeInRow());
                    player.setStrikeInRow(player.getStrikeInRow()-1);
                    System.out.println("Dbl Strike");

                }
                player.setTotalScore(player.getTotalScore()+bonusPoints+ player.getThrow1(currentRound));




            } else if (player.getOnSpare() || totalLastFrame == 10) {

                //Final round spare
                 if (currentRound == 10 && totalLastFrame == 10 && player.getThrow1(currentRound-1) != 10) {
                    bonusPoints = 10 + extraFrameSpare;
                    player.setTotalScore(player.getTotalScore() + bonusPoints);
                    player.throw2Array.add(currentRound + 1, 0);
                     System.out.println("Final spare");
                } else {
                    //If only 1 spare has been thrown
                    if (!player.getOnSpare() && totalForFrame != 10) {
                        bonusPoints = 10 + player.getThrow1(currentRound);
                        System.out.println(bonusPoints);
                        player.setTotalScore(player.getTotalScore() + bonusPoints + totalForFrame);
                        System.out.println("1 spare");

                        //If a double spare or more was thrown
                    } else if(player.getThrow1(currentRound-1) != 10) {
                        bonusPoints = 10 + player.getThrow1(currentRound);
                        player.setTotalScore(player.getTotalScore() + bonusPoints);
                        player.setOnSpare(false);
                        System.out.println("multispare");
                    }

                }
                //If only a single was thrown
            } else if (totalForFrame != 10) {
                player.setTotalScore(player.getTotalScore() + totalForFrame);
                System.out.println("Single");

            }



            ObservableList<Node> children = gridPane.getChildrenUnmodifiable();
            for (Node node : children) {
                if (node instanceof Label && gridPane.getRowIndex(node) == currentPlayer && gridPane.getColumnIndex(node) == 13) {
                    ((Label) node).setText(String.valueOf(this.getScore(currentPlayer)));
                }
            }

            gridPane.add(new Label(new CalculateScoreSymbols().calculateSymbols(player.getThrow1(currentRound), player.getThrow2(currentRound), currentRound)), currentRound + 1, currentPlayer);



    }
}