package org.catan.Model;

public class Thief {
    private Tile tile;
    private Speler speler;

    public void setTile(Tile tile) {
        this.tile= tile;
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    private Tile getTile() {
        return this.tile;
    }

    private Speler getSpeler() {
        return this.speler;
    }
}
