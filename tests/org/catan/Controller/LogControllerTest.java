package org.catan.Controller;

import org.catan.Model.Log;
import org.catan.Model.Logs;
import org.catan.Model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test for the LogController class
 *
 * @author Jeroen
 */

class LogControllerTest {

    /** Tests the backend logic of logging a roll event */
    @Test
    public void logRollEvent() {
        String dice1 = "3";
        String dice2 = "2";
        Logs logs = new Logs();
        ArrayList<String> imgPaths = new ArrayList<>();
        imgPaths.add(logs.getImgPath().get(dice1));
        imgPaths.add(logs.getImgPath().get(dice2));

        Player testPlayer = new Player("testPlayer");
        Log log = new Log("roll", testPlayer.getName());
        log.addImgPath(dice1);
        log.addImgPath(dice2);

        assertEquals("roll", log.getEventType());
        assertEquals(imgPaths, log.getImgPaths());
        assertEquals("img", log.getLogType());
        assertEquals("testPlayer rolled:", log.getEventString());
    }
}