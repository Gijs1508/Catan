package org.catan.Controller;

import org.catan.App;
import org.catan.Model.Game;
import org.catan.Model.Log;
import org.catan.Model.Tile;
import org.catan.Model.Village;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;

import java.io.IOException;
import java.util.ArrayList;

public class ResourcesController implements Observable {

    private static ResourcesController resourcesController;

    public ResourcesController(){
    }

    public static ResourcesController getInstance(){
        if(resourcesController == null){
            resourcesController = new ResourcesController();
        }
        return resourcesController;
    }

    public void setPlayerResources(int total){
        ArrayList<Tile> usedTiles = new ArrayList<>();
        ArrayList<String> receivedResources = new ArrayList<>();
        if(App.getCurrentGame().getBoard().getSettlements() != null) {
            for (Village village : App.getCurrentGame().getBoard().getSettlements()) {
                for (Tile tile : village.getConnectedTiles()){
                    int amount;
                    if(village.isUpgraded()){
                        amount = 2;
                    } else {
                        amount = 1;
                    }
                    if(total == tile.getNumber() && !usedTiles.contains(tile) && village.getColor().equals(App.getClientPlayer().getColor())){
                        App.getClientPlayer().getPlayerInventory().changeCards(tile.getType(), amount);
                        receivedResources.add(tile.getType());
                        usedTiles.add(tile);
                        DatabaseConnector.getInstance().updateGame(App.getCurrentGame());
                    }
                }
            }


            if(!receivedResources.isEmpty()) {
                LogController.getInstance().logReceiveEvent(receivedResources);
            }
        }
    }

    @Override
    public void update(Game game) {
        
    }

    private int getIntFromImgPath(String imgpath) {
        switch (imgpath) {
            case "org/catan/assets/img/die/die1.png":
                return 1;
            case "org/catan/assets/img/die/die2.png":
                return 2;
            case "org/catan/assets/img/die/die3.png":
                return 3;
            case "org/catan/assets/img/die/die4.png":
                return 4;
            case "org/catan/assets/img/die/die5.png":
                return 5;
            case "org/catan/assets/img/die/die6.png":
                return 6;
            default:
                return 0;
        }
    }

    public void updateByLog(Log log) {
        String diceResult1 = log.getImgPaths().get(0);
        String diceResult2 = log.getImgPaths().get(1);
        int diceTotal = getIntFromImgPath(diceResult1) + getIntFromImgPath(diceResult2);
        setPlayerResources(diceTotal);
    }
}
