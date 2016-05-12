package com.rsvalerio.route.entity;

import java.io.Serializable;

/**
 * Created by silvionetto on 5/12/16.
 */
public class LegalEntity implements Serializable {

    private int id;
    private String name;

    public int getId() {
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
}
