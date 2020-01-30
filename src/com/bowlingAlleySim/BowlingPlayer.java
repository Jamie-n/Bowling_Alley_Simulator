package com.bowlingAlleySim;

import java.util.ArrayList;

public class BowlingPlayer {

    private String name;
    private Integer totalScore;
    private Integer roundsPlayed;
    private Integer strikeScored;
    private Integer spareScored;
    private Integer spareScore;

    public BowlingPlayer(String name) {
        this.name = name;
        this.totalScore = 0;
        this.roundsPlayed = 0;
        this.strikeScored = 0;
        this.spareScored = 0;
        this.spareScore = 0;


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

    public Integer getStrikeScored() {
        return strikeScored;
    }

    public void setStrikeScored(Integer strikeScored) {
        this.strikeScored = strikeScored;
    }

    public Integer getSpareScored() {
        return spareScored;
    }

    public void setSpareScored(Integer spareScored) {
        this.spareScored = spareScored;
    }

    public Integer getSpareScore() {
        return spareScore;
    }

    public void setSpareScore(Integer spareScore) {
        this.spareScore = spareScore;
    }
}



