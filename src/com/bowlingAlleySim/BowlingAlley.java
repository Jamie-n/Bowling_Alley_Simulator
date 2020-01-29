package com.bowlingAlleySim;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;

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
        return playerList.get(index).getScore();
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

        if (currentRound < (this.playerList.size() * 10) && currentPlayer != this.playerList.size()) {
            gridPane.add(new Label(ball.bowlBall(this.playerList.get(currentPlayer))), this.getRoundsPlayed(currentPlayer), Integer.parseInt(this.getName(currentPlayer)));
            Node result = null;
            ObservableList<Node> children = gridPane.getChildrenUnmodifiable();
            for (Node node : children) {
                if (node instanceof Label && gridPane.getRowIndex(node) == currentPlayer && gridPane.getColumnIndex(node) == 11) {
                    ((Label) node).setText(String.valueOf(this.getScore(currentPlayer)));
                }
            }
            currentPlayer += 1;
            currentRound += 1;

            if (gridPane.getRowCount() == currentPlayer) {
                currentPlayer = 0;
            }
        } else {
            EndGameScreen endGameScreen = new EndGameScreen();
            endGameScreen.initModality(Modality.APPLICATION_MODAL);
            endGameScreen.show();
            gameWinner(gridPane);

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

    public String gameWinner(GridPane gridPane) {
        ArrayList<String> scoreArray = new ArrayList<>();
        Integer topScore = 0;
        Node result = null;
        ObservableList<Node> children = gridPane.getChildrenUnmodifiable();
        for (Node node : children) {
            if (node instanceof Label && gridPane.getColumnIndex(node) == 11) {
                scoreArray.add(((Label) node).getText());
            }
        }

        for(int i = 0; i < scoreArray.size()-1; i++){
            System.out.println(scoreArray.toString());
            if(Integer.parseInt(scoreArray.get(i)) > topScore)
            {
                topScore = Integer.parseInt(scoreArray.get(i));
            }
        }


        return topScore.toString();
    }

}

