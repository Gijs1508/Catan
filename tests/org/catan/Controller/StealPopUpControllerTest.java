package org.catan.Controller;

import org.catan.Model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test for the StealPopUpController class.
 *
 * @author Jeroen
 */

class StealPopUpControllerTest {

    /** Tests whether the colors of the opponents are functioning properly. */
    @Test
    public void updateOpponents() {
        ArrayList<Player> opponents = new ArrayList<>();
        Collections.addAll(opponents,
                new Player("testOpponent1"), new Player("testOpponent2"), new Player("testOpponent3"));

        opponents.get(0).setColor("blue");
        opponents.get(1).setColor("green");
        opponents.get(2).setColor("yellow");

        assertEquals("blue", opponents.get(0).getColor());
        assertEquals("green", opponents.get(1).getColor());
        assertEquals("yellow", opponents.get(2).getColor());
    }
}