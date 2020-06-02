package org.catan.Model;

public class Village {
    private double x;
    private double y;
    private String imgPath;
    private String color;

    public Village(double x, double y, String color) {
        this.x = x;
        this.y = y;
        this.imgPath = getFilePath(color);
        this.color = color;
    }

    private String getFilePath(String color) {
        switch (color) {
            case "red":
                return "org/catan/assets/img/gameobjects/village_red.png";
            case "blue":
                return "org/catan/assets/img/gameobjects/village_blue.png";
            case "green":
                return "org/catan/assets/img/gameobjects/village_green.png";
            case "yellow":
                return  "org/catan/assets/img/gameobjects/village_yellow.png";
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
}