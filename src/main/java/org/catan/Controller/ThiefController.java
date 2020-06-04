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
            return false;  //Rover staat er, dus kan niet plaatsen
        } else {
            return true;  //Rover staat er niet, dus kan plaatsen
        }
    }

    @FXML
    public static void placeThief(Player player, Tile tile) {
        if (placeableLocation(tile) == true){
            //Plaatsen van rover.png idk how
            setLocation(tile);
            updateThief();
        } else {
            System.out.println("The Thief is already here");
        }
    }

    public Tile getLocation() {
        return location;
    }

    public static void setLocation(Tile tile) {
        location = tile;
    }

    private static void updateThief() {

    }

}
