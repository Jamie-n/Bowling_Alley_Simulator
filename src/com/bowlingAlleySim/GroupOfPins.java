package com.bowlingAlleySim;

import javafx.scene.control.Button;

public class GroupOfPins {

    private Integer ballScore;
    private Button testButton;
    ScoreBoard testBoard;

    public Integer bowlBall(){
        ballScore = (int)(Math.random()*8)+1;

        return ballScore;
    }


}
