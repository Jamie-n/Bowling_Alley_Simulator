package com.bowlingsim.foodMenu.generateItems;



import com.bowlingsim.foodMenu.drinks.Drinks;
import com.bowlingsim.foodMenu.food.Food;
import com.bowlingsim.foodMenu.menuItem.MenuItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OnStartGenerate {

    static ArrayList<MenuItem> foodItem = new ArrayList<>();
    static ArrayList<MenuItem> drinksItem = new ArrayList<>();

    public static void generateMenuItems(){
        parseMenuCsv("src/com/bowlingsim/foodMenu/menuCsvs/assingmentDrinksMenu.csv",false);
        parseMenuCsv("src/com/bowlingsim/foodMenu/menuCsvs/foodMenu.csv",true);
    }



    public static void parseMenuCsv(String fileSource, Boolean food){

        try (BufferedReader br = new BufferedReader(new FileReader(fileSource))){
            String line;
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                if(food){
                    generateFoodItems(values);
                }else {
                    generateDrinkItems(values);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void generateDrinkItems(String[] values){

        String name = values[0];
        Double price = Double.parseDouble(values[1]);
        String type = values[2];
        Integer size = Integer.parseInt(values[3]);
        Boolean alcoholic = Boolean.parseBoolean(values[4]);
        Double alcContent = Double.parseDouble(values[5]);

        drinksItem.add(new Drinks(name,price,type,size,alcoholic,alcContent));

    }

    private static void generateFoodItems(String[] values){
        String name = values[0];
        Double price = Double.parseDouble(values[1]);

        foodItem.add(new Food(name, price));
    }

    public static ArrayList<MenuItem> getFoodItem() {
        return foodItem;
    }

    public static ArrayList<MenuItem> getDrinksItem() {
        return drinksItem;
    }
}
