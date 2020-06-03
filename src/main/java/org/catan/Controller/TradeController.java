package org.catan.Controller;

import javafx.fxml.FXML;;
import org.catan.Model.Speler;

public class TradeController {

    LogController logController = LogController.getInstance();

    @FXML
    public void bankTrade() {
    }

    @FXML
    public void playerTrade() {
    }

    @FXML
    public void buyDevelopmentCard() {
    }

    @FXML
    public void sendTrade() {
        Speler opponent = new Speler("Jan");  // placeholder
        logController.logTradeEvent(opponent);       // move to method that executes when trade succeeded
    }


    @FXML
    public void resetTrade() {
    }

    @FXML
    public void takeMoreWood() {
    }

    @FXML
    public void giveMoreWood() {
    }

    @FXML
    public void giveMoreBrick() {
    }

    @FXML
    public void takeMoreBrick() {
    }

    @FXML
    public void giveMoreSheep() {
    }

    @FXML
    public void takeMoreSheep() {
    }

    @FXML
    public void giveMoreOre() {
    }

    @FXML
    public void takeMoreOre() {
    }

    @FXML
    public void takeMoreWheat() {
    }

    @FXML
    public void giveMoreWheat() {
    }

}
