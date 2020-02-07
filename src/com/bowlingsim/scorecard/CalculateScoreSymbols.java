package com.bowlingsim.scorecard;

public class CalculateScoreSymbols {

    public String calculateSymbols(Integer score1, Integer score2, Integer currentRound) {
        if (score1 == 10) {
            return "X";

        } else if (score1 + score2 >= 10) {
            return "/";

        } else if (currentRound < 10) {
            return score1 + " " + score2;

        } else {
            return score1.toString();
        }
    }
}
