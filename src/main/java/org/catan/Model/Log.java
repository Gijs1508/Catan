package org.catan.Model;

import javafx.scene.image.Image;
import org.catan.App;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Log class that contains information about a log entry.
 * It finds the appropriate event text that belongs to the given event that is to be logged.
 *
 * todo
 * add log to logs
 * add images to the log if it's an image log
 * make it know who's player 1 and 2
 *
 * @author Jeroen
 * @version 0.1
 */


public class Log {
    private Logs logs = new Logs();
    private String eventType;
    private String logType;
    private String eventString;
    private String playerName;
    private ArrayList<Image> images = new ArrayList<>();

    public Log(String eventType, String playerName){
        if (logs.getImgEvents().containsKey(eventType))
            logType = "img";
        else logType = "txt";
        this.eventType = eventType;
        this.playerName = playerName;
        createLog();
    }

    private void createLog() {
        if (logs.getImgEvents().containsKey(eventType)) {
            eventString = handleEventString(logs.getImgEvents().get(eventType));
        }
        else if (logs.getTextEvents().containsKey(eventType)){
            eventString = handleEventString(logs.getTextEvents().get(eventType));
        }
    }

    private String handleEventString(String eventString) {
        eventString = eventString.replaceAll("%PLAYER%", playerName);
        if(eventString.contains("%PLAYER2%")){
            eventString = eventString.replaceAll("%PLAYER2%", "Jan");       // TODO needs the other player
        }
        return eventString;
    }

    public Image createImage(String img) {
        Image image = new Image(String.valueOf(logs.getImgPath().get(img)));
        images.add(image);
        return image;
    }

    public Image getImage() {
        return images.get(0);
    }

    public Image getImage(int i) {
        return images.get(i);
    }

    public ArrayList<Image> getImages() {
        return images;
    }

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
