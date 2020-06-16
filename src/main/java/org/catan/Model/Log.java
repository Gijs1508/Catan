package org.catan.Model;

import javafx.scene.image.Image;
import org.catan.App;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Log class that contains information about a log entry.
 * It finds the appropriate event text that belongs to the given event that is to be logged.
 *
 * @author Jeroen
 */


public class Log {
    private Logs logs = new Logs();
    private String eventType;
    private String logType;
    private String eventString;
    private String playerName;
    private String opponentName;
    private ArrayList<String> imgPaths = new ArrayList<>();


    /** Creates a log for one player */
    public Log(String eventType, String playerName){
        if (logs.getImgEvents().containsKey(eventType))
            logType = "img";
        else logType = "txt";
        this.eventType = eventType;
        this.playerName = playerName;
        createLog();
    }

    /** Creates a log for two players */
    public Log(String eventType, String playerName, String opponentName) {
        if (logs.getImgEvents().containsKey(eventType))
            logType = "img";
        else logType = "txt";
        this.eventType = eventType;
        this.playerName = playerName;
        this.opponentName = opponentName;
        createLog();
    }

    /** Creates a log's text according to its type */
    private void createLog() {
        if (logs.getImgEvents().containsKey(eventType)) {
            eventString = handleEventString(logs.getImgEvents().get(eventType));
        }
        else if (logs.getTextEvents().containsKey(eventType)){
            eventString = handleEventString(logs.getTextEvents().get(eventType));
        }
    }

    /** Handles the event text according to the player's name.
     * @param eventString event text that doesn't contain player names.
     * @return the updated eventString, containing player names. */
    private String handleEventString(String eventString) {
        eventString = eventString.replaceAll("%PLAYER%", playerName);
        if(eventString.contains("%PLAYER2%")){
            eventString = eventString.replaceAll("%PLAYER2%", opponentName);
        }
        return eventString;
    }

    /** Finds the image path for the event and adds it to the log's imgPaths. */
    public void addImgPath(String img) {
        String imgPath = logs.getImgPath().get(img);
        imgPaths.add(imgPath);
    }

    public String imgPathGetter() {
        return imgPaths.get(0);
    }

    public String imgPathGetter(int i) {
        return imgPaths.get(i);
    }


    public ArrayList<String> getImgPaths() { return imgPaths; }

    public String getEventString() {
        return eventString;
    }

    public String getPathToImg(String img) {
        return logs.getImgPath().get(img);
    }

    public String getEventType() {
        return eventType;
    }

    public String getLogType() {
        return logType;
    }
}
