package org.catan.Helper;

import javafx.scene.shape.Circle;
import org.catan.Model.RandomizeBoard;
import org.catan.Model.Tile;
import java.util.ArrayList;
import java.util.HashMap;

public class PolygonConnectedNodes {

    private ArrayList<Circle> vertexNodes = null;
    private ArrayList<Tile> tiles;
    private HashMap<Integer, ArrayList<Circle>> polygonsConnected = null;

    public PolygonConnectedNodes(ArrayList<Circle> vertexNodes) {
        this.vertexNodes = vertexNodes;
        this.tiles = RandomizeBoard.getTiles();
        this.polygonsConnected = getPolygons();
    }

    public ArrayList<Tile> getConnectedTiles(double x, double y) {
        ArrayList<Tile> connectedTiles = new ArrayList<>();
        for (int i=0; i < polygonsConnected.size(); i++) {
            if (i != 9) {
                for (int j=0; j < polygonsConnected.get(i).size(); j++) {
                    if (polygonsConnected.get(i).get(j).getLayoutX() == x && polygonsConnected.get(i).get(j).getLayoutY() == y) {
                        connectedTiles.add(getTile(i + 1));
                    }
                }
            }
        }
        return connectedTiles;
    }

    private Tile getTile(int polygonNumber) {
        Tile returnTile = null;
        String tile = "tile" + polygonNumber;
        for (int i=0; i < tiles.size(); i++) {
            if (tiles.get(i).getId().equals(tile)) {
                returnTile = tiles.get(i);
                break;
            }
        }
        return returnTile;
    }

    private HashMap<Integer, ArrayList<Circle>> getPolygons() {
        HashMap<Integer, ArrayList<Circle>> polygons =  new HashMap<>();
        polygons.put(0,tile1());
        polygons.put(1,tile2());
        polygons.put(2,tile3());
        polygons.put(3,tile4());
        polygons.put(4,tile5());
        polygons.put(5,tile6());
        polygons.put(6,tile7());
        polygons.put(7,tile8());
        polygons.put(8,tile9());
        polygons.put(9,tile9());
        polygons.put(10,tile11());
        polygons.put(11,tile12());
        polygons.put(12,tile13());
        polygons.put(13,tile14());
        polygons.put(14,tile15());
        polygons.put(15,tile16());
        polygons.put(16,tile17());
        polygons.put(17,tile18());
        polygons.put(18,tile19());
        return polygons;
    }

    private ArrayList<Circle> tile1() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(0));
        nodes.add(vertexNodes.get(1));
        nodes.add(vertexNodes.get(2));
        nodes.add(vertexNodes.get(8));
        nodes.add(vertexNodes.get(9));
        nodes.add(vertexNodes.get(10));
        return nodes;
    }

    private ArrayList<Circle> tile2() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(2));
        nodes.add(vertexNodes.get(3));
        nodes.add(vertexNodes.get(4));
        nodes.add(vertexNodes.get(10));
        nodes.add(vertexNodes.get(11));
        nodes.add(vertexNodes.get(12));
        return nodes;
    }

    private ArrayList<Circle> tile3() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(4));
        nodes.add(vertexNodes.get(5));
        nodes.add(vertexNodes.get(6));
        nodes.add(vertexNodes.get(12));
        nodes.add(vertexNodes.get(13));
        nodes.add(vertexNodes.get(14));
        return nodes;
    }

    private ArrayList<Circle> tile4() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(7));
        nodes.add(vertexNodes.get(8));
        nodes.add(vertexNodes.get(9));
        nodes.add(vertexNodes.get(17));
        nodes.add(vertexNodes.get(18));
        nodes.add(vertexNodes.get(19));
        return nodes;
    }

    private ArrayList<Circle> tile5() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(9));
        nodes.add(vertexNodes.get(10));
        nodes.add(vertexNodes.get(11));
        nodes.add(vertexNodes.get(19));
        nodes.add(vertexNodes.get(20));
        nodes.add(vertexNodes.get(21));
        return nodes;
    }

    private ArrayList<Circle> tile6() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(11));
        nodes.add(vertexNodes.get(12));
        nodes.add(vertexNodes.get(13));
        nodes.add(vertexNodes.get(21));
        nodes.add(vertexNodes.get(22));
        nodes.add(vertexNodes.get(23));
        return nodes;
    }

    private ArrayList<Circle> tile7() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(13));
        nodes.add(vertexNodes.get(14));
        nodes.add(vertexNodes.get(15));
        nodes.add(vertexNodes.get(23));
        nodes.add(vertexNodes.get(24));
        nodes.add(vertexNodes.get(25));
        return nodes;
    }

    private ArrayList<Circle> tile8() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(16));
        nodes.add(vertexNodes.get(17));
        nodes.add(vertexNodes.get(18));
        nodes.add(vertexNodes.get(27));
        nodes.add(vertexNodes.get(28));
        nodes.add(vertexNodes.get(29));
        return nodes;
    }

    private ArrayList<Circle> tile9() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(18));
        nodes.add(vertexNodes.get(19));
        nodes.add(vertexNodes.get(20));
        nodes.add(vertexNodes.get(29));
        nodes.add(vertexNodes.get(30));
        nodes.add(vertexNodes.get(31));
        return nodes;
    }

    private ArrayList<Circle> tile11() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(22));
        nodes.add(vertexNodes.get(23));
        nodes.add(vertexNodes.get(24));
        nodes.add(vertexNodes.get(33));
        nodes.add(vertexNodes.get(34));
        nodes.add(vertexNodes.get(35));
        return nodes;
    }

    private ArrayList<Circle> tile12() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(24));
        nodes.add(vertexNodes.get(25));
        nodes.add(vertexNodes.get(26));
        nodes.add(vertexNodes.get(35));
        nodes.add(vertexNodes.get(36));
        nodes.add(vertexNodes.get(37));
        return nodes;
    }

    private ArrayList<Circle> tile13() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(28));
        nodes.add(vertexNodes.get(29));
        nodes.add(vertexNodes.get(30));
        nodes.add(vertexNodes.get(38));
        nodes.add(vertexNodes.get(39));
        nodes.add(vertexNodes.get(40));
        return nodes;
    }

    private ArrayList<Circle> tile14() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(30));
        nodes.add(vertexNodes.get(31));
        nodes.add(vertexNodes.get(32));
        nodes.add(vertexNodes.get(40));
        nodes.add(vertexNodes.get(41));
        nodes.add(vertexNodes.get(42));
        return nodes;
    }

    private ArrayList<Circle> tile15() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(32));
        nodes.add(vertexNodes.get(33));
        nodes.add(vertexNodes.get(34));
        nodes.add(vertexNodes.get(42));
        nodes.add(vertexNodes.get(43));
        nodes.add(vertexNodes.get(44));
        return nodes;
    }

    private ArrayList<Circle> tile16() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(34));
        nodes.add(vertexNodes.get(35));
        nodes.add(vertexNodes.get(36));
        nodes.add(vertexNodes.get(44));
        nodes.add(vertexNodes.get(45));
        nodes.add(vertexNodes.get(46));
        return nodes;
    }

    private ArrayList<Circle> tile17() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(39));
        nodes.add(vertexNodes.get(40));
        nodes.add(vertexNodes.get(41));
        nodes.add(vertexNodes.get(47));
        nodes.add(vertexNodes.get(48));
        nodes.add(vertexNodes.get(49));
        return nodes;
    }

    private ArrayList<Circle> tile18() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(41));
        nodes.add(vertexNodes.get(42));
        nodes.add(vertexNodes.get(43));
        nodes.add(vertexNodes.get(49));
        nodes.add(vertexNodes.get(50));
        nodes.add(vertexNodes.get(51));
        return nodes;
    }

    private ArrayList<Circle> tile19() {
        ArrayList<Circle> nodes = new ArrayList<>();
        nodes.add(vertexNodes.get(43));
        nodes.add(vertexNodes.get(44));
        nodes.add(vertexNodes.get(45));
        nodes.add(vertexNodes.get(51));
        nodes.add(vertexNodes.get(52));
        nodes.add(vertexNodes.get(53));
        return nodes;
    }

}
