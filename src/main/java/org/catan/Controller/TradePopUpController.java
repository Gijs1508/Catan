package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.catan.Model.Inventory;
import org.catan.Model.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class TradePopUpController implements Initializable {

    @FXML
    private Text playerName;
    @FXML
    private Text woodOffer;
    @FXML
    private Text brickOffer;
    @FXML
    private Text oreOffer;
    @FXML
    private Text sheepOffer;
    @FXML
    private Text wheatOffer;
    @FXML
    private Text woodRequest;
    @FXML
    private Text brickRequest;
    @FXML
    private Text oreRequest;
    @FXML
    private Text sheepRequest;
    @FXML
    private Text wheatRequest;
    @FXML
    private ImageView declineBtn;

    public static String sender = "%PLAYER%";
    public static String[] offer = {"0", "0", "0", "0", "0"};
    public static String[] request = {"0", "0", "0", "0", "0"};
    // Lock to make sure offer cannot be accepted multiple times
    public static boolean offerLock = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerName.setText(sender);
        woodOffer.setText(offer[0]);
        brickOffer.setText(offer[1]);
        oreOffer.setText(offer[2]);
        sheepOffer.setText(offer[3]);
        wheatOffer.setText(offer[4]);
        woodRequest.setText(request[0]);
        brickRequest.setText(request[1]);
        oreRequest.setText(request[2]);
        sheepRequest.setText(request[3]);
        wheatRequest.setText(request[4]);
        offerLock = false;
    }

    public static void updateTradeOffer(String name, String[] offerArray, String[] requestArray){
        sender = name;
        offer = offerArray;
        request = requestArray;
    }

    public void acceptTrade(MouseEvent mouseEvent) {
        //TODO tradelock
        Inventory playerInventory = Player.getMainPlayer().getPlayerInventory();
        int[] cards = playerInventory.getCards();
        if(!offerLock && ownCards()){
            for (int i = 0; i < offer.length; i++){
                playerInventory.changeCards(i, Integer.parseInt(offer[i]));
                playerInventory.changeCards(i, -Integer.parseInt(request[i]));
            }
            offerLock = true;
            Stage stage = (Stage) declineBtn.getScene().getWindow();
            stage.close();
        }
    }

    public void declineTrade(MouseEvent mouseEvent) {
        Stage stage = (Stage) declineBtn.getScene().getWindow();
        stage.close();
    }

    // Check if player owns all the requested cards
    public boolean ownCards(){
        boolean ownCards = true;
        int[] cards = Player.getMainPlayer().getPlayerInventory().getCards();
        for (int i = 0; i < request.length; i++){
            if (!(cards[i] >= Integer.parseInt(request[i]))){
                return false;
            }
        }
        return ownCards;
    }

}
