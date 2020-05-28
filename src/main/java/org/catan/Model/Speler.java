package org.catan.Model;

import java.util.ArrayList;

public class Speler {

    private String naam;
    private String kleur;
//    private Inventaris spelerInventaris;
//    private ArrayList<SpelObject> spelerObjecten = new ArrayList<SpelObject>();

    public Speler(String naam) {
        this.naam = naam;
        this.kleur = "";    //TODO
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }
}
