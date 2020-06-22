package org.catan.Controller;

import javafx.fxml.Initializable;
import org.catan.App;
import org.catan.Model.*;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ResourcesController implements Initializable {

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
        ArrayList<String> receivedResources = new ArrayList<>();
            for (Village village : App.getCurrentGame().getBoard().getSettlements()) {
                for (Tile tile : village.getConnectedTiles()){
                    int amount;
                    if(village.isUpgraded()){
                        amount = 2;
                    } else {
                        amount = 1;
                    }
                    if(total == tile.getNumber() && village.getColor().equals(App.getClientPlayer().getColor()) && !tile.getId().equals(getThiefTileId())){
                        App.getClientPlayer().getPlayerInventory().changeCards(tile.getType(), amount);
                        updateGamePlayer(App.getClientPlayer());
                        receivedResources.add(tile.getType());
                    }
                }


            if(!receivedResources.isEmpty()) {
                DatabaseConnector.getInstance().updateGame(App.getCurrentGame());
                LogController.getInstance().logReceiveEvent(receivedResources);
            }
        }
    }

    private String getThiefTileId() {
        return "tile" + App.getCurrentGame().getBoard().getThief().getTile();
    }

    private void updateGamePlayer(Player player) {
        for (Player players : App.getCurrentGame().getPlayers()) {
            if (player.getIdentifier() == players.getIdentifier()) {
                players.setPlayerInventory(player.getPlayerInventory());
            }
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resourcesController = this;
    }
}
