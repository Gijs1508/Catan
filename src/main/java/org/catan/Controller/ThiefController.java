package org.catan.Controller;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import org.catan.Model.Game;
import org.catan.Model.Player;
import org.catan.Model.Tile;
import org.catan.interfaces.Observable;

public class ThiefController implements Observable {

    @Override
    public void update(Game game) {
    }

    public static int convertIDtoInt(String circleID){
        circleID = circleID.replaceAll("[^\\d.]", ""); //Cleaning tile string to only a number string
        int tileID;
        try {
            tileID = Integer.parseInt(circleID);
        }
        catch (NumberFormatException e) {
            tileID = 1;
        }
        return tileID;
    }

    public static void checkStealableOppenets(ArrayList<Player> opponents) throws IOException {
        if(opponents.isEmpty()) { // There are no opponents to steal from
            return; }
        else if(opponents.size() > 1) { // There are more opponents to steal from
            ScreenController.getInstance().showStealPopUp(); // Choose opponent popup
            StealPopUpController.getInstance().updateOpponents(opponents);
            return;
        }
    }

}
