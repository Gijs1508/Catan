package org.catan.Model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This is model is the village
 * @author Jan
 */

public class Village {
    private double x;
    private double y;
    private String color;
    private boolean upgraded = false;
    private ArrayList<Tile> connectedTiles = new ArrayList<>();

    public Village() {
    }

    public Village(double x, double y, String color, ArrayList<Tile> connectedTiles) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.connectedTiles = connectedTiles;
    }

    // Returns the right filepath for village
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

    // Returns the right filepath for city
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

    // Returns x coordinate
    public double getX() {
        return this.x;
    }

    // Returns y coordinate
    public double getY() {
        return this.y;
    }

    public String imgPath() {
        if (upgraded)
            return getFilePathCity();
        else
            return getFilePathVillage();
    }

    public String getColor() {
        return this.color;
    }

    /**
     * Sets the upgrade status of a village
     * @param upgraded true is a city and false (default) a village
     * @author Jan
     */
    public void setUpgraded(boolean upgraded) {
        this.upgraded = upgraded;
    }

    public boolean isUpgraded() {
        return upgraded;
    }

    public ArrayList<Tile> getConnectedTiles() {
        return connectedTiles;
    }

    /**
     * This is necessary to use the equals for the villages
     * @author Jan
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Village village = (Village) o;
        return Double.compare(village.x, x) == 0 &&
                Double.compare(village.y, y) == 0 &&
                upgraded == village.upgraded &&
                color.equals(village.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, color, upgraded);
    }
}
