package org.catan.logic;

import org.catan.App;
import org.catan.Model.*;

import java.util.ArrayList;

public class CreateTestGame {
    public static Game createTestGame() {
        Game game = new Game();
        Player jan = new Player("Jan");
        jan.setColor("blue");
        Player sabrina = new Player("Sabrina");
        sabrina.setColor("red");
        game.addSpeler(jan);
        game.addSpeler(sabrina);
        Tile tile1 = new Tile("wood", "tile1", 3);
        Tile tile2 = new Tile("stone", "tile2", 6);
        Tile tile3 = new Tile("ore", "tile3", 10);
        Tile tile4 = new Tile("wheat", "tile4", 8);
        Tile tile5 = new Tile("wool", "tile5", 2);
        Tile tile6 = new Tile("stone", "tile6", 4);
        Village janVillage1 = new Village(10, 33,"blue",new ArrayList<>(){{
            add(tile1);
            add(tile2);
            add(tile5);
        }});
        Village janVillage2 = new Village(40, 77,"blue",new ArrayList<>(){{
            add(tile3);
            add(tile2);
            add(tile6);
        }});
        Village sabrinaVillage1 = new Village(10, 33,"blue",new ArrayList<>(){{
            add(tile3);
            add(tile4);
            add(tile1);
        }});
//        game.getBoard().addSettlement(janVillage1);
//        game.getBoard().addSettlement(janVillage2);
//        game.getBoard().addSettlement(sabrinaVillage1);

        Road janRoad1 = new Road(33, 66, jan.getColor());
        Road janRoad2 = new Road(20, 77, jan.getColor());
        Road sabrinaRoad1 = new Road(10, 100, sabrina.getColor());
        Road sabrinaRoad2 = new Road(100, 10, sabrina.getColor());
        Road sabrinaRoad3 = new Road(45, 75, sabrina.getColor());

        game.getBoard().addRoad(janRoad1);
        game.getBoard().addRoad(janRoad2);
        game.getBoard().addRoad(sabrinaRoad1);
        game.getBoard().addRoad(sabrinaRoad2);
        game.getBoard().addRoad(sabrinaRoad3);

        Log log1 = new Log("roll", jan.getName());
        log1.addImgPath("3");
        log1.addImgPath("4");
        Log log2 = new Log("upgrade", sabrina.getName());
        game.addLog(log1);
        game.addLog(log2);

        return game;
    }
}
