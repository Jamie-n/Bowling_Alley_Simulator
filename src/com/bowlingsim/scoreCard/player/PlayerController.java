package com.bowlingsim.scoreCard.player;


import java.util.ArrayList;


public class PlayerController {


    private static ArrayList<BowlingPlayer> playerArrayList = new ArrayList<>();

    public  static void addPlayer(String player){
        playerArrayList.add(new BowlingPlayer(player));
    }

    public static BowlingPlayer getPlayer(int i){
        return playerArrayList.get(i);
    }

    public static ArrayList<BowlingPlayer> getPlayerArrayList(){
        return playerArrayList;
    }

}
