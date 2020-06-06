package org.catan.Controller;

import javafx.scene.shape.Circle;
import org.catan.Helper.BuildVillages;
import org.catan.Helper.MathBuildSettlement;
import org.catan.Helper.PolygonConnectedNodes;
import org.catan.Model.Road;
import org.catan.Model.Village;
import java.util.*;

public class BuildSettlementController {
    // todo add Player properties in all the methods
//    private Speler player;
    private String color = "blue";
    private ArrayList<Circle> vertexNodeList = new ArrayList<>();
    private ArrayList<Circle> upgradeNodeList = new ArrayList<>();
    private ArrayList<Circle> roadSpotNodeList = new ArrayList<>();

    private ArrayList<Road> buildRoads = new ArrayList<>();
    private ArrayList<Village> buildVillages = new ArrayList<>();
    private MathBuildSettlement math;
    private PolygonConnectedNodes poly;
    private BuildVillages bv;

    public BuildSettlementController(ArrayList<Circle> vertexNodeList, ArrayList<Circle> roadSpotNodeList,
                                     ArrayList<Circle> upgradeNodeList) {
        this.vertexNodeList = vertexNodeList;
        this.upgradeNodeList = upgradeNodeList;
        this.roadSpotNodeList = roadSpotNodeList;
        this.math = new MathBuildSettlement();
        this.poly = new PolygonConnectedNodes(vertexNodeList);
        this.bv = new BuildVillages();

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
    private ArrayList<Village> playerVillages() {
        ArrayList<Village> playerVillages = new ArrayList<>();
        for (Village buildVillage : buildVillages) {
            if (buildVillage.getColor().equals(this.color)) {
                playerVillages.add(buildVillage);
            }
        }
        return playerVillages;
    }

    public ArrayList<Circle> showVillageStartSpots() {
        ArrayList<Circle> nodes = new ArrayList<>();
        for (Circle circle : vertexNodeList) {
            for (Village buildVillage : buildVillages) {
                if (circle.getLayoutX() != buildVillage.getX() && circle.getLayoutY() != buildVillage.getY())
                    nodes.add(circle);
            }
        }
        return villagesNotClose(nodes);
    }

    public ArrayList<Circle> showRoadStartSpots(Circle village) {
        ArrayList<Circle> roads = math.circlesInRadius(village.getLayoutX(), village.getLayoutY(), roadSpotNodeList, "other");
        ArrayList<Circle> availableRoads = new ArrayList<>();
        for (Circle circle : roads) {
            for (Road buildRoad : buildRoads) {
                if (circle.getLayoutX() != buildRoad.getX() && circle.getLayoutY() != buildRoad.getY())
                    availableRoads.add(circle);
            }
        }
        return availableRoads;
    }

    // Gives available node for placing a village
    public ArrayList<Circle> showVillageSpots() {
        ArrayList<Road> roadsConnected = roadsConnected(); // Gives roads that have a minimum length of 2
        ArrayList<Circle> nodes = new ArrayList<>();
        for (int i=0; i < roadsConnected.size(); i++) {
            nodes.addAll(math.circlesInRadius(roadsConnected.get(i).getX(), roadsConnected.get(i).getY(), roadSpotNodeList, "road"));
        }
        nodes = removeDuplicates(nodes);
        ArrayList<Circle> nodesNodes = roadsNextToVillageSpot(nodes, 0);
        ArrayList<Circle> roadsConnectedNodes = roadsNextToVillageSpot(roadsConnected);
        return villagesNotClose(isSpotAvailable(removeNonDuplicates(nodesNodes, roadsConnectedNodes), buildVillages));
    }

    private ArrayList<Circle> villagesNotClose(ArrayList<Circle> spots) {
        ArrayList<Circle> placeAbleSpots = new ArrayList<>();
         for (int i=0; i < spots.size(); i++) {
             ArrayList<Circle> nodes = (math.circlesInRadius(spots.get(i).getLayoutX(), spots.get(i).getLayoutY(), vertexNodeList, "village"));
             int foundVillages = 0;
             for (int j=0; j < nodes.size(); j++) {
                 for (int k=0; k < buildVillages.size(); k++) {
                     if (nodes.get(j).getLayoutX() == buildVillages.get(k).getX() && nodes.get(j).getLayoutY() == buildVillages.get(k).getY()) {
                         foundVillages++;
                     }
                 }
             }
             if (foundVillages == 0) {
                 placeAbleSpots.add(spots.get(i));
             }
         }
         return placeAbleSpots;
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

    private ArrayList<Road> removeDuplicates(ArrayList<Road> array, int useless) {
        ArrayList<Road> arrayFixed = new ArrayList<>();
        for (Road road : array) {
            if (!arrayFixed.contains(road)) {
                arrayFixed.add(road);
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

    // Checks if the spots don't have a road already
    private ArrayList<Circle> isSpotAvailable(ArrayList<Circle> nodes, ArrayList<Road> roads, int useless) {
        for (int i=0; i < nodes.size(); i++) {
            for (Road r : roads) {
                if (nodes.get(i).getLayoutX() == r.getX() && nodes.get(i).getLayoutY() == r.getY()) {
                    nodes.remove(i);
                }
            }
        }
        return nodes;
    }

    // Checks if the available spots don't have a settlement already
    private ArrayList<Circle> isSpotAvailable(ArrayList<Circle> nodes, ArrayList<Village> village) {
        ArrayList<Circle> nodesToRemove = new ArrayList<>();
        for (int i=0; i < nodes.size(); i++) {
            for (Village v : village) {
                if (nodes.get(i).getLayoutX() == v.getX() && nodes.get(i).getLayoutY() == v.getY()) {
                    nodesToRemove.add(nodes.get(i));
                }
            }
        }
        nodes.removeAll(nodesToRemove);

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
        return removeDuplicates(roadsConnected, 0);
    }

    // Returns the village nodes that are next to the given roads
    private ArrayList<Circle> roadsNextToVillageSpot(ArrayList<Circle> array, int useless) {
        ArrayList<Circle> availableNodes = new ArrayList<>();
        for (Circle circle : array) {
            availableNodes.addAll(math.circlesInRadius(circle.getLayoutX(), circle.getLayoutY(), vertexNodeList, "other"));
        }
        availableNodes = removeDuplicates(availableNodes);
        return isSpotAvailable(availableNodes, buildVillages);
    }

    // Returns the village nodes that are next to the given roads
    private ArrayList<Circle> roadsNextToVillageSpot(ArrayList<Road> array) {
        ArrayList<Circle> availableNodes = new ArrayList<>();
        for (Road road : array) {
            availableNodes.addAll(math.circlesInRadius(road.getX(), road.getY(), vertexNodeList, "other"));
        }
        availableNodes = removeDuplicates(availableNodes);
        return isSpotAvailable(availableNodes, buildVillages);
    }

    // Makes villages and returns to GameSchermController for img placement.
    public Village buildVillage(Circle node) {
        Village village = new Village(node.getLayoutX(), node.getLayoutY(), "blue", poly.getConnectedTiles(node.getLayoutX(), node.getLayoutY()));
        buildVillages.add(village);
        bv.setBuildVillages(buildVillages);
        return village;
    }

    public Village buildUpgrade(Circle node) {
        Village village = null;
        for (Village buildVillage : buildVillages) {
            if (node.getLayoutX() == buildVillage.getX() && node.getLayoutY() == buildVillage.getY()) {
                buildVillage.setUpgraded(true);
                village = buildVillage;
                break;
            }
        }
        return village;
    }

    public ArrayList<Circle> showUpgradeableVillages() {
        ArrayList<Village> villages = playerVillages();
        ArrayList<Circle> upgradeableVillages = new ArrayList<>();
        if (villages.size() == 0){
            return null;
        } else {
            for (Circle circle : upgradeNodeList) {
                for (Village playerVillage : villages) {
                    if (circle.getLayoutX() == playerVillage.getX() && circle.getLayoutY() == playerVillage.getY() && !playerVillage.isUpgraded()) {
                        upgradeableVillages.add(circle);
                    }
                }
            }
            return upgradeableVillages;
        }
    }

    public ArrayList<Circle> showRoadSpots() {
        ArrayList<Road> playerRoads = playerRoads();
        ArrayList<Circle> roadPlaces = new ArrayList<>();

        for (Road playerRoad : playerRoads) {
            roadPlaces.addAll(math.circlesInRadius(playerRoad.getX(), playerRoad.getY(), roadSpotNodeList, "road"));
        }

        for (Village village : buildVillages) {
            if (village.getColor().equals(color))
                roadPlaces.addAll(math.circlesInRadius(village.getX(), village.getY(), roadSpotNodeList, "other"));
        }

        roadPlaces = isSpotAvailable(removeDuplicates(roadPlaces), buildRoads, 1);
        return isSpotAvailable(removeDuplicates(roadPlaces), buildRoads, 1);
    }

    public Road buildRoad(Circle node) {
        Road road = new Road(node.getLayoutX(), node.getLayoutY(), "blue");
        buildRoads.add(road);
        return road;
    }

}
