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
        this.settlements = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.thief = new Thief();
    }

    public HashMap getTiles() {
        return this.tiles;
    }

    /** Returns the built villages
     * Can be accessed in every class that needs it
     * @return an ArrayList type Village */
    public ArrayList<Village> getSettlements() {
        return this.settlements;
    }

    // Updates the array when a new village is built
    public void setSettlements(ArrayList<Village> settlements) {
        this.settlements = settlements;
    }

    public ArrayList<Road> getRoads() {
        return roads;
    }

    public void setRoads(ArrayList<Road> roads) {
        this.roads = roads;
    }

    public void addSettlement(Village village) {
        this.settlements.add(village);
    }

    public void addRoad(Road road) {
        this.roads.add(road);
    }

    public void setThief(Thief thief) {
        this.thief = thief;
    }

    public Thief getThief(){
        return this.thief;
    }
}
