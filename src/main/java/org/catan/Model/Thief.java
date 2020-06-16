package org.catan.Model;

public class Thief {
    private static int tile = 10;
    private Object Thief;

    public Thief() {
    }

    public static void setTile(int tileNumber) {
        tile = tileNumber;
    }

    public static int getTile() {
        return tile;
    }

    public static void setThief(Object Thief) { Thief = Thief; }
}
