package org.catan.Model;

import org.catan.App;
import org.catan.Controller.TradePopUpController;
import org.catan.logic.DatabaseConnector;

public class TradeOffer {

    private Player sender;
    private String[] offeredCards;
    private String[] requestedCards;

    public TradeOffer(){
    }

    public void updateOffer(Player sender, String[] offer, String[] request){
        this.sender = sender;
        this.offeredCards = offer;
        this.requestedCards = request;
        TradePopUpController.updateTradeOffer(sender, offer, request);
    }

    public String[][] fetchTradeOffer(){
        String[][] trade = {offeredCards, requestedCards};
        return trade;
    }

    public void setSender(Player player){
        this.sender = player;
    }

    public Player getSender(){
        return this.sender;
    }

    public String[] getOfferedCards(){
        return offeredCards;
    }

    public String[] getRequestedCards(){
        return requestedCards;
    }
}
