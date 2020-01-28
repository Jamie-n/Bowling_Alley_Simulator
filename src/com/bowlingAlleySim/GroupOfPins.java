package com.bowlingAlleySim;

public class GroupOfPins {

    private Integer ballScore;

    public Integer bowlBall(){
        ballScore = (int)(Math.random()*8)+1;

        return ballScore;
    }

}
