package com.bowlingAlleySim;

import javafx.scene.layout.GridPane;

import java.lang.reflect.Array;
import java.util.concurrent.ThreadLocalRandom;

public class BowlingBall {


    public void bowlBall(BowlingPlayer player) {

        int[] bowl_1 = new int[]{10,10,10,10,10,10,10,10,10,10,10,10};
        int[] bowl_2 = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0};

        player.setRoundsPlayed(player.getRoundsPlayed() + 1);


        int bowl1 = bowl_1[player.getRoundsPlayed() - 1]; //(int) (Math.random() * 11);
        int bowl2 = bowl_2[player.getRoundsPlayed() - 1];//(int) (Math.random() *(10-bowl1));

        player.addThrow1(bowl1);
        player.addThrow2(bowl2);

     /*   int bowl1 = ThreadLocalRandom.current().nextInt(0, 10 + 1);
        if(bowl1 >= 10){

            return "X";
        }
        int bowl2 = ThreadLocalRandom.current().nextInt(0, (10-bowl1));*/

    }

    public void endStrike(BowlingPlayer player){

    }
}