package org.catan.Model;

import java.util.ArrayList;

public class Game {
    private static ArrayList<Player> players = new ArrayList<Player>();
    private String status;
    private String code;

//    public static void nextTurn() {
//        for (int i = 0; i < 3; i++){
//            players.get(i).setMainPlayer(players.get(i));
//            if (i == 3){
//                i = 0;
//            }
//        }
//    }

//    public Tile getRoverStatus(){ gameboard.getRover().getTile(); }

//    connection with borspel/gameboard class required
//    public void placeGebouw(Gebouw gebouw){
//        setSettlements(Player player, Tile tile)
//    }

//    public void upgradeGebouw(Gebouw gebouw){
//        ?
//    }

    public void giveGrondstoffen(String type, int number, Player player){
        player.getPlayerInventory().changeCards(type, number);
    }

    public void giveRidderkaarten(int number, Player player) {
        player.getPlayerInventory().changeCards("knight", number);
    }

    public void setStatus(String status) { this.status = status; }

    public String getStatus() { return status; }

    public String getCode() { return code; }

}