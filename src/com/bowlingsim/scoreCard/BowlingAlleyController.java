package com.bowlingsim.scoreCard;


import com.bowlingsim.msgBox.MsgBox;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;


public class BowlingAlleyController {

    public ArrayList<BowlingPlayer> playerList = new ArrayList<>();
    public int currentPlayer;


    BowlingBall ball = new BowlingBall();

    public void addPlayer(String name) {
        playerList.add(new BowlingPlayer(name));
    }


    public Integer getScore(Integer index) {
        return playerList.get(index).getTotalScore();
    }


    public void updateScore(GridPane gridPane) {
        if (playerList.get(currentPlayer).getGameOver()) {
            System.out.println(currentPlayer);
            if (currentPlayer == 0) {
                getWinner(this.playerList);
            }
        } else {
            int currentPlayerRound = playerList.get(currentPlayer).getRoundsPlayed();
            if (currentPlayerRound < (this.playerList.size() * 12) || playerList.get(currentPlayerRound).getThrow2(currentPlayerRound - 1) + playerList.get(currentPlayer).getThrow1(currentPlayerRound - 1) == 10) {

                ball.bowlBall(this.playerList.get(currentPlayer));
                calculateScore(playerList.get(currentPlayer), gridPane);

                currentPlayer += 1;

            }
            if (gridPane.getRowCount() == currentPlayer) {
                currentPlayer = 0;
            }
        }
    }

    public void getWinner(ArrayList<BowlingPlayer> bowler) {

        Integer highScore = 0;
        String name = "";
        System.out.println(bowler.size());

        for (BowlingPlayer player : bowler) {
            if (player.getTotalScore() > highScore) {
                highScore = player.getTotalScore();
                name = player.getName();
            }
        }
        new MsgBox().showInfoBox("Game Over", "Game Over", "Player " + name + " has won the game with " + highScore + " points!", Alert.AlertType.INFORMATION);
    }


    public void addRows(GridPane gridPane, String playerName) {
        int numberOfPlayers = playerList.size();
        if (numberOfPlayers != 8) {
            gridPane.add(new Label(playerName), 0, numberOfPlayers);
            gridPane.add(new Label("0"), 13, numberOfPlayers);

            for (int i = 0; i < 11 + 1; i++) {
                gridPane.add(new Label(" "), i, numberOfPlayers);

            }
            this.addPlayer(playerName);
        } else {
            new MsgBox().showInfoBox("Too Many Players", "Too Many Players", "Can only have a maximum of 8 players", Alert.AlertType.WARNING);
        }

    }


    public void calculateScore(BowlingPlayer player, GridPane gridPane) {
        int bonusPoints = 0;
        Integer currentRound = player.getRoundsPlayed() - 1;
        int totalForFrame = 0;
        int totalLastFrame = 0;
        Integer extraFrameSpare = 0;

        if (player.getThrow1(currentRound) == 10) {
            player.setStrikeInRow(player.getStrikeInRow() + 1);
        }


        if (currentRound >= 1 || player.getThrow2(currentRound) + player.getThrow1(currentRound) != 10) {
            totalForFrame = player.getThrow1(currentRound) + player.getThrow2(currentRound);

            if (currentRound >= 1) {
                totalLastFrame = player.getThrow1(currentRound - 1) + player.getThrow2(currentRound - 1);
                //if a strike was scored
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


        //Checks for game over condition
        if (currentRound >= 9) {
            if (currentRound == 9 && totalForFrame != 10) {
                player.setGameOver(true);
            } else if (currentRound == 11) {
                player.setGameOver(true);
            }

        }


        //Checks for a double spare at the start of the game
        if (player.getThrowArray1Size() >= 1 && (totalLastFrame) == 10 && (totalForFrame) == 10) {
            player.setOnSpare(true);
        } else {
            player.setOnSpare(false);
        }


        //Final round strike
        if (currentRound >= 10 && player.getThrow1(9) == 10) {
            if (currentRound == 11) {
                bonusPoints = 10 + player.getThrow1(currentRound) + player.getThrow1(currentRound - 1);
                player.updateScore(bonusPoints);
            }


            //More than 3 strikes in a row
        } else if (player.getOnStrike() && currentRound >= 2 && player.getStrikeInRow() >= 3) {
            player.updateScore(10 * player.getStrikeInRow());
            player.setStrikeInRow(player.getStrikeInRow() - 1);

            //Spare after strike
        } else if (player.getOnStrike() && player.getThrow1(currentRound) != 10 && totalForFrame == 10 && player.getStrikeInRow() < 2) {
            bonusPoints = (10 + totalForFrame);
            player.updateScore(bonusPoints);
            player.setStrikeInRow(player.getStrikeInRow() - 1);

            //Single Strike
        } else if (player.getOnStrike() && player.getThrow1(currentRound) != 10 && player.getStrikeInRow() == 1) {
            bonusPoints = 10 + (totalForFrame + totalForFrame);
            player.updateScore(bonusPoints);
            player.setStrikeInRow(player.getStrikeInRow() - 1);


            //2 Strikes
        } else if (player.getOnStrike() && player.getThrow1(currentRound - 1) == 10 && player.getThrow1(currentRound) != 10) {
            while (player.getStrikeInRow() != 0) {
                bonusPoints += player.getThrow1(currentRound - player.getStrikeInRow()) + player.getThrow2(currentRound - player.getStrikeInRow());
                bonusPoints += player.getThrow1(currentRound - player.getStrikeInRow()) + player.getThrow2(currentRound - player.getStrikeInRow());
                player.setStrikeInRow(player.getStrikeInRow() - 1);
                System.out.println("Dbl Strike");

            }
            player.updateScore(bonusPoints + player.getThrow1(currentRound));


        } else if (player.getOnSpare() || totalLastFrame == 10) {

            //Final round spare
            if (currentRound == 10 && totalLastFrame == 10 && player.getThrow1(currentRound - 1) != 10) {
                bonusPoints = 10 + extraFrameSpare;
                player.updateScore(bonusPoints);
                player.addThrow2AtIndex(currentRound + 1, 0);
                player.setGameOver(true);
            } else {

                //If only 1 spare has been thrown
                if (!player.getOnSpare() && totalForFrame != 10) {
                    bonusPoints = 10 + player.getThrow1(currentRound);
                    player.updateScore(bonusPoints + totalForFrame);

                    //If a double spare or more was thrown
                } else if (player.getThrow1(currentRound - 1) != 10) {
                    bonusPoints = 10 + player.getThrow1(currentRound);
                    player.setOnSpare(false);
                    player.updateScore(bonusPoints);
                }

            }
            //If only a single was thrown
        } else if (totalForFrame != 10) {
            player.updateScore(totalForFrame);
        }


        //Updates the score at end of scorecard
        ObservableList<Node> children = gridPane.getChildrenUnmodifiable();
        for (Node node : children) {
            if (node instanceof Label && GridPane.getRowIndex(node) == currentPlayer && GridPane.getColumnIndex(node) == 13) {
                ((Label) node).setText(String.valueOf(this.getScore(currentPlayer)));
            }
        }

        //Displays the score to the user
        gridPane.add(new Label(new CalculateScoreSymbols().calculateSymbols(player.getThrow1(currentRound), player.getThrow2(currentRound), currentRound)), currentRound + 1, currentPlayer);


    }
}