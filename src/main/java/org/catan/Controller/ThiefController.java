package org.catan.Controller;

import javafx.fxml.FXML;
import org.catan.Model.Player;
import org.catan.Model.Tile;

public class ThiefController {
    private static Tile location;

    private void keyHandler() {
        //WIP
    }

    private static boolean placeableLocation(Tile tile) {
        if (tile == location){
            return false;
        } else {
            return true;
        }
    }

    @FXML
    public void placeThief(Player player, Tile tile) {
        if (placeableLocation(tile) == true){
            //plaatsen
            setLocation(tile);
            updateThief();
        } else {
            System.out.println("The Thief is already here");
        }
    }

    public Tile getLocation() {
        return location;
    }

    public void setLocation(Tile location) {
        this.location = location;
    }

    private static void updateThief() {

    }

}
