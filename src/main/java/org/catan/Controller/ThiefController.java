package org.catan.Controller;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

import javafx.fxml.Initializable;
import org.catan.App;
import org.catan.Helper.BuildVillages;
import org.catan.Model.*;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;

public class ThiefController implements Initializable, Observable {
    private static ThiefController thiefController;

    public ThiefController(){
    }

    public static ThiefController getInstance(){
        if(thiefController == null){
            thiefController = new ThiefController();
        }
        return thiefController;
    }

    public void checkThiefPosition(){
        App.getClientPlayer().isTurn();
    }

    /** Converts String circleID ("Tilenr") to an int tileID (nr)
     * @param circleID the id of the tile the thief was moved to, but in String format
     * @return int of cicleID */
    public static int convertStringIDtoIntID(String circleID){
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

    public static void checkStealableOppenets(int tileID) throws IOException {
        ArrayList<Player> opponents = ThiefController.findOpponentsOnTile(tileID);

        if(opponents.isEmpty()) { // There are no opponents to steal from
            return; }
        else if(opponents.size() > 1) { // There are more opponents to steal from
            ScreenController.getInstance().showStealPopUp(); // Choose opponent popup
            StealPopUpController.getInstance().updateOpponents(opponents);
            return;
        }
    }

    public static void stealOppenets(int tileID){
        ArrayList<Player> opponents = ThiefController.findOpponentsOnTile(tileID);

        Player victim = opponents.get(0); // There is one opponent to steal from
        App.getClientPlayer().stealFromVictim(victim);
    }

    /** Finds what opponents are potential victims for stealing by looking at the settlements that border the tileID's tile.
     * @param tileID the id of the tile the thief was moved to
     * @return arrayList with the opponents as Player objects
     * @author Jeroen */
    // TODO desert tile always seem to not have any settlements bordered to it
    private static ArrayList<Player> findOpponentsOnTile(int tileID) {
        Map<String, Integer> colorToCount = new HashMap<>();
        ArrayList<Player> opponents = new ArrayList<>();
        int opponentCount = 0;
        for (Village settlement : BuildVillages.getBuildVillages()) { // Loop through all settlements
            // TODO this if statement can't be tested properly since colors aren't implemented yet
            if (!settlement.getColor().equals(App.getCurrentGame().turnPlayerGetter().getColor())) { // If settlement isn't player's
                ArrayList<Tile> connectedTiles = settlement.getConnectedTiles(); // Get the connected tiles for each settlement
                for (Tile tile : connectedTiles) {
                    if (Integer.parseInt(tile.getId().replaceAll("tile", "")) == tileID) { // Tile's ID is saved as "tileX" and tileID is an integer
                        opponentCount++;
                        colorToCount.put(settlement.getColor(), opponentCount);
                    }
                }
            }
        }
        for (Map.Entry<String, Integer> entry : colorToCount.entrySet()) { // Get opponent's Player object by color and add to opponents list
            for(Player player : App.getCurrentGame().getPlayers()) {
                if(player.getColor().equals(entry.getKey())) {
                    opponents.add(player);
                }
            }
        }
        return opponents;
    }

    @Override
    public void update(Game game) {
        System.out.println("Old thief tile: " + App.getCurrentGame().getBoard().getThief().getTile());
        System.out.println("New thief tile: " + game.getBoard().getThief().getTile());
        if (App.getCurrentGame().getBoard().getThief().getTile() != game.getBoard().getThief().getTile()){
            GameSchermController.getInstance().updateThief(game.getBoard().getThief().getTile());
            App.getCurrentGame().getBoard().getThief().setTile(game.getBoard().getThief().getTile());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thiefController = this;
    }
}
