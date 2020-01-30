package com.bowlingAlleySim;

import javafx.scene.layout.GridPane;

public class BowlingBall {


    public String bowlBall(BowlingPlayer player) {


        player.setRoundsPlayed(player.getRoundsPlayed()+1);

        int bowl1 = (int) (Math.random() * 11);
        int  bowl2 = (int) (Math.random() *(10-bowl1));

        player.setBowl1(bowl1);
        player.setBowl2(bowl2);

        System.out.println(bowl1+" "+bowl2);

        if(bowl1 >= 10){
            player.setStrikeScored(player.getStrikeScored()+1);
            return "X";
        }
        if(bowl1 + bowl2 < 10){
            player.setTotalScore((player.getTotalScore()+(bowl1+bowl2)));
            return bowl1+" "+bowl2;
        }

        else if((bowl1 + bowl2) >= 10){
            player.setSpareScored(player.getSpareScored()+1);
            return "/";


        }





        return null;
    }
}