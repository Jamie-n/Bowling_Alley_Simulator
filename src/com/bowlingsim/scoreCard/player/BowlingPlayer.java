package com.bowlingsim.scoreCard.player;

import com.bowlingsim.foodMenu.menuItem.MenuItem;

import java.util.ArrayList;

public class BowlingPlayer {

    private String name;
    private Integer totalScore;
    private Integer roundsPlayed;
    private Boolean onSpare;
    private Boolean onStrike;
    private Boolean gameOver;
    private Integer strikeInRow;
    private Double currentTab;


    public BowlingPlayer(String name) {
        this.name = name;
        this.totalScore = 0;
        this.roundsPlayed = 0;
        this.onSpare = false;
        this.onStrike = false;
        this.gameOver = false;
        this.strikeInRow = 0;
        this.currentTab = 0.00;


    }

    private ArrayList<Integer> throw1Array = new ArrayList<>();
    private ArrayList<Integer> throw2Array = new ArrayList<>();
    private ArrayList<MenuItem> itemsConsumed = new ArrayList<MenuItem>();

    public Integer getStrikeInRow() {
        return strikeInRow;
    }

    public void setStrikeInRow(Integer strikeInRow) {
        this.strikeInRow = strikeInRow;
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Boolean getOnStrike() {
        return onStrike;
    }

    public void setOnStrike(Boolean onStrike) {
        this.onStrike = onStrike;
    }

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

    public int getThrowArray1Size(){
        return this.throw1Array.size();
    }

    public void addThrow1AtIndex(Integer index, Integer score){
        this.throw1Array.add(index,score);
    }

    public void addThrow2AtIndex(Integer index, Integer score){
        this.throw2Array.add(index,score);
    }

    public String getName() {
        return name;
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

    public Double getCurrentTab() {
        return currentTab;
    }

    public void updateTab(Double currentTab) {
        this.currentTab =+ currentTab;
    }

    public void settleTab(){
        this.currentTab = 0.0;
    }

    public void updateScore(Integer score) {
        this.setTotalScore(this.getTotalScore() + score);
    }


    public void addAllItemsConsumed(ArrayList<MenuItem> shoppingBasket){
        this.itemsConsumed.addAll(shoppingBasket);
    }

    public MenuItem getItemConsumed(Integer index){
        return this.itemsConsumed.get(index);
    }


    @Override
    public String toString() {
        return this.getName();
    }
}




