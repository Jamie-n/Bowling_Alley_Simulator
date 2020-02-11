package com.bowlingsim.foodMenu;

public class CafeItem {

    String nameOfItem;
    Double priceOfItem;
    Boolean alcoholic;
    String typeOfItem;

    public CafeItem(String nameOfItem, Double priceOfItem, String typeOfItem) {
        this.nameOfItem = nameOfItem;
        this.priceOfItem = priceOfItem;
        this.typeOfItem = typeOfItem;
    }

    public CafeItem(String nameOfItem, Double priceOfItem, Boolean alcoholic, String typeOfItem) {
        this.nameOfItem = nameOfItem;
        this.priceOfItem = priceOfItem;
        this.alcoholic = alcoholic;
        this.typeOfItem = typeOfItem;
    }

    public String getNameOfItem() {
        return nameOfItem;
    }

    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }

    public Double getPriceOfItem() {
        return priceOfItem;
    }

    public void setPriceOfItem(Double priceOfItem) {
        this.priceOfItem = priceOfItem;
    }

    public Boolean getAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(Boolean alcoholic) {
        this.alcoholic = alcoholic;
    }

    public String getTypeOfItem() {
        return typeOfItem;
    }

    public void setTypeOfItem(String typeOfItem) {
        this.typeOfItem = typeOfItem;
    }
}

