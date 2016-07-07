package com.apps.type_a.cashlost;

import java.io.Serializable;

/**
 * Created by Type_A on 07.07.2016.
 */
public class PurchaseItem implements Serializable {
    private String place;
    private String name;
    private Double cost;
    private PurchaseDate date;
    private String purchaseType;

    public PurchaseItem(String place, String name, Double cost, PurchaseDate date, String purchaseType) {
        this.place = place;
        this.name = name;
        this.cost = cost;
        this.date = date;
        this.purchaseType = purchaseType;
    }

    public String getPlace() {
        return place;
    }

    public String getName() {
        return name;
    }

    public Double getCoast() {
        return cost;
    }

    public PurchaseDate getDate() {
        return date;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoast(Double coast) {
        this.cost = coast;
    }

    public void setDate(PurchaseDate date) {
        this.date = date;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }
}
