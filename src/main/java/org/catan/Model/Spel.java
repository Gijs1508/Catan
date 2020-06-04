package org.catan.Model;


import org.catan.logic.CodeGenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class Spel {

    private ArrayList<Speler> spelers;
    private String status;
    private String code;
    private Logs logs;

    public Spel() {
        this.spelers = new ArrayList<>();
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

    public void addSpeler(Speler speler) {
        this.spelers.add(speler);
    }

    public ArrayList<Speler> getSpelers() {
        return this.spelers;
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

    public void setSpelers(ArrayList<Speler> spelers) {
        this.spelers = spelers;
    }

    public void setCode(String code) {
        this.code = code;
    }
}