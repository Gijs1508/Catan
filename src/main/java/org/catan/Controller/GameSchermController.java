package org.catan.Controller;

//import Model.Spel;
//import Model.Spelbord;
//import Model.Speler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import javax.swing.text.html.ImageView;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameSchermController implements Initializable {

    private int aantalSpelers;
//    private Spelbord spelbord;
//    private Spel spel;
    private ArrayList<Circle> vertices;           // later in hashmap met bijbehorende class?
    private ArrayList<Circle> roadSpotNodeList;
    private ArrayList<Label> tileNumNodeList;
    private ArrayList<ImageView> roadNodeList;
    private ArrayList<Polygon> tileNodeList;



    public GameSchermController() {

    }

    private void keyHandler() {

    }

    private void timer() {

    }

    private void handelMetBank() {

    }

    private void handelMetSpelers() {

    }

    private void gebruikRidderkaart() {

    }

    @FXML
    public void buildSettlement() {
    }

    @FXML
    public void emphasizeRoad() {
    }

    @FXML
    public void undoEmphasizeRoad() {
    }

    @FXML
    public void emphasizeSettlement() {
    }

    @FXML
    public void undoEmphasizeSettlement() {
    }


    @FXML
    public void upgradeSettlement() {
    }

    @FXML
    public void buildRoad() {
    }

    @FXML
    public void endTurn() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        tileNodeList.addAll(tile1)
        initializePlacementSpots();
    }

    private void initializePlacementSpots(){

    }

//    private Speler getSpeler() {
//        return Speler; // Dit moet worden gewijzigd
//    }
}
