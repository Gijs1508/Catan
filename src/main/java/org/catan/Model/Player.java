package org.catan.Model;

public class Player {

    private String name;
    private String color;
    private Inventory playerInventory;

    public Player(String name){
        this.name = name;
        this.color = ""; //TODO
        this.playerInventory = new Inventory();
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
