package com.bowlingAlleySim;

import java.util.ArrayList;

public class BowlingPlayer {

    private String name;
    private Integer score;
    private Integer roundsPlayed;

    public BowlingPlayer(String name) {
        this.name = name;
        this.score = 0;
        this.roundsPlayed = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getRoundsPlayed() {
        return roundsPlayed;
    }

    public void setRoundsPlayed(Integer roundsPlayed) {
        this.roundsPlayed = roundsPlayed;
    }
}
