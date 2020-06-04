package org.catan.Model;

public class Thief {
    private Tile tile;
    private Player player;

    public void setTile(Tile tile) {
        this.tile= tile;
    }

    private Tile getTile() {
        return this.tile;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
