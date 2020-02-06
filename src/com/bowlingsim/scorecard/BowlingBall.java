package com.bowlingsim.scorecard;

import java.util.concurrent.ThreadLocalRandom;

public class BowlingBall {


    public void bowlBall(BowlingPlayer player) {

        int[] bowl1Array = {5,2,10,6,7,3,10,1,0,7,2};
        int[] bowl2Array = {0,5,0,1,0,3,0,1,6,0,6};

        player.setRoundsPlayed(player.getRoundsPlayed() + 1);
        int bowl1 = bowl1Array[player.getRoundsPlayed()];    //ThreadLocalRandom.current().nextInt(0, 9 + 1);

        if(bowl1 == 10){
            player.addThrow1(bowl1);
            player.addThrow2(0);
            return;
        }else{
            int bowl2 = bowl2Array[player.getRoundsPlayed()];     //ThreadLocalRandom.current().nextInt(0, (10-bowl1));
            player.addThrow2(bowl2);
            player.addThrow1(bowl1);
        }

        System.out.println(bowl1+" "+ player.getThrow2(0));
    }
}