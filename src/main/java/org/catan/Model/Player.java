package org.catan.Model;

public class Player {

    private String name;
    private String color;
    private Inventory playerInventory;
    public static Player mainPlayer; // Player controlling the instance of the game


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
