package com.bowlingAlleySim;

import javafx.scene.layout.GridPane;

public class BowlingBall {

    public String bowlBall(BowlingPlayer player){
        int totalBowl = 0;

        if(player.getRoundsPlayed() < 10) {

            int bowl1 = (int) (Math.random() * 11);

            int bowl2 = (int) (Math.random() * bowl1);

            player.setRoundsPlayed(player.getRoundsPlayed() + 1);

            if (bowl1 > 9) {
                player.setScore(player.getScore()+10);
                return "X";
            } else if (bowl1 + bowl2 > 10) {
                player.setScore(player.getScore()+10);
                return "/";
            } else {
                player.setScore(player.getScore()+(bowl1+bowl2));
                return bowl1 + " " + bowl2;
            }
        }
        else{
            return null;
        }


    }
}
