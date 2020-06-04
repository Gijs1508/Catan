package org.catan.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.catan.Model.Inventory;
import org.catan.Model.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class StockController implements Initializable {


    //Make controller available to other classes
    private static StockController stockController;

    @FXML private Text wheatCount;
    @FXML private Text woodCount;
    @FXML private Text brickCount;
    @FXML private Text sheepCount;
    @FXML private Text oreCount;
    @FXML private Text knightCount;
    @FXML private Button testResources;

    @FXML private Pane animationResourcesPane;
    @FXML private ImageView animationKnightCard;
    @FXML private ImageView animationWheat;
    @FXML private ImageView animationWood;
    @FXML private ImageView animationBrick;
    @FXML private ImageView animationSheep;
    @FXML private ImageView animationOre;
    private boolean animationIsActive = false;


    
    public StockController(){
        stockController = this;
    }

    private LogController logController = LogController.getInstance();
    private HashMap<String, ImageView> animationCardForResource;

    public static StockController getInstance(){
        if(stockController == null){
            stockController = new StockController();
        }
        return stockController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        animationResourcesPane.setVisible(true);
        initializeAnimationCardMap();
    }

    @FXML
    private void initialize(){}

    public void testResources(){
        Inventory inventory = Player.getMainPlayer().getPlayerInventory();
        inventory.changeCards(0, 1);
        inventory.changeCards(1, 2);
        inventory.changeCards(2, 3);
        inventory.changeCards(3, 4);
        inventory.changeCards(4, 5);
        updateResources();
    }

    public void updateResources(){
        int[] cards = Player.getMainPlayer().getPlayerInventory().getCards();
        woodCount.setText(Integer.toString(cards[0]));
        brickCount.setText(Integer.toString(cards[1]));
        oreCount.setText(Integer.toString(cards[2]));
        sheepCount.setText(Integer.toString(cards[3]));
        wheatCount.setText(Integer.toString(cards[4]));
        knightCount.setText(Integer.toString(cards[5]));
    }

    public void removeResources(ArrayList<String> resources) {
        for (int i = 0; i < resources.size(); i++) {
            removeCardAnimation(animationCardForResource.get(resources.get(i)));
        }
    }

    private void removeCardAnimation(ImageView animationCard){
        animationCard.setVisible(true);

        double x = animationCard.getTranslateX();
        double y = animationCard.getTranslateY();

        if(!animationIsActive){
            AnimationTimer animationTimer = new AnimationTimer() {
                int tick = 0;
                @Override
                public void handle(long l) {
                    tick++;
                    animationIsActive = true;

                    animationCard.setTranslateY(animationCard.getTranslateY() - 2.8);

                    if(tick > 40) {
                        animationCard.setOpacity(animationCard.getOpacity() - 0.06);
                    }

                    if(animationCard.getOpacity() <= 0) {
                        this.stop();

                        animationCard.setTranslateX(x);
                        animationCard.setTranslateY(y);
                        animationCard.setOpacity(1);
                        animationCard.setVisible(false);

                        tick = 0;
                        animationIsActive = false;
                    }
                }
            };
            animationTimer.start();
        }
    }

    public void activateKnight() {
        logController.logKnightEvent();

        removeCardAnimation(animationKnightCard);

//        ArrayList<String> resources = new ArrayList<>();          // Use this code when a resource gets taken
//        resources.add("ore");
//        resources.add("sheep");
//        removeResources(resources);
    }

    public void showKnightDetails() {
        ScreenController.getInstance().showKnightPopup();
    }

    public void hideKnightDetails() {
        ScreenController.getInstance().hideKnightPopup();
    }

    private void initializeAnimationCardMap() {
        animationCardForResource = new HashMap<>() {{
            put("wheat", animationWheat);
            put("wood", animationWood);
            put("brick", animationBrick);
            put("sheep", animationSheep);
            put("ore", animationOre);
        }};
    }
}
