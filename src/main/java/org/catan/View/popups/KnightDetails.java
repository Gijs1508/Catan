package org.catan.View.popups;

import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.catan.App;

public class KnightDetails {

    AnchorPane root = new AnchorPane();
    ImageView knightDetails = new ImageView();

    private static FadeTransition fadeIn = new FadeTransition(Duration.millis(300));
    private static FadeTransition fadeOut = new FadeTransition(Duration.millis(300));

    public KnightDetails() {
        initialiseFades();
        initialiseKnightPopup();
    }

    private void initialiseKnightPopup(){
        root.setPrefHeight(296);
        root.setPrefWidth(185);

        knightDetails.setImage(new Image(String.valueOf(App.class.getResource("assets/img/knightDetails.png"))));
        knightDetails.setFitHeight(root.getPrefHeight());
        knightDetails.setFitWidth(root.getPrefWidth());

        root.getChildren().add(knightDetails);
    }

    private void initialiseFades() {
//        fadeIn = new FadeTransition(Duration.millis(300));
        fadeIn.setNode(root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setCycleCount(1);
        fadeIn.setAutoReverse(false);

//        fadeOut = new FadeTransition(Duration.millis(300));
        fadeOut.setNode(root);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setCycleCount(1);
        fadeOut.setAutoReverse(false);
    }

    public AnchorPane getRoot() {
        return root;
    }

    public static FadeTransition getFadeIn() {
        return fadeIn;
    }

    public static FadeTransition getFadeOut() {
        return fadeOut;
    }
}
