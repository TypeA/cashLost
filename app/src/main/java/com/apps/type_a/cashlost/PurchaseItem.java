package com.apps.type_a.cashlost;

import java.io.Serializable;

/**
 * Created by Type_A on 07.07.2016.
 */
public class PurchaseItem implements Serializable {
    private String place;
    private String name;
    private int cost;
    private PurchaseDate date;
    private int purchaseTypeID;

    public PurchaseItem(String place, String name, int cost, PurchaseDate date, int purchaseTypeID) {
        this.place = place;
        this.name = name;
        this.cost = cost;
        this.date = date;
        this.purchaseTypeID = purchaseTypeID;
    }

    public String getPlace() {
        return place;
    }

    public String getName() {
        return name;
    }

    public int getCoast() {
        return cost;
    }

    public PurchaseDate getDate() {
        return date;
    }

    public int getPurchaseTypeID() {
        return purchaseTypeID;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoast(int coast) {
        this.cost = coast;
    }

    public void setDate(PurchaseDate date) {
        this.date = date;
    }

    public void setPurchaseTypeID(int purchaseTypeID) {
        this.purchaseTypeID = purchaseTypeID;
    }
}
