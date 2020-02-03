package com.bowlingsim.scorecard;

import java.util.concurrent.ThreadLocalRandom;

public class BowlingBall {


    public void bowlBall(BowlingPlayer player) {

        player.setRoundsPlayed(player.getRoundsPlayed() + 1);
        int bowl1 = ThreadLocalRandom.current().nextInt(0, 10 + 1);

        if(bowl1 == 10){
            player.addThrow1(bowl1);
            player.addThrow2(0);
            return;
        }else{
            int bowl2 = ThreadLocalRandom.current().nextInt(0, (10-bowl1));
            player.addThrow2(bowl2);
            player.addThrow1(bowl1);
        }

        System.out.println(bowl1+" "+ player.getThrow2(0));






    }

    public void endStrike(BowlingPlayer player){

    }
}