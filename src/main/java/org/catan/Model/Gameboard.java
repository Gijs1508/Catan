package org.catan.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Gameboard {
    private HashMap tiles;
    private ArrayList<Village> settlements;
    private ArrayList<Road> roads;
    private HashMap players;
    private Thief thief;
    private HashMap harbour;

    public Gameboard() {

    }

    public void setTiles() {

    }

    public void setSettlements(Player player, Tile tile) {

    }

    public void setThief(Player player, Tile tile) {

    }

    public void setHarbour() {

    }

    public HashMap getTiles() {
        return this.tiles;
    }

    public ArrayList<Village> getSettlements() {
        return this.settlements;
    }

    public void setSettlements(ArrayList<Village> settlements) {
        this.settlements = settlements;
    }

    public ArrayList<Road> getRoads() {
        return roads;
    }

    public void setRoads(ArrayList<Road> roads) {
        this.roads = roads;
    }
}
