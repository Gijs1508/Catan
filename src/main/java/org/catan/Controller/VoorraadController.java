package org.catan.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class VoorraadController implements Initializable {

    @FXML private Pane animationResourcesPane;
    @FXML private ImageView animationKnightCard;
    @FXML private ImageView animationWheat;
    @FXML private ImageView animationWood;
    @FXML private ImageView animationBrick;
    @FXML private ImageView animationSheep;
    @FXML private ImageView animationOre;


    private boolean animationIsActive = false;


    private LogController logController = LogController.getInstance();
    private HashMap<String, ImageView> animationCardForResource;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        animationResourcesPane.setVisible(true);
        initializeAnimationCardMap();
    }

    @FXML
    public void activateKnight() {
        logController.logKnightEvent();

        removeCardAnimation(animationKnightCard);

//        ArrayList<String> resources = new ArrayList<>();          // Use this code when a resource gets taken
//        resources.add("ore");
//        resources.add("sheep");
//        removeResources(resources);
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

    @FXML
    public void showKnightDetails() {
        ScreenController.getInstance().showKnightPopup();
    }

    @FXML
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
