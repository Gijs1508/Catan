package org.catan.Model;

/**
 * Class that contains information about the thief.
 */

public class Thief {
    private int tile = 10;

    public Thief() {
    }

    public void setTile(int tileNumber) {
        tile = tileNumber;
    }

    public int getTile() {
        return tile;
    }

}
