package org.catan.Model;

import java.util.ArrayList;

public class Game {

    private static ArrayList<Player> players = new ArrayList<>();
    private String status;
    private Long code;
    private ArrayList<Log> logs;
    private Gameboard board;
    private Bank bank;
    private ArrayList<TradeOffer> tradeOffers = new ArrayList<TradeOffer>();
    private String tradeStatus = "closed";
    private boolean sevenThrown = false;

    public Game() {
        this.board = new Gameboard();
        this.players = new ArrayList<>();
        this.bank = new Bank();
        this.logs = new ArrayList<>();
        this.status = "open";
        this.code = CreateGameCode.randomCodeGen();
    }

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

    public Bank getBank() { return bank; }
    public void setBank(Bank bank) { this.bank = bank; }


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

    public boolean isSevenThrown(){
        return sevenThrown;
    }

    public void setSevenThrown(boolean sevenThrown){
        this.sevenThrown = sevenThrown;
    }
}
