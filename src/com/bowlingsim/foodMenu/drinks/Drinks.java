package com.bowlingsim.foodMenu.drinks;

import com.bowlingsim.foodMenu.menuItem.MenuItem;


public class Drinks extends MenuItem {



    private String type;
    private Integer size;
    private Boolean alcoholic;
    private Double alcContent;

    public Drinks(String name, Double price, String type, Integer size, Boolean alcoholic, Double alcContent) {
        super(name, price);
        this.type = type;
        this.size = size;
        this.alcoholic = alcoholic;
        this.alcContent = alcContent;
    }

    public Integer getSize() {
        return size;
    }

    public Boolean getAlcoholic() {
        return alcoholic;
    }

    public Double getAlcContent() {
        return alcContent;
    }

}
