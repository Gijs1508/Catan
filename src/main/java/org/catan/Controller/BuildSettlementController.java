package org.catan.Controller;

import javafx.scene.control.Alert;
import javafx.scene.shape.Circle;
import org.catan.Helper.BuildVillages;
import org.catan.Helper.MathBuildSettlement;
import org.catan.Helper.PolygonConnectedNodes;
import org.catan.Model.*;
import org.catan.interfaces.Observable;

import java.lang.reflect.Array;
import java.util.*;

/* This controller calculates the nodes for settlements / road placement and returns it to GameSchermController
 */
public class BuildSettlementController implements Observable {


    // todo add Player properties in all the methods
//    private Speler player;
    private String color = "blue";
    private ArrayList<Circle> vertexNodeList;
    private ArrayList<Circle> upgradeNodeList;
    private ArrayList<Circle> roadSpotNodeList;

    private ArrayList<Road> buildRoads;
    private ArrayList<Village> buildVillages;
    private MathBuildSettlement math;
    private PolygonConnectedNodes poly;
    private BuildVillages bv;

    private GameSchermController gameSchermController = GameSchermController.getInstance();

    private static BuildSettlementController buildSettlementController;


    public BuildSettlementController(ArrayList<Circle> vertexNodeList, ArrayList<Circle> roadSpotNodeList,
                                     ArrayList<Circle> upgradeNodeList) {
        buildSettlementController = this;

        this.vertexNodeList = vertexNodeList;
        this.upgradeNodeList = upgradeNodeList;
        this.roadSpotNodeList = roadSpotNodeList;
        this.math = new MathBuildSettlement();
        this.poly = new PolygonConnectedNodes(vertexNodeList);
        this.bv = new BuildVillages();
        this.buildRoads = new ArrayList<>();
        this.buildVillages = new ArrayList<>();
    }

    /** Gives the harbor that a settlement has been placed adjacent to to the player for updates.
     * @param harbor the harbor object
     * @author Jeroen */
    public void updatePlayerFromHarbor(Harbor harbor) {
        Player.getActivePlayer().updateResourceCosts(harbor);
        // todo It updates when you click it outside of your turn (probably because of the placeholder button, placeRoadBtn)
        // todo Make method private when placeholder is replaced
    }

    /** Checks if settlement was placed adjacent to a harbor */
    private boolean builtAtHarbor() {
        // todo Going to work on this when the build settlement functionality has been pushed -Jeroen
        return false;
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

    /**
    * The methods returns the right placement nodes in the start phase
    * This is for village placement
    * @return an arrayList with nodes
     */
    public ArrayList<Circle> showVillageStartSpots() {
        ArrayList<Circle> nodes = new ArrayList<>();
        for (Circle circle : vertexNodeList) {
            if (buildVillages.isEmpty()) {
                nodes.add(circle);
            } else {
                for (Village buildVillage : buildVillages) {
                    if ((circle.getLayoutX() != buildVillage.getX()) || (circle.getLayoutY() != buildVillage.getY())) {
                        nodes.add(circle);
                    }
                }
            }
            }
        return villagesNotClose(nodes);
    }

    /**
     * The methods returns the right placement nodes in the start phase
     * This is for road placement
     * @param village Give the village the player just build
     * @return an arrayList with nodes
     */
    public ArrayList<Circle> showRoadStartSpots(Circle village) {
        ArrayList<Circle> roads = new ArrayList<>(math.circlesInRadius(village.getLayoutX(), village.getLayoutY(), roadSpotNodeList, "other"));
        ArrayList<Circle> availableRoads = new ArrayList<>();
        for (Circle circle : roads) {
            if(buildRoads.isEmpty()) {
                availableRoads.add(circle);
            } else {
                for (Road buildRoad : buildRoads) {
                    if (circle.getLayoutX() != buildRoad.getX() || circle.getLayoutY() != buildRoad.getY())
                        availableRoads.add(circle);
                }
            }
        }
        return availableRoads;
    }

    /**
     * The methods returns the right placement nodes outside the start phase
     * This is for village placement
     * @return an arrayList with nodes
     */
    public ArrayList<Circle> showVillageSpots() {
        ArrayList<Road> roadsConnected = new ArrayList<>(roadsConnected()); // Gives roads that have a minimum length of 2
        ArrayList<Circle> nodes = new ArrayList<>();
        for (Road road : roadsConnected) {
            nodes.addAll(math.circlesInRadius(road.getX(), road.getY(), roadSpotNodeList, "road"));
        }
        nodes = removeDuplicates(nodes);
        ArrayList<Circle> nodesNodes = roadsNextToVillageSpot(nodes, 0);
        ArrayList<Circle> roadsConnectedNodes = roadsNextToVillageSpot(roadsConnected);
        return villagesNotClose(isSpotAvailable(removeNonDuplicates(nodesNodes, roadsConnectedNodes), buildVillages));
    }

    // Returns nodes that have no villages close to them
    private ArrayList<Circle> villagesNotClose(ArrayList<Circle> spots) {
        ArrayList<Circle> placeAbleSpots = new ArrayList<>();
        for (Circle spot : spots) {
            ArrayList<Circle> nodes = (math.circlesInRadius(spot.getLayoutX(), spot.getLayoutY(), vertexNodeList, "village"));
            int foundVillages = 0;
            for (Circle node : nodes) {
                for (Village buildVillage : buildVillages) {
                    if (node.getLayoutX() == buildVillage.getX() && node.getLayoutY() == buildVillage.getY()) {
                        foundVillages++;
                    }
                }
            }
            if (foundVillages == 0) {
                placeAbleSpots.add(spot);
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

    // Removes duplicates in array
    private ArrayList<Road> removeDuplicates(ArrayList<Road> array, int useless) {
        ArrayList<Road> arrayFixed = new ArrayList<>();
        for (Road road : array) {
            if (!arrayFixed.contains(road)) {
                arrayFixed.add(road);
            }
        }
        return arrayFixed;
    }

    // Removes duplicates in array
    private ArrayList<Village> removeDuplicates(ArrayList<Village> array, double useless) {
        ArrayList<Village> arrayFixed = new ArrayList<>();
        for (Village village : array) {
            if (!arrayFixed.contains(village)) {
                arrayFixed.add(village);
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
        ArrayList<Circle> availableSpots = new ArrayList<>(nodes);
        for (Circle node : nodes) {
            for (Road r : roads) {
                if (node.getLayoutX() == r.getX() && node.getLayoutY() == r.getY()) {
                    availableSpots.remove(node);
                }
            }
        }
        return availableSpots;
    }

    // Checks if the available spots don't have a settlement already
    private ArrayList<Circle> isSpotAvailable(ArrayList<Circle> nodes, ArrayList<Village> villages) {
        ArrayList<Circle> nodesToRemove = new ArrayList<>();
        for (Circle node : nodes) {
            for (Village village : villages) {
                if (node.getLayoutX() == village.getX() && node.getLayoutY() == village.getY()) {
                    nodesToRemove.add(node);
                }
            }
        }
        nodes.removeAll(nodesToRemove);
        return nodes;
    }

    // Returns roads that lie next to another road
    private ArrayList<Road> roadsConnected() {
        ArrayList<Road> playerRoads = new ArrayList<>(playerRoads());
        ArrayList<Road> roadsConnected = new ArrayList<>();
        for (Road road : playerRoads) {
            for (Road playerRoad : playerRoads) {
                if (road.getX() != playerRoad.getX() || road.getY() != playerRoad.getY()) {
                    double distance = math.distance(road.getX(), road.getY(), playerRoad.getX(), playerRoad.getY());
                    if (distance <= 63) {
                        roadsConnected.add(road);
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

    /**
     * The methods makes a Village and returns it
     * Villages gets used in GameSchermController for image placement
     * @param node the player clicked
     */
    public Village buildVillage(Circle node) {
        Village village = new Village(node.getLayoutX(), node.getLayoutY(), "blue", poly.getConnectedTiles(node.getLayoutX(), node.getLayoutY()));
        buildVillages.add(village);
        bv.setBuildVillages(buildVillages);
        return village;
    }

    /**
     * The methods upgrades a Village and returns it
     * Upgraded villages gets used in GameSchermController for image placement
     * @param node the player clicked
     */
    public Village buildUpgrade(Circle node) {
        Village village = null;
        int[] reqResources = {0, 0, 3, 0, 2, 0};
        if(gameSchermController.canBuildObject(reqResources)){
            for (Village buildVillage : buildVillages) {
                if (node.getLayoutX() == buildVillage.getX() && node.getLayoutY() == buildVillage.getY()) {
                    buildVillage.setUpgraded(true);
                    village = buildVillage;
                    break;
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You don't have enough resources to upgrade an village!");
            alert.show();
        }
        return village;
    }

    /**
     * Checks the villages that can be upgraded
     * @return an arrayList with nodes of villages
     */
    public ArrayList<Circle> showUpgradeableVillages() {
        ArrayList<Village> villages = new ArrayList<>(playerVillages());
        ArrayList<Circle> upgradeableVillages = new ArrayList<>();
        if (villages.size() == 0){
            return null;
        } else {
            for (Circle circle : upgradeNodeList) {
                for (Village playerVillage : villages) {
                    if (circle.getLayoutX() == playerVillage.getX() && circle.getLayoutY() == playerVillage.getY() && !playerVillage.isUpgraded()) {
                        upgradeableVillages.add(circle);
                        break;
                    }
                }
            }
            return upgradeableVillages;
        }
    }

    /**
     * Checks the roads that can be build
     * @return an arrayList with nodes of roads
     */
    public ArrayList<Circle> showRoadSpots() {
        ArrayList<Road> playerRoads = new ArrayList<>(playerRoads());
        ArrayList<Circle> roadPlaces = new ArrayList<>();

        for (Road playerRoad : playerRoads) {
            roadPlaces.addAll(math.circlesInRadius(playerRoad.getX(), playerRoad.getY(), roadSpotNodeList, "road"));
        }

        for (Village village : buildVillages) {
            if (village.getColor().equals(color))
                roadPlaces.addAll(math.circlesInRadius(village.getX(), village.getY(), roadSpotNodeList, "other"));
        }

        return isSpotAvailable(removeDuplicates(roadPlaces), buildRoads, 1);
    }

    /**
     * The returns the villages that can be upgraded
     * @return an arrayList with nodes of villages
     */
    public Road buildRoad(Circle node) {
        Road road = new Road(node.getLayoutX(), node.getLayoutY(), "blue");
        buildRoads.add(road);
        return road;
    }

//    public boolean buildItem(int[] requiredResources, Object object){
//        Inventory playerInventory = Player.getMainPlayer().getPlayerInventory();
//        for (int i = 0; i < playerInventory.getCards().length; i++) {
//            for (int reqCard : requiredResources){
//                if(playerInventory.getCards()[i] >= reqCard){
//                    System.out.println(playerInventory.getStrCards()[i]);
//                    playerInventory.changeCards(playerInventory.getStrCards()[i], -reqCard);
//                    //buildVillages.add(object);
//                    System.out.println(object.getClass().getName());
//
//                    switch (object.getClass().getName()){
//                        case "org.catan.Model.Village": buildVillages.add((Village) object); break;
//                        case "org.catan.Model.Road": buildRoads.add((Road) object); break;
//                    }
//
//                    bv.setBuildVillages(buildVillages);
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    private void print(ArrayList<Village> village) {
        for (Village v : village) {
            System.out.println("This is a village " + v);
        }
    }

    @Override
    public void update(Game game) {
        updateRoads(game.getBoard().getRoads());
        updateSettlements(game.getBoard().getSettlements());
    }

    private void updateRoads(ArrayList<Road> roads) {
        if (!roads.equals(buildRoads)) {
            roads.addAll(buildRoads);
            ArrayList<Road> changedRoads = new ArrayList<>(removeDuplicates(roads, 0));
            GameSchermController.getInstance().updateRoads(changedRoads);
            buildRoads.addAll(changedRoads);
        }
    }

    private void updateSettlements(ArrayList<Village> villages) {
        if (!villages.equals(buildVillages)) {
            villages.addAll(buildVillages);
            ArrayList<Village> changedVillages = new ArrayList<>(removeDuplicates(villages, 0));
            ArrayList<Village> villages2 = new ArrayList<>(changedVillages);
            ArrayList<Village> cities = new ArrayList<>();
            for (Village village : changedVillages) {
                if (village.isUpgraded()) {
                    cities.add(village);
                    villages2.remove(village);
                }
            }
            if (!villages2.isEmpty())
                GameSchermController.getInstance().updateVillage(villages2);
            if (!cities.isEmpty())
                GameSchermController.getInstance().updateCity(cities);
        }
    }

    public static BuildSettlementController getInstance() {
        return buildSettlementController;
    }
}
