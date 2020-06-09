package org.catan.Model;

import org.catan.Controller.LogController;
import org.catan.Controller.TradeController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Player {

    private String name;
    private String color;
    private Inventory playerInventory;

    private HashMap<String, Integer> resourceToCost;
    private TradeController tradeController = TradeController.getInstance();

    public static Player mainPlayer; // Player controlling the instance of the game
    public static ArrayList<Player> allPlayers = new ArrayList<Player>(); //TODO Moet aangemaakt worden in de Lobby of bij het opstarten van het spel
    public static Player activePlayer;
    public static boolean mainPlayerActive;



    public Player(String name){
        this.name = name;
        this.color = ""; //TODO
        this.playerInventory = new Inventory();
        allPlayers.add(this);

        initializeResourceCosts();
    }

    /*** Initializes the resource costs of bank trade (everything starts with a cost of 1:4)
     * @author Jeroen */
    private void initializeResourceCosts() {
        resourceToCost = new HashMap<>(){{
            put("wheat", 4); put("wood", 4);
            put("brick", 4); put("wool", 4);
            put("ore", 4);
        }};
    }

    /*** Updates resource costs for the player object and the player's trade view.
     * Takes harbor's type and ratio (costs) as a parameter since it's the only relevant information.
     * If the type is any, all values in resourceToCost get updated (unless the ratio has already been updated).
     * @param harbor harbor object that settlement has been placed adjacent to
     * @author Jeroen */
    public void updateResourceCosts(Harbor harbor) {
        if(harbor.getType().equals("any")) {
            Iterator it = resourceToCost.entrySet().iterator();
            while (it.hasNext()) { // Iterate through the resourceToCost HashMap
                Map.Entry pair = (Map.Entry) it.next();
                if((Integer) pair.getValue() > harbor.getRatio()) { // Only update if the old value is higher than the new value
                    resourceToCost.replace((String) pair.getKey(), harbor.getRatio());
                    tradeController.updateRatioView((String) pair.getKey(), harbor.getRatio());
                }
            }
        }
        else {
            resourceToCost.replace(harbor.getType(), harbor.getRatio());
            tradeController.updateRatioView(harbor.getType(), harbor.getRatio());
        }

    }

    public static Player getMainPlayer() {
        return mainPlayer;
    }

    public void setMainPlayer(Player player){
        mainPlayer = player;
    }

    public String getName(){
        return this.name;
    }

    public String getColor(){
        return this.color;
    }

    public Inventory getPlayerInventory(){
        return this.playerInventory;
    }

    public static ArrayList<Player> getAllPlayers(){
        return allPlayers;
    }

    public static void setActivePlayer(Player player){
        activePlayer = player;
        LogController.setPlayer();
        if (player == mainPlayer){
            mainPlayerActive = true;
            Sound.playStartTurnJingle();
            System.out.println("MAIN PLAYER ACTIVE");
        } else{
            mainPlayerActive = false;
        }
    }

    public static Player getActivePlayer(){
        return activePlayer;
    }

    public static boolean isMainPlayerActive(){
        return mainPlayerActive;
    }

    public int getCostOf(String resource) {
        return resourceToCost.get(resource);
    }

}
