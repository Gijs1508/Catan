package org.catan.Model;

import java.util.Objects;

/**
 * This model is the road
 * @author Jan
 */

public class Road {
    private double x;
    private double y;
    private String imgPath;
    private String color;

    public Road() {
    }


    public Road(double x, double y, String color) {
        this.x = x;
        this.y = y;
        this.imgPath = getFilePath(color);
        this.color = color;
    }

    private String getFilePath(String color) {
        switch (color) {
            case "red":
                return "assets/img/gameobjects/wall_red.png";
            case "blue":
                return "assets/img/gameobjects/wall_blue.png";
            case "green":
                return "assets/img/gameobjects/wall_green.png";
            case "yellow":
                return  "assets/img/gameobjects/wall_yellow.png";
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
        return this.imgPath;
    }

    public String getColor() {
        return this.color;
    }


    /**
     * This is necessary to use the equals for the roads
     * @author Jan
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return Double.compare(road.x, x) == 0 &&
                Double.compare(road.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
