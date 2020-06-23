package org.catan.Model;

/**
 * Contains information about a single tile
 */

public class Tile {
    private String type;
    private String id;
    private int number;

    public Tile() {
    }

    public Tile(String type, String id, Integer number){
        this.type = type;
        this.id = id;
        this.number = number;
    }

    public String getType() {
        return this.type;
    }

    public String getId() {
        return this.id;
    }

    public int getNumber() {
        return this.number;
    }
}
