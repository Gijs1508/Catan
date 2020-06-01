package org.catan.Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that contains all Log objects.
 * This is the class that the LogController reads to update the log view.
 *
 * todo
 * make it contain all Log objects in a way that makes it useful for the controller
 * database
 *
 * @author Jeroen
 * @version 0.1
 */

public class Logs {

    private ArrayList<Log> logs = new ArrayList<>();
    private HashMap<String, String> imgPath = new HashMap<>();
    private HashMap<String, String> textEvents = new HashMap<>();
    private HashMap<String, String> imgEvents = new HashMap<>();

    public Logs(){
        initialize();
    }

    public void initialize(){
        imgPath = new HashMap<>() {{
            put("1", "assets/img/die/die1.png");
            put("2", "assets/img/die/die2.png");
            put("3", "assets/img/die/die3.png");
            put("4", "assets/img/die/die4.png");
            put("5", "assets/img/die/die5.png");
            put("6", "assets/img/die/die6.png");

            put("brick", "assets/img/brickSmall.png");
            put("ore", "assets/img/oreSmall.png");
            put("sheep", "assets/img/sheepSmall.png");
            put("wheat", "assets/img/wheatSmall.png");
            put("wood", "assets/img/woodSmall.png");
        }};

        textEvents = new HashMap<>() {{
            put("stole", "%PLAYER% steals a card from %PLAYER2%.");
            put("endturn", "%PLAYER% ends turn.");
            put("startturn", "It is now %PLAYER%'s turn");
            put("upgrade", "%PLAYER% upgrades a settlement.");
            put("road", "%PLAYER% places a road.");
            put("trade", "%PLAYER% traded with %PLAYER2.");
            put("settlement", "%PLAYER% places a settlement.");
            put("robber", "%PLAYER% moves the robber.");
            put("win", "%PLAYER% wins the game.");
            put("point", "%PLAYER% gains a victory point.");
            put("knight", "%PLAYER% activates a knight card.");
        }};

        imgEvents = new HashMap<>() {{
            put("roll", "%PLAYER% rolled:");
            put("receives", "%PLAYER% receives:");
        }};

    }

    public void addLog(Log log) {
        logs.add(log);
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

    public HashMap<String, String> getImgPath() {
        return imgPath;
    }

    public HashMap<String, String> getTextEvents() {
        return textEvents;
    }

    public HashMap<String, String> getImgEvents() {
        return imgEvents;
    }

}
