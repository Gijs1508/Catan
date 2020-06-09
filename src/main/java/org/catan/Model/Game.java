package org.catan.Model;

import org.catan.logic.CodeGenerator;

import java.util.ArrayList;

public class Game {

    private static ArrayList<Player> players = new ArrayList<Player>();
    public static Player activePlayer;
    private String status;
    private Long code;
    private ArrayList<Log> logs;
    private Gameboard board;

    public Game() {
        this.board = new Gameboard();
        this.players = new ArrayList<>();
        this.logs = new ArrayList<>();
        this.status = "Open";
        this.code = CreateGameCode.randomCodeGen();
    }

    public void nextTurn() {

    }

//    public Tile getRoverStatus(){
//
//    }

//    public void placeGebouw(Gebouw gebouw){
//
//    }

//    public void upgradeGebouw(Gebouw gebouw){
//
//    }

//    public void giveGrondstoffen(ArrayList<Grondstof> grondstoffen, Speler speler){
//
//    }

//    public void giveRidderkaarten(ArrayList<Ridderkaart> ridderkaarten, Speler speler) {
//
//    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    public void addSpeler(Player speler) {
        this.players.add(speler);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Long getCode(){
        return this.code;
    }

    public void addLog(Log log) {
        this.logs.add(log);
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }

    public void setLogs(ArrayList<Log> logs) {
        this.logs = logs;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Gameboard getBoard() {
        return board;
    }

    public void setBoard(Gameboard board) {
        this.board = board;
    }

    public static Player getActivePlayer(){
        return activePlayer;
    }

    public static void setActivePlayer(Player player){
        activePlayer = player;
    }

}
