package org.catan.Model;

public class Bank {
    private Inventory bankInventory;

    public Bank(){
        this.bankInventory = new Inventory();
        // Kaarten toevoegen aan bank, kijken hoe we dit kunnen veranderen naar kaarten over in stapel
        this.bankInventory.changeCards("wood", 50);
        this.bankInventory.changeCards("brick", 50);
        this.bankInventory.changeCards("ore", 50);
        this.bankInventory.changeCards("wool", 50);
        this.bankInventory.changeCards("wheat", 50);
    }

    public Inventory getCards(){
        return this.bankInventory;
    }

}