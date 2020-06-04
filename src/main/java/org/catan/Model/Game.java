package org.catan.Model;


import org.catan.logic.CodeGenerator;

import java.util.ArrayList;

public class Game {

    private ArrayList<Player> players;
    private String status;
    private String code;
    private Logs logs;
    private Gameboard board;

    public Game() {
        this.board = new Gameboard();
        this.players = new ArrayList<>();
        this.logs = new Logs();
        this.status = "Open";
        this.code = CodeGenerator.generateCode(10);
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

    public String getCode(){
        return this.code;
    }

    public void addLog(Log log) {
        this.logs.addLog(log);
    }

    public ArrayList<Log> getLogs() {
        return logs.getLogs();
    }

    public void setLogs(Logs logs) {
        this.logs = logs;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Gameboard getBoard() {
        return board;
    }

    public void setBoard(Gameboard board) {
        this.board = board;
    }
}
