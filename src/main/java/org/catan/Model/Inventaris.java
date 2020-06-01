package org.catan.Model;

import java.util.HashMap;

public class Inventaris {

    private HashMap<String, Integer> grondstoffen = new HashMap<String, Integer>(); // Misschien meer leesbaar als HashMap
    private int ridderKaarten = 0;

    public Inventaris() {
        this.grondstoffen.put("Hout", 0);
        this.grondstoffen.put("Erts", 0);
        this.grondstoffen.put("Wol", 0);
        this.grondstoffen.put("Graan", 0);
        this.grondstoffen.put("Steen", 0);
    }

    public HashMap<String, Integer> getGrondstoffen() {
        return grondstoffen;
    }

    public int getRidderKaarten() {
        return ridderKaarten;
    }

    public boolean bezitGrondstoffen() {
        return false;   //TODO
    }
}
