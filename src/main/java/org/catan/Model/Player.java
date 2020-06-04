package org.catan.Model;

import java.util.ArrayList;

public class Player {

    private String name;
    private String color;
    private Inventory playerInventory;
//    private ArrayList<SpelObject> spelerObjecten = new ArrayList<SpelObject>();
    public static Player mainPlayer; // Player controlling the instance of the game

    public void setName(String name) {
        this.name = name;
    }


    public void setColor(String color) {
        this.color = color;
    }

    public Player(String name){
        this.name = name;
        this.color = ""; //TODO
        this.playerInventory = new Inventory();
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
}
