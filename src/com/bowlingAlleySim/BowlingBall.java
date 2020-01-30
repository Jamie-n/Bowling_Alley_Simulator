package com.bowlingAlleySim;

import javafx.scene.layout.GridPane;

public class BowlingBall {


    public String bowlBall(BowlingPlayer player) {


        player.setRoundsPlayed(player.getRoundsPlayed()+1);
        int bowl1 =5; //(int) (Math.random() * 11);
        int bowl2 =5;// (int) (Math.random() * bowl1);

        System.out.println(bowl1+" "+bowl2);

        if(bowl1 >= 10){
            player.setStrikeScored(player.getStrikeScored()+1);
            return "X";
        }
        if(bowl1 + bowl2 < 10){
            player.setTotalScore((player.getTotalScore()+(bowl1+bowl2)));
            player.setSpareScore(bowl1);
            return bowl1+" "+bowl2;
        }

        else if((bowl1 + bowl2) >= 10){
            player.setSpareScored(player.getSpareScored()+1);
            return "/";


        }





        return null;
    }
}