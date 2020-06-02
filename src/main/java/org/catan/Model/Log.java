package org.catan.Model;

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
    private Logs logs;
    private String eventType;
    private String eventString;

    Speler player = new Speler();

    public Log() {}

    public Log(String eventType){
        this.eventType = eventType;
        if(eventType.equals("txt")){
            eventString = handleEventString("txt event");
        }
        else if(eventType.equals("img")){
            eventString = handleEventString(logs.getImgEvents().get(eventType));
        }
    }

    public String handleEventString(String eventString) {
        eventString = eventString.replaceAll("%PLAYER%", player.getNaam());
        if(eventString.contains("%PLAYER2%")){
            eventString = eventString.replaceAll("%PLAYER2%", "Jan");
        }
        return eventString;
    }

    public String getPathToImg(String img) {
        return logs.getImgPath().get(img);
    }

    public String getEventType() {
        return eventType;
    }
}
