package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import org.catan.Model.Road;
import org.catan.Model.Village;

import java.util.*;

public class BuildSettlementController {
//    private Speler player;
    private String color = "blue";
    private ArrayList<Circle> vertexNodeList = new ArrayList<>();
    private ArrayList<Circle> roadSpotNodeList = new ArrayList<>();

    private ArrayList<Road> buildRoads = new ArrayList<>();
    private ArrayList<Village> buildVillages = new ArrayList<>();

    @FXML private Pane objectsPane;

    public BuildSettlementController(ArrayList<Circle> vertexNodeList, ArrayList<Circle> roadSpotNodeList) {
        this.vertexNodeList = vertexNodeList;
        this.roadSpotNodeList = roadSpotNodeList;
        buildRoads.add(new Road(226.0, 41.0, "blue"));
        buildRoads.add(new Road(278.0, 40.0, "blue"));
        buildRoads.add(new Road(380.0, 121.0, "blue"));
        showVillageSpots();
    }

    // Returns the circles that are next to the circle you put in the function
    // x and y are the selected circle, ArrayList is the list from player, Type is what you want it to check road/village road/road ect
    private ArrayList<Circle> circlesInRadius(double x, double y, ArrayList<Circle> a, String type) {
        int radius = radius(type);
        ArrayList<Circle> inRadius = new ArrayList<>();
        for (Circle circle : a) {
            if (circle.getLayoutX() == x && circle.getLayoutY() == y) {
            } else {
                double distance = distance(x, y, circle.getLayoutX(), circle.getLayoutY());
                if (distance <= radius) {
                        inRadius.add(circle);
                }
            }
        }
        return inRadius; // Nodes that are in the radius
    }
    
    // Measures the distance between points
    private double distance(double xa, double ya, double xb, double yb) {
        double xTotal = Math.pow(xb - xa, 2);
        double yTotal = Math.pow(yb - ya, 2);
        return Math.sqrt(xTotal + yTotal);
    }

    // Returns the correct radius for that type
    private int radius(String type) {
        if (type.equals("Road") || type.equals("road")) // Road next to road
            return 63;
        else if (type.equals("Village") || type.equals("village")) // Village next to village
            return 69;
        else {
            return 41; // Road next to village and visa versa
        }
    }

    private ArrayList<Road> playerRoads() {
        ArrayList<Road> playerRoads = new ArrayList<>();
        for (int i=0; i < buildRoads.size(); i++) {
            if (buildRoads.get(i).getColor().equals(this.color)) {
                playerRoads.add(buildRoads.get(i));
            }
        }
        return playerRoads;
    }

//    private ArrayList<Village> playerVillages() {
//        ArrayList<Village> playerVillages = new ArrayList<>();
//        for (int i=0; i < buildRoads.size(); i++) {
//            if (buildVillages.get(i).getColor().equals(this.color)) {
//                playerVillages.add(buildVillages.get(i));
//            }
//        }
//        return playerVillages;
//    }

    public ArrayList<Circle> showVillageSpots() {
        ArrayList<Road> roadsConnected = roadsConnected(); // Gives roads that have a minimum length of 2
        ArrayList<Circle> nodes = new ArrayList<>();
        for (int i=0; i < roadsConnected.size(); i++) {
            nodes.addAll(circlesInRadius(roadsConnected.get(i).getX(), roadsConnected.get(i).getY(), roadSpotNodeList, "road"));
        }
        nodes = filterOwnRoads(nodes, roadsConnected);

        ArrayList<Circle> nodesNodes = new ArrayList<>();
        for (int i=0; i < nodes.size(); i++) {
            nodesNodes.addAll(circlesInRadius(nodes.get(i).getLayoutX(), nodes.get(i).getLayoutY(), vertexNodeList, "other"));
        }
        ArrayList<Circle> roadsConnectedNodes = new ArrayList<>();
        for (int i=0; i < roadsConnected.size(); i++) {
            roadsConnectedNodes.addAll(circlesInRadius(roadsConnected.get(i).getX(), roadsConnected.get(i).getY(), vertexNodeList, "other"));
        }

        nodesNodes = removeDuplicates(nodesNodes);
        roadsConnectedNodes = removeDuplicates(roadsConnectedNodes);
        ArrayList<Circle> nodeOutput = new ArrayList<>();
        nodeOutput.addAll(nodesNodes);
        nodeOutput.addAll(roadsConnectedNodes);
//        for (int i=0; i < nodesNodes.size(); i++) {
//            for (int j=0; j < roadsConnected.size(); j++) {
//                if (nodesNodes.get(i).getLayoutX() == roadsConnectedNodes.get(j).getLayoutX() && nodesNodes.get(i).getLayoutY() == roadsConnectedNodes.get(j).getLayoutY()) {
//                    nodeOutput.add(nodesNodes.get(i));
//                }
//            }
//        }
        nodeOutput = removeNonDuplicates(nodeOutput);
        print(nodeOutput);

        return nodes;
    }

    // Removes the roads that are already placed
    private ArrayList<Circle> filterOwnRoads(ArrayList<Circle> ci, ArrayList<Road> r) {
        ArrayList<Circle> c = ci;
        for (int i=0; i < c.size(); i++) {
            for (int j=0; j < r.size(); j++) {
                if (c.get(i).getLayoutX() == r.get(j).getX() && c.get(i).getLayoutY() == r.get(j).getY()) {
                    c.remove(i);
                }
            }
        }
        return c;
    }

    private ArrayList<Circle> removeDuplicates(ArrayList<Circle> array) {
        ArrayList<Circle> arrayFixed = new ArrayList<>();
        for (Circle circle : array) {
            if (!arrayFixed.contains(circle)) {
                arrayFixed.add(circle);
            }
        }
        return arrayFixed;
    }

    private ArrayList<Circle> removeNonDuplicates(ArrayList<Circle> array) {
        ArrayList<Circle> arrayFixed = new ArrayList<>();
        ArrayList<Circle> arrayCopy = array;
        for (Circle circle : array) {
            if (!arrayFixed.contains(circle)) {
                arrayCopy.remove(circle);
                arrayFixed.add(circle);
            }
        }
        return arrayCopy;
    }

    // Returns roads that lie next to another road
    private ArrayList<Road> roadsConnected() {
        ArrayList<Road> playerRoads = playerRoads();
        ArrayList<Road> roadsConnected = new ArrayList<>();
        for (int i=0; i < playerRoads.size(); i++) {
            for (int j=0; j < playerRoads.size(); j++) {
                if (playerRoads.get(i).getX() == playerRoads.get(j).getX() && playerRoads.get(i).getY() == playerRoads.get(j).getY()) {
                } else {
                    double distance = distance(playerRoads.get(i).getX(), playerRoads.get(i).getY(), playerRoads.get(j).getX(), playerRoads.get(j).getY());
                    if (distance <= 63) {
                        roadsConnected.add(playerRoads.get(i));
                    }
                }
            }
        }
        return roadsConnected;
        
    }

//    public void buildVillage(double x, double y) {
//        ArrayList<Circle> nearbyRoads = circlesInRadius(x, y, vertexNodeList, "roadNextToVillage");
//        ArrayList<Circle> roadsNextToRoads = circlesInRadius(x, y, nearbyRoads, "road");
//        objectsPane.getChildren().add()
//    }

    // Prints coordinates ArrayList
    private void print(ArrayList<Circle> c) {
        System.out.println("Print initialized");
        for (int i=0; i < c.size(); i++) {
            System.out.println("This is X: " + c.get(i).getLayoutX());
            System.out.println("This is Y: " + c.get(i).getLayoutY());
        }
    }

}
