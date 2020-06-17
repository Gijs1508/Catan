package org.catan.Model;

import org.catan.App;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that contains all Log objects.
 * Also contains information required for creating logs.
 *
 * @author Jeroen
 */

public class Logs {

    private ArrayList<Log> logs = new ArrayList<>();
//    private HashMap<String, String> imgPath = new HashMap<>();
    private HashMap<String, Image> images = new HashMap<>();
    private HashMap<String, String> textEvents = new HashMap<>();
    private HashMap<String, String> imgEvents = new HashMap<>();

    public Logs(){
        initialize();
    }

    /** Creates the information required for creating logs. */
    public void initialize(){
//        imgPath = new HashMap<>() {{
//            put("1", String.valueOf(App.class.getResource("assets/img/die/die1.png")));
//            put("2", String.valueOf(App.class.getResource("assets/img/die/die2.png")));
//            put("3", String.valueOf(App.class.getResource("assets/img/die/die3.png")));
//            put("4", String.valueOf(App.class.getResource("assets/img/die/die4.png")));
//            put("5", String.valueOf(App.class.getResource("assets/img/die/die5.png")));
//            put("6", String.valueOf(App.class.getResource("assets/img/die/die6.png")));
//
//            put("brick", String.valueOf(App.class.getResource("assets/img/brickSmall.png")));
//            put("ore", String.valueOf(App.class.getResource("assets/img/oreSmall.png")));
//            put("wool", String.valueOf(App.class.getResource("assets/img/sheepSmall.png")));
//            put("wheat", String.valueOf(App.class.getResource("assets/img/wheatSmall.png")));
//            put("wood", String.valueOf(App.class.getResource("assets/img/woodSmall.png")));
//        }};

        images = new HashMap<>() {{
            put("1", new Image(String.valueOf(App.class.getResource("assets/img/die/die1.png"))));
            put("2", new Image(String.valueOf(App.class.getResource("assets/img/die/die2.png"))));
            put("3", new Image(String.valueOf(App.class.getResource("assets/img/die/die3.png"))));
            put("4", new Image(String.valueOf(App.class.getResource("assets/img/die/die4.png"))));
            put("5", new Image(String.valueOf(App.class.getResource("assets/img/die/die5.png"))));
            put("6", new Image(String.valueOf(App.class.getResource("assets/img/die/die6.png"))));

            put("brick", new Image(String.valueOf(App.class.getResource("assets/img/brickSmall.png"))));
            put("ore", new Image(String.valueOf(App.class.getResource("assets/img/oreSmall.png"))));
            put("wool", new Image(String.valueOf(App.class.getResource("assets/img/sheepSmall.png"))));
            put("wheat", new Image(String.valueOf(App.class.getResource("assets/img/wheatSmall.png"))));
            put("wood", new Image(String.valueOf(App.class.getResource("assets/img/woodSmall.png"))));
        }};

        textEvents = new HashMap<>() {{
            put("steal", "%PLAYER% steals a card from %PLAYER2%.");
            put("startturn", "%PLAYER% starts turn.");
            put("endturn", "%PLAYER% ends turn.");
            put("upgrade", "%PLAYER% upgrades a settlement.");
            put("road", "%PLAYER% builds a road.");
            put("trade", "%PLAYER% traded with %PLAYER2%.");
            put("settlement", "%PLAYER% builds a settlement.");
            put("robber", "%PLAYER% moves the robber.");
            put("win", "%PLAYER% wins the game.");
            put("point", "%PLAYER% gains a victory point.");
            put("knight", "%PLAYER% activates a knight card.");
            put("development", "%PLAYER% buys a development card.");
        }};

        imgEvents = new HashMap<>() {{
            put("roll", "%PLAYER% rolled:");
            put("receive", "%PLAYER% receives:");
        }};

    }

    public void addLog(Log log) {
        logs.add(log);
    }

    public void setLogs(ArrayList<Log> logs) {
        this.logs = logs;
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

//    public HashMap<String, String> getImgPath() {
//        return imgPath;
//    }

    public HashMap<String, Image> getImages() {
        return images;
    }

    public HashMap<String, String> getTextEvents() {
        return textEvents;
    }

    public HashMap<String, String> getImgEvents() {
        return imgEvents;
    }

}
