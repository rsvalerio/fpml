package com.silvionetto.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Currency;

/**
 * Created by silvionetto on 5/12/16.
 */
public class Fee implements Serializable {

    private static int count = 0;

    private int id;
    private String type;
    private Currency currency;
    private Calendar date;

    public int getId() {
        if (id == 0) {
            id = count++;
        }
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
