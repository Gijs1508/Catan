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

public class ResourcesController implements Initializable, Observable {

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
                        System.out.println("Player color: " + App.getClientPlayer().getColor());
                        App.getClientPlayer().getPlayerInventory().changeCards(tile.getType(), amount);
                        System.out.println(Arrays.toString(App.getClientPlayer().getPlayerInventory().getCards()));
                        updateGamePlayer(App.getClientPlayer());
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

    private void updateGamePlayer(Player player) {
        for (int i = 0; i < App.getCurrentGame().getPlayers().size(); i++) {
            if (player.getIdentifier() == App.getCurrentGame().getPlayers().get(i).getIdentifier()) {
                App.getCurrentGame().getPlayers().get(i).setPlayerInventory(player.getPlayerInventory());
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
        System.out.println("Go into resource update");
        String diceResult1 = log.getImgPaths().get(0);
        String diceResult2 = log.getImgPaths().get(1);
        int diceTotal = getIntFromImgPath(diceResult1) + getIntFromImgPath(diceResult2);
        System.out.println("Dice total: " + diceTotal);
        setPlayerResources(diceTotal);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resourcesController = this;
    }
}
