package org.catan.Controller;

//import Model.Spel;
//import Model.Spelbord;
//import Model.Speler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import org.catan.App;
import org.catan.Model.RandomizeBoard;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class GameSchermController implements javafx.fxml.Initializable{

    private int aantalSpelers;
//    private Spelbord spelbord;
//    private Spel spel;

    // Tiles
    @FXML private Polygon tile1;
    @FXML private Polygon tile2;
    @FXML private Polygon tile3;
    @FXML private Polygon tile4;
    @FXML private Polygon tile5;
    @FXML private Polygon tile6;
    @FXML private Polygon tile7;
    @FXML private Polygon tile8;
    @FXML private Polygon tile9;
    @FXML private Polygon tile11;
    @FXML private Polygon tile12;
    @FXML private Polygon tile13;
    @FXML private Polygon tile14;
    @FXML private Polygon tile15;
    @FXML private Polygon tile16;
    @FXML private Polygon tile17;
    @FXML private Polygon tile18;
    @FXML private Polygon tile19;
    // This is the bandit
    @FXML private Polygon tile10;

    // Tile numbers
    @FXML private Label tile1num;
    @FXML private Label tile2num;
    @FXML private Label tile3num;
    @FXML private Label tile4num;
    @FXML private Label tile5num;
    @FXML private Label tile6num;
    @FXML private Label tile7num;
    @FXML private Label tile8num;
    @FXML private Label tile9num;
    @FXML private Label tile11num;
    @FXML private Label tile12num;
    @FXML private Label tile13num;
    @FXML private Label tile14num;
    @FXML private Label tile15num;
    @FXML private Label tile16num;
    @FXML private Label tile17num;
    @FXML private Label tile18num;
    @FXML private Label tile19num;


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
    public void showVertex() {
    }

    @FXML
    public void hideVertex() {
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
        ArrayList<Polygon> tiles = addAllTilesToArray();
        ArrayList<Label> labels = addAllTileNumbersToArray();
        RandomizeBoard.setRandomTiles(tiles, labels);

        //tile1.setFill(Color.BROWN);
    }

    private ArrayList<Polygon> addAllTilesToArray() {
        ArrayList<Polygon> tiles = new ArrayList<Polygon>();

        Collections.addAll(tiles, tile1, tile2, tile3, tile4, tile5,
                tile6, tile7, tile8, tile9, tile11, tile12, tile13,
                tile14, tile15,tile16, tile17, tile18, tile19);


        return tiles;
    }

    private ArrayList<Label> addAllTileNumbersToArray(){
        ArrayList<Label> tileLabels = new ArrayList<Label>();

        Collections.addAll(tileLabels, tile1num, tile2num, tile3num, tile4num,
                tile5num, tile6num, tile7num, tile8num, tile9num,
                tile11num, tile12num, tile13num, tile14num, tile15num,
                tile16num, tile17num, tile18num, tile19num);

        return tileLabels;
    }

//    private Speler getSpeler() {
//        return Speler; // Dit moet worden gewijzigd
//    }
}
