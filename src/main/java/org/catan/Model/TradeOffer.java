package org.catan.Model;

import org.catan.App;
import org.catan.Controller.TradePopUpController;
import org.catan.logic.DatabaseConnector;

/**
 * Used to create & update trade offers
 *
 * @author Kaz
 */
public class TradeOffer {

    private Player sender;
    private int[] offeredCards = {0, 0, 0, 0, 0};
    private int[] requestedCards = {0, 0, 0, 0, 0};
    private int rejections;

    public TradeOffer(){
    }

    /**
     * This method updates the trade offer
     * @param sender The player who sent the offer
     * @param offer The offered resources
     * @param request The requested resources
     */
    public void updateOffer(Player sender, int[] offer, int[] request){
        this.sender = sender;
        this.offeredCards = offer;
        this.requestedCards = request;
        this.rejections = 0;
    }

    public void setSender(Player player){
        this.sender = player;
    }

    public Player getSender(){
        return this.sender;
    }

    public int[] getOfferedCards(){
        return this.offeredCards;
    }

    public int[] getRequestedCards(){
        return this.requestedCards;
    }

    public void addRejection(){
        this.rejections += 1;
    }

    public int getRejections(){
        return this.rejections;
    }
}
