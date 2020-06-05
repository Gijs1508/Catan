package org.catan.Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.catan.App;
import org.catan.Model.Inventory;
import org.catan.Model.Player;

import javax.sound.sampled.AudioSystem;
import java.applet.AudioClip;
import java.io.*;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TradePopUpController implements Initializable {

    @FXML private AnchorPane popupPane;
    @FXML private ImageView declineBtn;

    @FXML private Text popupTitle;
    @FXML private Text playerName;
    @FXML private Text woodOffer;
    @FXML private Text brickOffer;
    @FXML private Text oreOffer;
    @FXML private Text sheepOffer;
    @FXML private Text wheatOffer;
    @FXML private Text woodRequest;
    @FXML private Text brickRequest;
    @FXML private Text oreRequest;
    @FXML private Text sheepRequest;
    @FXML private Text wheatRequest;

    private ScreenController screenController = ScreenController.getInstance();

    private double x;
    private double y;

    private HashMap<String, Double> paneInfo = screenController.getTradePopupLayout();
    private double paneLayoutXinRoot = paneInfo.get("layoutX");
    private double paneLayoutYinRoot = paneInfo.get("layoutY");
    private double paneWidth;
    private double paneHeight;

    public static String sender = "%PLAYER%";
    public static String[] offer = {"0", "0", "0", "0", "0"};
    public static String[] request = {"0", "0", "0", "0", "0"};
    // Lock to make sure offer cannot be accepted multiple times
    public static boolean offerLock = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        try {
//            InputStream in = new FileInputStream(String.valueOf(App.class.getResource("assets/sounds/soundeffects/pop.wav")));
//            AudioStream audioStream = new AudioStream(in);
//            AudioPlayer.player.start(audioStream);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


        paneWidth = popupPane.getPrefWidth();
        paneHeight = popupPane.getPrefHeight();

        popupTitle.setText(popupTitle.getText().replaceAll("%PLAYER%", sender));
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

        initializeDrag();
    }

    private void initializeDrag() {
        popupPane.setOnMousePressed(mouseEvent -> {
            x = popupPane.getLayoutX() - mouseEvent.getSceneX();
            y = popupPane.getLayoutY() - mouseEvent.getSceneY();
            popupPane.setCursor(Cursor.MOVE);
        });
        popupPane.setOnMouseReleased(mouseEvent -> popupPane.setCursor(Cursor.HAND));
        popupPane.setOnMouseDragged(mouseEvent -> {
            popupPane.setLayoutX(mouseEvent.getSceneX() + x);
            popupPane.setLayoutY(mouseEvent.getSceneY() + y);
        });
        popupPane.setOnMouseEntered(mouseEvent -> popupPane.setCursor(Cursor.HAND));
    }

    private void checkCollision() {
        // Collisions are recognized, but I can't find a way to make use of it.
        // X Collisions
        if(popupPane.getLayoutX() + paneLayoutXinRoot + paneWidth  >  screenController.getRoot().getPrefWidth()) {
            System.out.println("collides right");
        }
        else if(popupPane.getLayoutX() + paneLayoutXinRoot  <  0) {
            System.out.println("collides left");
        }
        // Y Collisions
        if(popupPane.getLayoutY() + paneLayoutYinRoot + paneHeight > screenController.getRoot().getPrefHeight()) {
            System.out.println("collides bottom");
        }

        if(popupPane.getLayoutY() + paneLayoutYinRoot < 0) {
            System.out.println("collides top");
        }
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

            screenController.hideTradePopup();
        }
    }

    public void declineTrade(MouseEvent mouseEvent) {
        screenController.hideTradePopup();
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
