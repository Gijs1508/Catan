package org.catan.Model;

import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Tile {
    private String type;
    private String id;
    private int number;

    //TODO
    private ArrayList<Village> connectedSettlements;

    public Tile() {

    }

    public Tile(String type, String id, Integer number){
        this.type = type;
        this.id = id;
        this.number = number;
    }

    public String getType() {
        return this.type;
    }

    public String getId() {
        return this.id;
    }

    public int getNumber() {
        return this.number;
    }

    //TODO
    public void addToConnectedSettlements(Village village) {
        connectedSettlements.add(village);
    }
    public ArrayList<Village> getConnectedSettlements() {
        return connectedSettlements;
    }
}
