package org.catan.Model;

public class Tile {
    private String type;
    private String id;


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

}
