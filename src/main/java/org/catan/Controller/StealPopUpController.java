package org.catan.Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import org.catan.Model.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;

public class StealPopUpController implements Initializable {
    @FXML Circle opponent1;
    @FXML Circle opponent2;
    @FXML Circle opponent3;
    @FXML HBox circleBox;

    private ScreenController screenController = ScreenController.getInstance();
    private GameSchermController gameSchermController = GameSchermController.getInstance();
    private static StealPopUpController stealPopUpController;

    private final String RED = "#e74c3c";
    private final String GREEN = "#2ecc71";
    private final String BLUE = "#3498db";
    private final String YELLOW = "#f4d03f";
    private HashMap<String, String> colorToHex = new HashMap<>() {{
        put("red", RED);
        put("green", GREEN);
        put("blue", BLUE);
        put("yellow", YELLOW);
    }};
    private ArrayList<Circle> circles = new ArrayList<>();
    private ArrayList<Player> opponents;

    public StealPopUpController() {
        stealPopUpController = this;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        circleBox.getChildren().clear();
    }

    public void updateOpponents(ArrayList<Player> opponents) {
        this.opponents = opponents;
        for(Player opponent : opponents) {
            createCircle(opponent);
        }
    }

    private void createCircle(Player opponent) {
        Circle circle = new Circle();
        circle.setCursor(Cursor.HAND);
        circle.setRadius(36);
        circle.setStroke(Color.web("#0000001a"));
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setStrokeWidth(2);

        circle.setFill(Color.web(colorToHex.get(opponent.getColor())));

        if(circles.isEmpty()) { // This circle is the first circle
            circle.setOnMouseClicked(e -> opponent1clicked()); }
        else if(circles.size() == 1) { // This circle is the second circle
            circle.setOnMouseClicked(e -> opponent2clicked()); }
        else { // This circle is the third circle (there can't be four)
            circle.setOnMouseClicked(e -> opponent3clicked()); }

        circles.add(circle);
        circleBox.getChildren().add(circle);
    }

    public void opponent1clicked() {
        gameSchermController.stealFromVictim(opponents.get(0));
        screenController.hideStealPopUp();
    }

    public void opponent2clicked() {
        gameSchermController.stealFromVictim(opponents.get(1));
        screenController.hideStealPopUp();
    }

    public void opponent3clicked() {
        gameSchermController.stealFromVictim(opponents.get(2));
        screenController.hideStealPopUp();
    }

    public static StealPopUpController getInstance() {
        return stealPopUpController;
    }
}
