package com.rsvalerio.route.entity;

import java.util.Calendar;
import java.util.Currency;

/**
 * Created by silvionetto on 5/12/16.
 */
public class Trade {

    private int id;
    private Calendar date;
    private Currency amount;
    private LegalEntity legalEntity;
    private Vector<Fee> fees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Currency getAmount() {
        return amount;
    }

    public void setAmount(Currency amount) {
        this.amount = amount;
    }

    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(LegalEntity legalEntity) {
        this.legalEntity = legalEntity;
    }

    public Vector<Fee> getFees() {
        return fees;
    }

    public void setFees(Vector<Fee> fees) {
        this.fees = fees;
    }
}
