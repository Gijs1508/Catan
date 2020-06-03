package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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

    public static String sender = "%PLAYER%";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerName.setText(sender);
    }

    public static void setSender(String name){
        sender = name;
    }

    public void acceptTrade(MouseEvent mouseEvent) {
    }

    public void declineTrade(MouseEvent mouseEvent) {
    }
}
