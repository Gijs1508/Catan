package org.catan.Model;

import java.util.ArrayList;

public class Village {
    private double x;
    private double y;
    private String color;
    private boolean upgraded = false;
    private ArrayList<Tile> connectedTiles = new ArrayList<>();

    public Village(double x, double y, String color, ArrayList<Tile> connectedTiles) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.connectedTiles = connectedTiles;
    }

    private String getFilePathVillage() {
        switch (color) {
            case "red":
                return "assets/img/gameobjects/village_red.png";
            case "blue":
                return "assets/img/gameobjects/village_blue.png";
            case "green":
                return "assets/img/gameobjects/village_green.png";
            case "yellow":
                return  "assets/img/gameobjects/village_yellow.png";
            default:
                return "null";
        }
    }

    private String getFilePathCity() {
        switch (color) {
            case "red":
                return "assets/img/gameobjects/city_red.png";
            case "blue":
                return "assets/img/gameobjects/city_blue.png";
            case "green":
                return "assets/img/gameobjects/city_green.png";
            case "yellow":
                return  "assets/img/gameobjects/city_yellow.png";
            default:
                return "null";
        }
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public String getImgPath() {
        if (upgraded)
            return getFilePathCity();
        else
            return getFilePathVillage();
    }

    public String getColor() {
        return this.color;
    }

    public void setUpgraded(boolean upgraded) {
        this.upgraded = upgraded;
    }

    public boolean isUpgraded() {
        return upgraded;
    }

    public ArrayList<Tile> getConnectedTiles() {
        return connectedTiles;
    }
}
