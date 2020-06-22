package org.catan.Controller;

import javafx.fxml.Initializable;
import org.catan.App;
import org.catan.Model.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResourcesController implements Initializable {

    private static ResourcesController resourcesController;

    public ResourcesController(){
    }

    public static ResourcesController getInstance(){
        if(resourcesController == null){
            resourcesController = new ResourcesController();
        }
        return resourcesController;
    }

    public void setPlayerResources(int total){
        ArrayList<String> receivedResources = new ArrayList<>();
            for (Village village : App.getCurrentGame().getBoard().getSettlements()) {
                int amount;
                if(village.isUpgraded()){
                    amount = 2;
                } else {
                    amount = 1;
                }
                for (Tile tile : village.getConnectedTiles()){
                    if(total == tile.getNumber() && village.getColor().equals(App.getClientPlayer().getColor()) && !tile.getId().equals(getThiefTileId())){
                        for (Player player : App.getCurrentGame().getPlayers()) {
                            if (player.getColor().equals(App.getClientPlayer().getColor()))
                                player.getPlayerInventory().changeCards(tile.getType(), amount);
                        }
                        receivedResources.add(tile.getType());
                    }
                }
            if(!receivedResources.isEmpty()) {
                StockController.getInstance().updateResources();
                LogController.getInstance().logReceiveEvent(receivedResources);
            }
        }
    }

    private String getThiefTileId() {
        return "tile" + App.getCurrentGame().getBoard().getThief().getTile();
    }

    private int getIntFromImgPath(String imgpath) {
        switch (imgpath) {
            case "org/catan/assets/img/die/die1.png":
                return 1;
            case "org/catan/assets/img/die/die2.png":
                return 2;
            case "org/catan/assets/img/die/die3.png":
                return 3;
            case "org/catan/assets/img/die/die4.png":
                return 4;
            case "org/catan/assets/img/die/die5.png":
                return 5;
            case "org/catan/assets/img/die/die6.png":
                return 6;
            default:
                return 0;
        }
    }

    public void updateByLog(Log log) {
        String diceResult1 = log.getImgPaths().get(0);
        String diceResult2 = log.getImgPaths().get(1);
        int diceTotal = getIntFromImgPath(diceResult1) + getIntFromImgPath(diceResult2);
        setPlayerResources(diceTotal);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resourcesController = this;
    }
}
