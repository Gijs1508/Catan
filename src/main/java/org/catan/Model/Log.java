package org.catan.Model;

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

    Player player = new Player("Jeroen");


    public Log(String eventType){
        this.eventType = eventType;
        if(eventType.equals("txt")){
            eventString = handleEventString(logs.getTextEvents().get(eventType));
        }
        else if(eventType.equals("img")){
            eventString = handleEventString(logs.getImgEvents().get(eventType));
        }
    }

    public String handleEventString(String eventString) {
        eventString = eventString.replaceAll("%PLAYER%", player.getName());
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
