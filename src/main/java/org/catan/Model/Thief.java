package org.catan.Model;

public class Thief {
    private static int tile = 10;

    public Thief() {
    }

    public static void setTile(int tilenr) {
        tile = tilenr;
    }

    public static int getTile() {
        return tile;
    }
}
