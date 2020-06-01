package org.catan.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.catan.App;
import org.catan.Controller.LogController;

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
    private LogController logController = new LogController();
    private Logs logs = new Logs();
    private String logType;
    private String eventType;
    private String eventString;
    private String playerName;
    private ArrayList<Image> images = new ArrayList<>();

    private Speler player = new Speler("Jeroen"); //placeholder

    public Log(String logType, String eventType){
        this.logType = logType;
        this.eventType = eventType;
        playerName = player.getNaam();
        createEventString();
    }

    public void createEventString() {
        if(logType.equals("txt")){
            eventString = logs.getTextEvents().get(eventType);
        }
        else if(logType.equals("img")){
            eventString = logs.getImgEvents().get(eventType);
        }
        handleEventString();
    }

    public String handleEventString() {
        eventString = eventString.replaceAll("%PLAYER%", playerName);
        if(eventString.contains("%PLAYER2%")){
            eventString = eventString.replaceAll("%PLAYER2%", "Jeroen");
        }
        return eventString;
    }

    public Image createImage(String img) {
        Image image = new Image(String.valueOf(App.class.getResource(logs.getImgPath().get(img))));
        images.add(image);
        return image;
    }

    public Image getImage() {
        return images.get(0);
    }

    public Image getImage(int i) {
        return images.get(i);
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventString() { return eventString; }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
