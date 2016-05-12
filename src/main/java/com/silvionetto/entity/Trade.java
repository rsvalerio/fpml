package com.silvionetto.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

/**
 * Created by silvionetto on 5/12/16.
 */
public class Trade implements Serializable {

    private static int count = 0;

    private int id;
    private Calendar date = Calendar.getInstance();
    private Double amount;
    private LegalEntity legalEntity;
    private Vector<Fee> fees;
    private String externalId;

    public Trade () {}

    public Trade(String externalId, Double amount, LegalEntity legalEntity) {
        this.externalId = externalId;
        this.amount = amount;
        this.legalEntity = legalEntity;
    }

    public int getId() {
        if (id == 0) {
            id = count++;
        }
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDate() { return date; }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getDateAux() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date.getTime());
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + getId() +
                ", date=" + getDateAux() +
                ", amount=" + amount +
                ", legalEntity=" + legalEntity +
                ", fees=" + fees +
                ", externalId='" + externalId + '\'' +
                '}';
    }
}
