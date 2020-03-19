package com.bowlingsim.scoreCard.player;

import javax.naming.CompositeName;
import java.util.ArrayList;
import java.util.Hashtable;

public class PlayerController {


    private static ArrayList<BowlingPlayer> playerArrayList = new ArrayList<>();

    public  static void addPlayer(String player){
        playerArrayList.add(new BowlingPlayer(player));
        System.out.println(playerArrayList.size());
        System.out.println("added");
    }

    public static BowlingPlayer getPlayer(int i){
        return playerArrayList.get(i);
    }

    public static ArrayList<BowlingPlayer> getPlayerArrayList(){
        return playerArrayList;
    }

}
