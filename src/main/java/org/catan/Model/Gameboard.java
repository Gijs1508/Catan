package org.catan.Model;

import javafx.scene.image.ImageView;
import org.catan.Controller.ThiefController;
import java.util.ArrayList;
import java.util.HashMap;

public class Gameboard {
    private HashMap tiles;
    private HashMap players;
    private Thief thief;
    private HashMap harbour;

    public Gameboard() {
        this.thief = new Thief();
    }

    public void setTiles() {

    }

    public void setThief(Player player, Tile tile) {
//        ThiefController.placeThief(player, tile);
    }

    public void setHarbour() {

    }

    public HashMap getTiles() {
        return this.tiles;
    }

    public void setThief(Thief thief) {
        this.thief = thief;
    }

    public Thief getThief(){
        return this.thief;
    }
}
