package com.bowlingAlleySim;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BowlingPlayer {

    private String name;
    private Integer totalScore;
    private Integer roundsPlayed;
    private Boolean onSpare;


    public BowlingPlayer(String name) {
        this.name = name;
        this.totalScore = 0;
        this.roundsPlayed = 0;
        this.onSpare = false;


    }

    public ArrayList<Integer> throw1Array = new ArrayList<>();
    public ArrayList<Integer> throw2Array = new ArrayList<>();

    public void addThrow1(Integer score1) {
        throw1Array.add(score1);
    }

    public Integer getThrow1(Integer i) {
        return this.throw1Array.get(i);
    }

    public Integer getThrow2(Integer i) {
        return this.throw2Array.get(i);
    }

    public void addThrow2(Integer score2) {
        throw2Array.add(score2);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getRoundsPlayed() {
        return roundsPlayed;
    }

    public void setRoundsPlayed(Integer roundsPlayed) {
        this.roundsPlayed = roundsPlayed;
    }

    public Boolean getOnSpare() {
        return onSpare;
    }

    public void setOnSpare(Boolean onSpare) {
        this.onSpare = onSpare;
    }
}




