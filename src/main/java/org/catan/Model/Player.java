package org.catan.Model;

import java.util.ArrayList;

public class Player {

    private String name;
    private String color;
//    private Inventaris spelerInventaris;
//    private ArrayList<SpelObject> spelerObjecten = new ArrayList<SpelObject>();

    public Player(String name) {
        this.name = name;
        this.color = "";    //TODO
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
