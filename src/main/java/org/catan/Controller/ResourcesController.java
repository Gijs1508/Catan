package org.catan.Controller;

import org.catan.App;
import org.catan.Model.Game;
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
    public void update(Game game) throws IOException {
        
    }
}
