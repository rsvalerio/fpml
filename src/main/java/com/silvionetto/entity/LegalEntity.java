package com.silvionetto.entity;

import java.io.Serializable;

/**
 * Created by silvionetto on 5/12/16.
 */
public class LegalEntity implements Serializable {

    private static int count = 0;

    private int id;
    private String name;

    public LegalEntity(String name) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LegalEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
