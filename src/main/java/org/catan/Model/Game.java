package org.catan.Model;

import java.util.ArrayList;

public class Game {

    private static ArrayList<Player> players = new ArrayList<Player>();
    public static Player activePlayer;
    private String status;
    private Long code;
    private ArrayList<Log> logs;
    private Gameboard board;
    private ArrayList<Village> buildVillages = new ArrayList<>();
    private TradeOffer tradeOffer = new TradeOffer();
    private ArrayList<TradeOffer> tradeOffers = new ArrayList<TradeOffer>();
    private String tradeStatus = "closed";

    public Game() {
        this.board = new Gameboard();
        this.players = new ArrayList<>();
        this.logs = new ArrayList<>();
        this.status = "open";
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

    // Updates the array when a new village is build
    public void setBuildVillages(ArrayList<Village> buildVillages) {
        this.buildVillages = buildVillages;
    }
    /** Returns the build villages
     * Can be accessed in every class that needs it
     * @return an ArrayList type Village */
    public ArrayList<Village> getBuildVillages() {
        return buildVillages;
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

    public Player getActivePlayer(){
        return activePlayer;
    }

    public void setActivePlayer(Player player){
        activePlayer = player;
    }

    public void removePlayer(Player player) {
        Player playerToRemove = new Player();
        for (Player gamePlayer: players) {
            if (gamePlayer.getIdentifier() == player.getIdentifier()) {
                playerToRemove = gamePlayer;
            }
        }
        players.remove(playerToRemove);
    }

    public Player turnPlayerGetter() {
        for (Player player : players) {
            if (player.isTurn()) {
                return player;
            }
        }

        return new Player();
    }

    public TradeOffer getTradeOffer(){
        return tradeOffer;
    }

    public String getTradeStatus(){
        return tradeStatus;
    }

    public void setTradeStatus(String status){
        this.tradeStatus = status;
    }

    public void setTradeOffers(ArrayList<TradeOffer> tradeOffers){
        this.tradeOffers = tradeOffers;
    }

    public ArrayList<TradeOffer> getTradeOffers(){
        return this.tradeOffers;
    }
}
