package org.catan.Model;

import org.catan.Controller.ThiefController;

import java.util.HashMap;

public class Gameboard {
    private HashMap tiles;
    private HashMap settlements;
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
        ThiefController.placeThief(player, tile);
    }

    public void setHarbour() {

    }

    public HashMap getTiles() {
        return this.tiles;
    }

    public HashMap getSettlements() {
        return this.settlements;
    }
}
