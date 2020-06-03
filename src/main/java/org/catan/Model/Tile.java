package org.catan.Model;

public class Tile {
    private String type;
    private String id;
    private int number;


    public Tile(String type, String id){
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public String getId() {
        return this.id;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }
}