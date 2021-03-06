package org.catan.Controller;

import javafx.scene.shape.Circle;
import org.catan.App;
import org.catan.Helper.MathBuildSettlement;
import org.catan.Helper.PolygonConnectedNodes;
import org.catan.Model.*;
import org.catan.interfaces.Observable;
import org.catan.logic.DatabaseConnector;

import java.util.*;

/**
 * This controller calculates the nodes for placement and places these on the board with the GameSchermController
 */
public class BuildSettlementController implements Observable {

    private ArrayList<Circle> vertexNodeList;
    private ArrayList<Circle> upgradeNodeList;
    private ArrayList<Circle> roadSpotNodeList;

    private ArrayList<Road> buildRoads;
    private ArrayList<Village> buildVillages;
    private MathBuildSettlement math;
    private PolygonConnectedNodes poly;

    private BoardController boardController = BoardController.getInstance();
    private static BuildSettlementController buildSettlementController;

    public BuildSettlementController(ArrayList<Circle> vertexNodeList, ArrayList<Circle> roadSpotNodeList,
                                     ArrayList<Circle> upgradeNodeList) {
        buildSettlementController = this;

        this.vertexNodeList = vertexNodeList;
        this.upgradeNodeList = upgradeNodeList;
        this.roadSpotNodeList = roadSpotNodeList;
        this.math = new MathBuildSettlement();
        this.poly = new PolygonConnectedNodes(vertexNodeList);
        this.buildRoads = new ArrayList<>();
        this.buildVillages = new ArrayList<>();
    }

    // Returns roads from player
    private ArrayList<Road> playerRoads() {
        ArrayList<Road> playerRoads = new ArrayList<>();
        for (Road buildRoad : buildRoads) {
            if (buildRoad.getColor().equals(getPlayerColor())) {
                playerRoads.add(buildRoad);
            }
        }
        return playerRoads;
    }

    // Returns villages from player
    private ArrayList<Village> playerVillages() {
        ArrayList<Village> playerVillages = new ArrayList<>();
        for (Village buildVillage : buildVillages) {
            if (buildVillage.getColor().equals(getPlayerColor())) {
                playerVillages.add(buildVillage);
            }
        }
        return playerVillages;
    }

    /**
    * The methods returns the right placement nodes in the start phase
    * This is for village placement
    * @return an arrayList with nodes
     * @author Jan
     */
    public ArrayList<Circle> showVillageStartSpots() {
        ArrayList<Circle> nodes = new ArrayList<>();
        for (Circle circle : vertexNodeList) {
            int count = 0;
            for (Village buildVillage : buildVillages) {
                if ((circle.getLayoutX() == buildVillage.getX()) && (circle.getLayoutY() == buildVillage.getY())) {
                    count++;
                }
            }
            if (count == 0)
                nodes.add(circle);
            if (buildVillages.isEmpty())
                nodes.add(circle);
            }
        return villagesNotClose(nodes);
    }

    /**
     * The methods returns the right placement nodes in the start phase
     * This is for road placement
     * @param village Give the village the player just build
     * @return an arrayList with nodes
     * @author Jan
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
     * @author Jan
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

    // Removes everything that has a duplicate and returns the unique
    private ArrayList<Road> removeDuplicatesCompletely(ArrayList<Road> array, ArrayList<Road> array2) {
        ArrayList<Road> arrayFixed = new ArrayList<>();
        for (Road road : array) {
            if (!array2.contains(road)) {
                arrayFixed.add(road);
            }
        }
        return arrayFixed;
    }

    // Removes everything that has a duplicate and returns the unique
    private ArrayList<Village> removeDuplicatesCompletely(ArrayList<Village> array, ArrayList<Village> array2, int useless) {
        ArrayList<Village> arrayFixed = new ArrayList<>();
        for (Village village : array) {
            if (!array2.contains(village)) {
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
     * @author Jan
     */
    public Village buildVillage(Circle node) {
        // If the node borders a harbor
        if(BoardController.getInstance().getAllHarborVertices().contains(node)) {
            builtAtHarbor(node);
        }

        Village village = new Village(node.getLayoutX(), node.getLayoutY(), getPlayerColor(), poly.getConnectedTiles(node.getLayoutX(), node.getLayoutY()));
        buildVillages.add(village);
        App.getCurrentGame().getBoard().setSettlements(buildVillages);
        App.getCurrentGame().turnPlayerGetter().addVillagePoint();
        App.getCurrentGame().turnPlayerGetter().addVictoryPoint();
        DatabaseConnector.getInstance().updateGame(App.getCurrentGame());
        return village;
    }

    // Finds the harbor that the settlement has been placed adjacent to and updates accordingly.
    private void builtAtHarbor(Circle node) {
        int harborNum = 0;

        // Finds what harborNum belongs to the vertex a settlement was placed on
        for (Map.Entry<Integer, ArrayList<Circle>> entry : BoardController.getInstance().getHarborNumToVertices().entrySet()) {
            if(entry.getValue().contains(node)) {
                harborNum = entry.getKey();
            }
        }
        // Finds what harbor belongs to that harborNum and updates the player's costs accordingly
        for (Harbor harbor : BoardController.getInstance().getHarbors()) {
            if(harbor.getHarborNum() == harborNum) {
                App.getCurrentGame().turnPlayerGetter().updateResourceCosts(harbor);
            }
        }
    }

    /**
     * The methods upgrades a Village and returns it
     * Upgraded villages gets used in GameSchermController for image placement
     * @param node the player clicked
     * @author Jan
     */
    public Village buildUpgrade(Circle node) {
        Village village = null;
        int[] reqResources = {0, 0, 3, 0, 2, 0};
        if(boardController.canBuildObject(reqResources) && !App.getCurrentGame().getTradeStatus().equals("pending")){
            for (Village buildVillage : buildVillages) {
                if (node.getLayoutX() == buildVillage.getX() && node.getLayoutY() == buildVillage.getY()) {
                    buildVillage.setUpgraded(true);
                    village = buildVillage;
                    break;
                }
            }
            App.getCurrentGame().turnPlayerGetter().addCityPoint();
            App.getCurrentGame().turnPlayerGetter().addVictoryPoint();
            App.getCurrentGame().turnPlayerGetter().removeVillagePoint();
            App.getCurrentGame().getBoard().setSettlements(buildVillages);
            DatabaseConnector.getInstance().updateGame(App.getCurrentGame());

        } else if(App.getCurrentGame().getTradeStatus().equals("pending")){
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription("You can't upgrade your settlement while your trade offer is pending.");
        }
        else {
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription("You don't have enough resources to upgrade a village.");
        }
        return village;
    }

    /**
     * Checks the villages that can be upgraded
     * @return an arrayList with nodes of villages
     * @author Jan
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
     * @author Jan
     */
    public ArrayList<Circle> showRoadSpots() {
        ArrayList<Road> playerRoads = new ArrayList<>(playerRoads());
        ArrayList<Circle> roadPlaces = new ArrayList<>();

        for (Road playerRoad : playerRoads) {
            roadPlaces.addAll(math.circlesInRadius(playerRoad.getX(), playerRoad.getY(), roadSpotNodeList, "road"));
        }

        for (Village village : buildVillages) {
            if (village.getColor().equals(getPlayerColor()))
                roadPlaces.addAll(math.circlesInRadius(village.getX(), village.getY(), roadSpotNodeList, "other"));
        }

        return isSpotAvailable(removeDuplicates(roadPlaces), buildRoads, 1);
    }

    /**
     * The returns the villages that can be upgraded
     * @return an arrayList with nodes of villages
     * @author Jan
     */
    public Road buildRoad(Circle node) {
        Road road = new Road(node.getLayoutX(), node.getLayoutY(), getPlayerColor());
        buildRoads.add(road);
        App.getCurrentGame().turnPlayerGetter().addRoadPoint();
        App.getCurrentGame().getBoard().setRoads(buildRoads);
        DatabaseConnector.getInstance().updateGame(App.getCurrentGame());
        return road;
    }

    // Returns the color from the player whose turn it is
    private String getPlayerColor() {
        return App.getCurrentGame().turnPlayerGetter().getColor();
    }

    /**
     * This method updates all the villages when called
     * @author Jan
     */
    @Override
    public void update(Game game) {
        updateRoads(game.getBoard().getRoads());
        updateSettlements(game.getBoard().getSettlements());
    }

    // Updates the roads on the display and in the array
    private void updateRoads(ArrayList<Road> roads) {
        if (!roads.equals(buildRoads)) {
            ArrayList<Road> changedRoads = new ArrayList<>(removeDuplicatesCompletely(roads, buildRoads));
            BoardController.getInstance().updateRoads(changedRoads);
            buildRoads.addAll(changedRoads);
            App.getCurrentGame().getBoard().setRoads(buildRoads);
        }
    }

    // Updates the settlements on the display and in the array
    private void updateSettlements(ArrayList<Village> villages) {
        if (!villages.equals(buildVillages) && villages.size() >= buildVillages.size()) {
            ArrayList<Village> changedVillages = new ArrayList<>(removeDuplicatesCompletely(villages, buildVillages, 0));
            ArrayList<Village> villages2 = new ArrayList<>(changedVillages);
            ArrayList<Village> cities = new ArrayList<>();
            buildVillages.addAll(changedVillages);

            for (Village village : changedVillages) {
                if (village.isUpgraded()) {
                    cities.add(village);
                    cities.add(village);
                    villages2.remove(village);
                }
            }

            if (!villages2.isEmpty())
                BoardController.getInstance().updateVillage(villages2);
            if (!cities.isEmpty())
                BoardController.getInstance().updateCity(cities);

            App.getCurrentGame().getBoard().setSettlements(buildVillages);
        }
    }

    // returns the instance of this class
    public static BuildSettlementController getInstance() {
        return buildSettlementController;
    }
}
