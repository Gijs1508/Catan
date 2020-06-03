package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import org.catan.App;
import org.catan.Helper.MathBuildSettlement;
import org.catan.Model.Road;
import org.catan.Model.Village;
import java.util.*;

public class BuildSettlementController {
//    private Speler player;
    private String color = "blue";
    private ArrayList<Circle> vertexNodeList = new ArrayList<>();
    private ArrayList<Circle> upgradeNodeList = new ArrayList<>();
    private ArrayList<Circle> roadSpotNodeList = new ArrayList<>();

    private ArrayList<Road> buildRoads = new ArrayList<>();
    private ArrayList<Village> buildVillages = new ArrayList<>();
    private MathBuildSettlement math;

    @FXML private Pane objectsPane;

    public BuildSettlementController(ArrayList<Circle> vertexNodeList, ArrayList<Circle> roadSpotNodeList,
                                     ArrayList<Circle> upgradeNodeList) {
        this.vertexNodeList = vertexNodeList;
        this.upgradeNodeList = upgradeNodeList;
        this.roadSpotNodeList = roadSpotNodeList;
        buildRoads.add(new Road(226.0, 41.0, "blue"));
        buildRoads.add(new Road(278.0, 40.0, "blue"));
        buildRoads.add(new Road(380.0, 121.0, "blue"));
        this.math = new MathBuildSettlement();
        showUpgradeableVillages();
    }

    // Returns roads from player
    private ArrayList<Road> playerRoads() {
        ArrayList<Road> playerRoads = new ArrayList<>();
        for (Road buildRoad : buildRoads) {
            if (buildRoad.getColor().equals(this.color)) {
                playerRoads.add(buildRoad);
            }
        }
        return playerRoads;
    }

    // Returns villages from player
    public ArrayList<Village> playerVillages() {
        ArrayList<Village> playerVillages = new ArrayList<>();
        for (Village buildVillage : buildVillages) {
            if (buildVillage.getColor().equals(this.color)) {
                playerVillages.add(buildVillage);
            }
        }
        return playerVillages;
    }

    // Gives available node for placing a village
    public ArrayList<Circle> showVillageSpots() {
        ArrayList<Road> roadsConnected = roadsConnected(); // Gives roads that have a minimum length of 2
        ArrayList<Circle> nodes = new ArrayList<>();
        for (int i=0; i < roadsConnected.size(); i++) {
            nodes.addAll(math.circlesInRadius(roadsConnected.get(i).getX(), roadsConnected.get(i).getY(), roadSpotNodeList, "road"));
        }
        nodes = filterOwnRoads(nodes, roadsConnected);

        ArrayList<Circle> nodesNodes = roadsNextToVillageSpot(nodes);
        ArrayList<Circle> roadsConnectedNodes = roadsNextToVillageSpotRoad(roadsConnected);
        return villageSpotAvailable(removeNonDuplicates(nodesNodes, roadsConnectedNodes));
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

    // Removes duplicates in array
    private ArrayList<Circle> removeDuplicates(ArrayList<Circle> array) {
        ArrayList<Circle> arrayFixed = new ArrayList<>();
        for (Circle circle : array) {
            if (!arrayFixed.contains(circle)) {
                arrayFixed.add(circle);
            }
        }
        return arrayFixed;
    }

    // Removes the uniques from two ArrayLists
    private ArrayList<Circle> removeNonDuplicates(ArrayList<Circle> array, ArrayList<Circle> array2) {
        ArrayList<Circle> arrayFixed = new ArrayList<>();
        for (Circle circle : array) {
            if (array2.contains(circle)) {
                arrayFixed.add(circle);
            }
        }
        return arrayFixed;
    }

    // Checks if the available spots don't have a settlement already
    private ArrayList<Circle> villageSpotAvailable(ArrayList<Circle> nodes) {
        for (int i=0; i < nodes.size(); i++) {
            for (Village buildVillage : buildVillages) {
                if (nodes.get(i).getLayoutX() == buildVillage.getX() && nodes.get(i).getLayoutY() == buildVillage.getY()) {
                    nodes.remove(i);
                }
            }
        }
        return nodes;
    }

    // Returns roads that lie next to another road
    private ArrayList<Road> roadsConnected() {
        ArrayList<Road> playerRoads = playerRoads();
        ArrayList<Road> roadsConnected = new ArrayList<>();
        for (int i=0; i < playerRoads.size(); i++) {
            for (Road playerRoad : playerRoads) {
                if (playerRoads.get(i).getX() == playerRoad.getX() && playerRoads.get(i).getY() == playerRoad.getY()) {
                } else {
                    double distance = math.distance(playerRoads.get(i).getX(), playerRoads.get(i).getY(), playerRoad.getX(), playerRoad.getY());
                    if (distance <= 63) {
                        roadsConnected.add(playerRoads.get(i));
                    }
                }
            }
        }
        return roadsConnected;
    }

    // Returns the village nodes that are next to the given roads
    private ArrayList<Circle> roadsNextToVillageSpot(ArrayList<Circle> array) {
        ArrayList<Circle> availableNodes = new ArrayList<>();
        for (Circle circle : array) {
            availableNodes.addAll(math.circlesInRadius(circle.getLayoutX(), circle.getLayoutY(), vertexNodeList, "other"));
        }
        availableNodes = removeDuplicates(availableNodes);
        return availableNodes;
    }

    // Returns the village nodes that are next to the given roads
    private ArrayList<Circle> roadsNextToVillageSpotRoad(ArrayList<Road> array) {
        ArrayList<Circle> availableNodes = new ArrayList<>();
        for (Road road : array) {
            availableNodes.addAll(math.circlesInRadius(road.getX(), road.getY(), vertexNodeList, "other"));
        }
        availableNodes = removeDuplicates(availableNodes);
        return availableNodes;
    }

    // Builds the village
    public void buildVillage(Circle node) {
        Village village = new Village(node.getLayoutX(), node.getLayoutY(), "blue");
        buildVillages.add(village);
//        Image img = new Image(String.valueOf(App.class.getResource(village.getImgPath())));
//        ImageView imageView = new ImageView(img);
//        imageView.setLayoutX(village.getX());
//        imageView.setLayoutY(village.getY());
//        objectsPane.getChildren().add(imageView);
    }

    public void buildUpgrade(Circle node) {
        for (int i=0; i < buildVillages.size(); i++) {
            if (node.getLayoutX() == buildVillages.get(i).getX() && node.getLayoutY() == buildVillages.get(i).getY()) {
                buildVillages.get(i).setUpgraded(true);
            }
        }

    }

    public ArrayList<Circle> showUpgradeableVillages() {
        ArrayList<Village> villages = playerVillages();
        ArrayList<Circle> upgradeableVillages = new ArrayList<>();
        if (villages.size() == 0){
            return null;
        } else {
            for (Circle circle : upgradeNodeList) {
                for (Village playerVillage : villages) {
                    if (circle.getLayoutX() == playerVillage.getX() && circle.getLayoutY() == playerVillage.getY()) {
                        upgradeableVillages.add(circle);
                    }
                }
            }
            return upgradeableVillages;
        }
    }

    // Prints coordinates ArrayList
    private void print(ArrayList<Circle> c) {
        System.out.println("Print initialized");
        for (int i=0; i < c.size(); i++) {
            System.out.println("This is X: " + c.get(i).getLayoutX());
            System.out.println("This is Y: " + c.get(i).getLayoutY());
        }
    }

}
