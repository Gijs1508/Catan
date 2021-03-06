package org.catan.Model;

import org.catan.App;
import org.catan.Controller.*;
import org.catan.logic.DatabaseConnector;

import java.util.*;

/**
 * The player that's controlled by a person in the match.
 *
 * @author Sabrina, Werner, Kaz, Jeroen
 */

public class Player {

    private String name;
    private String color;
    private int score;
    private int roadScore;
    private int villageScore;
    private int cityScore;
    private int identifier;
    private boolean turn = false;
    private boolean host = false;

    private Inventory playerInventory;

    private HashMap<String, Integer> resourceToCost;

    private TradeController tradeController = TradeController.getInstance();
    private ScoreController scoreController = ScoreController.getInstance();

    /** Empty constructor, needed for Jackson to do proper deserialization */
    public Player() { }

    public Player(String name){
        this.name = name;
        this.color = "red";
        this.score = 0;
        this.playerInventory = new Inventory();
        this.identifier = Math.toIntExact(CreateGameCode.randomCodeGen());
        playerInventory.setOwnerID(identifier);
        initializeResourceCosts();
    }

    /*** Initializes the resource costs of bank trade (everything starts with a cost of 1:4)
     * @author Jeroen */
    private void initializeResourceCosts() {
        resourceToCost = new HashMap<>(){{
            put("wheat", 4); put("wood", 4);
            put("brick", 4); put("wool", 4);
            put("ore", 4);
        }};
    }

    /*** Updates resource costs for the player object and the player's trade view.
     * Takes harbor's type and ratio (costs) as a parameter since it's the only relevant information.
     * If the type is any, all values in resourceToCost get updated (unless the ratio has already been updated).
     * @param harbor harbor object that settlement has been placed adjacent to
     * @author Jeroen */
    public void updateResourceCosts(Harbor harbor) {
        if(harbor.getType().equals("any")) {
            Iterator it = resourceToCost.entrySet().iterator();
            while (it.hasNext()) { // Iterate through the resourceToCost HashMap
                Map.Entry pair = (Map.Entry) it.next();
                if((Integer) pair.getValue() > harbor.getRatio()) { // Only update if the old value is higher than the new value
                    resourceToCost.replace((String) pair.getKey(), harbor.getRatio());
                    tradeController.updateRatioView((String) pair.getKey(), harbor.getRatio());
                }
            }
        }
        else {
            resourceToCost.replace(harbor.getType(), harbor.getRatio());
            tradeController.updateRatioView(harbor.getType(), harbor.getRatio());
        }
    }

    /** Makes the player steal a random card from another player.
     * @param victim the opponent to steal from
     * @author Jeroen */
    public void stealFromVictim(Player victim) {
        HashMap<String, Integer> resourcesToAmount = victim.getPlayerInventory().resourceToAmountGetter(); // Get the victim's resources in their inventory
        Iterator it = resourcesToAmount.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if((Integer) pair.getValue() <= 0){
                it.remove(); // Ignore resources that the victim doesn't have any of
            }
        }
        List<String> resources = new ArrayList<>(resourcesToAmount.keySet()); // Random resource from resources
        if(resources.isEmpty()) {
            ScreenController.getInstance().showAlertPopup();
            AlertPopUpController.getInstance().setAlertDescription(
                    "Stealing from " + victim.getName() + " failed.\nOpponent does not have any resources."
            );
            return;
        }
        String resource = resources.get(new Random().nextInt(resources.size()));

        // Take the resource from the victim, and give it to the active player
        victim.getPlayerInventory().changeCards(resource, -1);
        getPlayerInventory().changeCards(resource, 1);

        DatabaseConnector.getInstance().updateGame(App.getCurrentGame());
        LogController.getInstance().logStealEvent(victim); // Log steal event
    }

    public void addVictoryPoint() {
        score++;
    }

    public void addRoadPoint() {
        roadScore++;
    }

    public void addVillagePoint() {
        villageScore++;
    }

    public void removeVillagePoint() {
        villageScore--;
    }

    public void addCityPoint() {
        cityScore++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName(){
        return this.name;
    }

    public String getColor(){
        return this.color;
    }

    public int getScore() {
        return score;
    }

    public Inventory getPlayerInventory(){
        return this.playerInventory;
    }

    public void setPlayerInventory(Inventory inventory) {
        this.playerInventory = inventory;
    }

    public HashMap<String, Integer> getResourceToCost() {
        return resourceToCost;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRoadScore() {
        return roadScore;
    }

    public void setRoadScore(int roadScore) {
        this.roadScore = roadScore;
    }

    public int getVillageScore() {
        return villageScore;
    }

    public void setVillageScore(int villageScore) {
        this.villageScore = villageScore;
    }

    public int getCityScore() {
        return cityScore;
    }

    public void setCityScore(int cityScore) {
        this.cityScore = cityScore;
    }
}
