package org.catan.logic;

import org.catan.Model.*;

public class GameDataPrinter {
    public static void printGameDetails(Game game) {
        System.out.println(game.getCode());
        System.out.println("game players: ");
        for (Player player : game.getPlayers()) {
            System.out.println(player.getName());
            System.out.println(player.getColor());
            for (int cardCount : player.getPlayerInventory().getCards()) {
                System.out.println(cardCount);
            }
        }
        System.out.println("game logs: ");
        for (Log log : game.getLogs()) {
            System.out.println(log.getImgPaths());
            System.out.println(log.getEventString());
        }
        System.out.println("game board villages: ");
        for (Village village : game.getBuildVillages()) {
            System.out.println(village.getX());
            System.out.println(village.getY());
            System.out.println("is upgraded: " + village.isUpgraded());
            System.out.println("village connected tiles: ");
            for (Tile tile : village.getConnectedTiles()) {
                System.out.println(tile.getType());
            }
        }
        System.out.println("game board roads: ");
        for (Road road : game.getBoard().getRoads()) {
            System.out.println(road.getColor());
            System.out.println(road.getImgPath());
        }
    }
}
