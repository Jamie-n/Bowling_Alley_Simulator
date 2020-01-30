package com.bowlingAlleySim;

import javafx.scene.layout.GridPane;

import java.lang.reflect.Array;

public class BowlingBall {


    public String bowlBall(BowlingPlayer player) {

        int[] bowl_1 = new int[]{5,5,5,5,5,9,4,0};
        int[] bowl_2 = new int[]{5,5,5,5,5,1,0,0};

        player.setRoundsPlayed(player.getRoundsPlayed()+1);



        int bowl1 = bowl_1[player.getRoundsPlayed()-1]; //(int) (Math.random() * 11);
        int  bowl2 = bowl_2[player.getRoundsPlayed()-1];//(int) (Math.random() *(10-bowl1));
        System.out.println(player.getRoundsPlayed()+" "+ player.getName());


        player.addThrow1(bowl1);
        player.addThrow2(bowl2);





        if(bowl1 >= 10){
            player.setStrikeScored(player.getRoundsPlayed());
            return "X";
        }
        if(bowl1 + bowl2 < 10){
            return bowl1+" "+bowl2;

        }

        else if((bowl1 + bowl2) >= 10){
            return "/";

        }





        return null;
    }
}