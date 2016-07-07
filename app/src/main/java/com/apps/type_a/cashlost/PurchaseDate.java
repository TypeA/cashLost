package com.apps.type_a.cashlost;

import java.io.Serializable;

/**
 * Created by Type_A on 07.07.2016.
 */
public class PurchaseDate implements Serializable {
    private int day;
    private int month;
    private int year;

    public PurchaseDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
