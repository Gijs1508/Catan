package org.catan.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class VoorraadController implements Initializable {

    @FXML private ImageView knightCard;
    @FXML private ImageView animationKnightCard;

    private AnimationTimer animationTimer;
    private boolean animationIsActive = false;


    private LogController logController = LogController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void activateKnight() {
        logController.logKnightEvent();

        removeCardAnimation(animationKnightCard);
    }

    private void removeCardAnimation(ImageView animationCard){
        animationCard.setVisible(true);

        double x = animationCard.getTranslateX();
        double y = animationCard.getTranslateY();

        if(!animationIsActive){
            animationTimer = new AnimationTimer() {
                int tick = 0;
                @Override
                public void handle(long l) {
                    tick++;
                    animationIsActive = true;

                    animationCard.setTranslateY(animationCard.getTranslateY() - 4);

                    if(tick > 40) {
                        animationCard.setOpacity(animationCard.getOpacity() - 0.04);
                    }

                    if(animationCard.getOpacity() <= 0) {
                        animationTimer.stop();

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
}
