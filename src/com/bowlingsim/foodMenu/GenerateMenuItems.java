package com.bowlingsim.foodMenu;

import java.util.ArrayList;

public class GenerateMenuItems {


    static ArrayList<CafeItem> cafeItemList = new ArrayList<>();




    private static String[] foodItems = {"Chips, 1.99", "Garlic Bread, 2.00", "Halloumi Fries, 3.49", "Crisps, 0.99"};
    private static String[] drinkItems = {"Pepsi, 2.99, false","Diet Pepsi, 2.49, false","Fanta, 2.99, false","Becks 4.8%, 3.50, True ","Rose 8.5%, 4.99, True"};


    public static void createFoodItems() {
        if (cafeItemList.size() == 0) {
            for(String food : foodItems){
                String[] splitItem = food.split(",");
                cafeItemList.add(new CafeItem(splitItem[0],Double.parseDouble(splitItem[1]),"food"));
            }
            for(String drinks : drinkItems){
                String[] splitItem = drinks.split(",");
                cafeItemList.add(new CafeItem(splitItem[0],Double.parseDouble(splitItem[1]),Boolean.parseBoolean(splitItem[2]),"drink"));
            }
        }
    }
}
