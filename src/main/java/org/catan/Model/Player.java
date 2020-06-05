package org.catan.Model;

import org.catan.Controller.LogController;

import java.util.ArrayList;

public class Player {

    private String name;
    private String color;
    private Inventory playerInventory;
    private boolean active = false;

    public static Player mainPlayer; // Player controlling the instance of the game
    public static ArrayList<Player> allPlayers = new ArrayList<Player>(); //TODO Moet aangemaakt worden in de Lobby of bij het opstarten van het spel
    public static Player activePlayer;
    public static boolean mainPlayerActive;


    public Player(String name){
        this.name = name;
        this.color = ""; //TODO
        this.playerInventory = new Inventory();
        allPlayers.add(this);
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
        player.setActive();
        LogController.setPlayer();
        if (player == mainPlayer){
            mainPlayerActive = true;
            System.out.println("MAIN PLAYER ACTIVE");
        } else{
            mainPlayerActive = false;
        }
    }

    public static Player getActivePlayer(){
        return activePlayer;
    }

    public void setActive(){
        active = true;
    }

    public static boolean isMainPlayerActive(){
        return mainPlayerActive;
    }

}
