package org.catan.Model;

public class Thief {
    private Tile tile;
//    private double x;
//    private double y;
    private String imgPath;

    public Thief(Tile tile) {
//        this.x = x;
//        this.y = y;
        this.imgPath = "assets/img/gameobjects/Thief.png";
    }

//    public double getX() {
//        return this.x;
//    }
//
//    public double getY() {
//        return this.y;
//    }

    public String getImgPath() {
        return this.imgPath;
    }

    public void setTile(Tile tile) {
        this.tile= tile;
    }

    private Tile getTile() {
        return this.tile;
    }
}
