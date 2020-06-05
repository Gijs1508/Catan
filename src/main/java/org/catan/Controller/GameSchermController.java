package org.catan.Controller;

//import Model.Spel;
//import Model.Spelbord;
//import Model.Speler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import org.catan.Model.CreateGameCode;
import org.catan.Model.Player;
import org.catan.Model.RandomizeBoard;
import org.catan.Model.TurnManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class GameSchermController implements Initializable {

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

    @FXML private ImageView roadButton;
    @FXML private ImageView settlementButton;
    @FXML private ImageView upgradeButton;
    @FXML private ImageView roadButtonClose;
    @FXML private ImageView settlementButtonClose;
    @FXML private ImageView upgradeButtonClose;

    @FXML private Circle vertex1;
    @FXML private Circle vertex2;
    @FXML private Circle vertex3;
    @FXML private Circle vertex4;
    @FXML private Circle vertex5;
    @FXML private Circle vertex6;
    @FXML private Circle vertex7;
    @FXML private Circle vertex8;
    @FXML private Circle vertex9;
    @FXML private Circle vertex10;
    @FXML private Circle vertex11;
    @FXML private Circle vertex12;
    @FXML private Circle vertex13;
    @FXML private Circle vertex14;
    @FXML private Circle vertex15;
    @FXML private Circle vertex16;
    @FXML private Circle vertex17;
    @FXML private Circle vertex18;
    @FXML private Circle vertex19;
    @FXML private Circle vertex20;
    @FXML private Circle vertex21;
    @FXML private Circle vertex22;
    @FXML private Circle vertex23;
    @FXML private Circle vertex24;
    @FXML private Circle vertex25;
    @FXML private Circle vertex26;
    @FXML private Circle vertex27;
    @FXML private Circle vertex28;
    @FXML private Circle vertex29;
    @FXML private Circle vertex30;
    @FXML private Circle vertex31;
    @FXML private Circle vertex32;
    @FXML private Circle vertex33;
    @FXML private Circle vertex34;
    @FXML private Circle vertex35;
    @FXML private Circle vertex36;
    @FXML private Circle vertex37;
    @FXML private Circle vertex38;
    @FXML private Circle vertex39;
    @FXML private Circle vertex40;
    @FXML private Circle vertex41;
    @FXML private Circle vertex42;
    @FXML private Circle vertex43;
    @FXML private Circle vertex44;
    @FXML private Circle vertex45;
    @FXML private Circle vertex46;
    @FXML private Circle vertex47;
    @FXML private Circle vertex48;
    @FXML private Circle vertex49;
    @FXML private Circle vertex50;
    @FXML private Circle vertex51;
    @FXML private Circle vertex52;
    @FXML private Circle vertex53;
    @FXML private Circle vertex54;

    @FXML private Circle roadSpot1;
    @FXML private Circle roadSpot2;
    @FXML private Circle roadSpot3;
    @FXML private Circle roadSpot4;
    @FXML private Circle roadSpot5;
    @FXML private Circle roadSpot6;
    @FXML private Circle roadSpot7;
    @FXML private Circle roadSpot8;
    @FXML private Circle roadSpot9;
    @FXML private Circle roadSpot10;
    @FXML private Circle roadSpot11;
    @FXML private Circle roadSpot12;
    @FXML private Circle roadSpot13;
    @FXML private Circle roadSpot14;
    @FXML private Circle roadSpot15;
    @FXML private Circle roadSpot16;
    @FXML private Circle roadSpot17;
    @FXML private Circle roadSpot18;
    @FXML private Circle roadSpot19;
    @FXML private Circle roadSpot20;
    @FXML private Circle roadSpot21;
    @FXML private Circle roadSpot22;
    @FXML private Circle roadSpot23;
    @FXML private Circle roadSpot24;
    @FXML private Circle roadSpot25;
    @FXML private Circle roadSpot26;
    @FXML private Circle roadSpot27;
    @FXML private Circle roadSpot28;
    @FXML private Circle roadSpot29;
    @FXML private Circle roadSpot30;
    @FXML private Circle roadSpot31;
    @FXML private Circle roadSpot32;
    @FXML private Circle roadSpot33;
    @FXML private Circle roadSpot34;
    @FXML private Circle roadSpot35;
    @FXML private Circle roadSpot36;
    @FXML private Circle roadSpot37;
    @FXML private Circle roadSpot38;
    @FXML private Circle roadSpot39;
    @FXML private Circle roadSpot40;
    @FXML private Circle roadSpot41;
    @FXML private Circle roadSpot42;
    @FXML private Circle roadSpot43;
    @FXML private Circle roadSpot44;
    @FXML private Circle roadSpot45;
    @FXML private Circle roadSpot46;
    @FXML private Circle roadSpot47;
    @FXML private Circle roadSpot48;
    @FXML private Circle roadSpot49;
    @FXML private Circle roadSpot50;
    @FXML private Circle roadSpot51;
    @FXML private Circle roadSpot52;
    @FXML private Circle roadSpot53;
    @FXML private Circle roadSpot54;
    @FXML private Circle roadSpot55;
    @FXML private Circle roadSpot56;
    @FXML private Circle roadSpot57;
    @FXML private Circle roadSpot58;
    @FXML private Circle roadSpot59;
    @FXML private Circle roadSpot60;
    @FXML private Circle roadSpot61;
    @FXML private Circle roadSpot62;
    @FXML private Circle roadSpot63;
    @FXML private Circle roadSpot64;
    @FXML private Circle roadSpot65;
    @FXML private Circle roadSpot66;
    @FXML private Circle roadSpot67;
    @FXML private Circle roadSpot68;
    @FXML private Circle roadSpot69;
    @FXML private Circle roadSpot70;
    @FXML private Circle roadSpot71;
    @FXML private Circle roadSpot72;

    @FXML private ImageView road1;
    @FXML private ImageView road2;
    @FXML private ImageView road3;
    @FXML private ImageView road4;
    @FXML private ImageView road5;
    @FXML private ImageView road6;
    @FXML private ImageView road7;
    @FXML private ImageView road8;
    @FXML private ImageView road9;
    @FXML private ImageView road10;
    @FXML private ImageView road11;
    @FXML private ImageView road12;
    @FXML private ImageView road13;
    @FXML private ImageView road14;
    @FXML private ImageView road15;
    @FXML private ImageView road16;
    @FXML private ImageView road17;
    @FXML private ImageView road18;
    @FXML private ImageView road19;
    @FXML private ImageView road20;
    @FXML private ImageView road21;
    @FXML private ImageView road22;
    @FXML private ImageView road23;
    @FXML private ImageView road24;
    @FXML private ImageView road25;
    @FXML private ImageView road26;
    @FXML private ImageView road27;
    @FXML private ImageView road28;
    @FXML private ImageView road29;
    @FXML private ImageView road30;
    @FXML private ImageView road31;
    @FXML private ImageView road32;
    @FXML private ImageView road33;
    @FXML private ImageView road34;
    @FXML private ImageView road35;
    @FXML private ImageView road36;
    @FXML private ImageView road37;
    @FXML private ImageView road38;
    @FXML private ImageView road39;
    @FXML private ImageView road40;
    @FXML private ImageView road41;
    @FXML private ImageView road42;
    @FXML private ImageView road43;
    @FXML private ImageView road44;
    @FXML private ImageView road45;
    @FXML private ImageView road46;
    @FXML private ImageView road47;
    @FXML private ImageView road48;
    @FXML private ImageView road49;
    @FXML private ImageView road50;
    @FXML private ImageView road51;
    @FXML private ImageView road52;
    @FXML private ImageView road53;
    @FXML private ImageView road54;
    @FXML private ImageView road55;
    @FXML private ImageView road56;
    @FXML private ImageView road57;
    @FXML private ImageView road58;
    @FXML private ImageView road59;
    @FXML private ImageView road60;
    @FXML private ImageView road61;
    @FXML private ImageView road62;
    @FXML private ImageView road63;
    @FXML private ImageView road64;
    @FXML private ImageView road65;
    @FXML private ImageView road66;
    @FXML private ImageView road67;
    @FXML private ImageView road68;
    @FXML private ImageView road69;
    @FXML private ImageView road70;
    @FXML private ImageView road71;
    @FXML private ImageView road72;

    //    private Spelbord spelbord;
//    private Spel spel;
    private ArrayList<Circle> vertexNodeList = new ArrayList<>();           // Probably needs to be in a HashMap later on to connect a model with the node.
    private ArrayList<Circle> roadSpotNodeList = new ArrayList<>();
    private ArrayList<Label> tileNumNodeList;
    private ArrayList<ImageView> roadNodeList = new ArrayList<>();
    private ArrayList<Polygon> tileNodeList;

    LogController logController = LogController.getInstance();


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


    // TODO Jan
    @FXML
    public void buildSettlement(MouseEvent mouseEvent) {
        logController.logSettlementEvent();

        Circle circle = (Circle) mouseEvent.getSource(); // The vertex node that is clicked
        circle.getLayoutX();                             // Information you might need for the settlement/road
        circle.getLayoutY();                             // Use it as a parameter (so the logic isn't in this controller)
    }

    @FXML
    public void buildRoad(MouseEvent mouseEvent) {
        logController.logRoadEvent();
    }
    

    @FXML
    public void showVertex() {
    }

    @FXML
    public void hideVertex() {
    }

    @FXML
    public void upgradeSettlement() {
        logController.logUpgradeEvent();
    }

    @FXML
    public void endTurn() {
        logController.logEndTurnEvent();
        TurnManager.nextTurn(Player.getMainPlayer());
        //TODO
    }

    @FXML
    public void emphasizeRoad(MouseEvent mouseEvent) {
        Circle circle = (Circle) mouseEvent.getSource();
        // Code that checks whether the road can be placed
        // RoadSpot roadSpot = getAssociatedRoadSpot(circle)
        // if (roadSpot.isValid() {
        circle.setFill(Paint.valueOf("#c89eff"));
        circle.setScaleX(1.1);
        circle.setScaleY(1.1);
    }

    @FXML
    public void undoEmphasizeRoad(MouseEvent mouseEvent) {
        Circle circle = (Circle) mouseEvent.getSource();
        circle.setFill(Paint.valueOf("#c89cffd9"));
        circle.setScaleX(1);
        circle.setScaleY(1);
    }

    @FXML
    public void emphasizeSettlement(MouseEvent mouseEvent) {
        Circle circle = (Circle) mouseEvent.getSource();
        // Code that checks whether the settlement can be placed
        // Vertex vertex = getAssociatedVertex(circle)
        // if (vertex.isValid() {
        circle.setFill(Color.WHITE);
        circle.setScaleX(1.1);
        circle.setScaleY(1.1);
    }

    @FXML
    public void undoEmphasizeSettlement(MouseEvent mouseEvent) {
        Circle circle = (Circle) mouseEvent.getSource();
        circle.setFill(Paint.valueOf("#ffffffa8"));
        circle.setScaleX(1);
        circle.setScaleY(1);
    }

    @FXML
    public void buildRoadBtnClicked() {
        for (int i = 0; i < roadSpotNodeList.size(); i++) {
            roadSpotNodeList.get(i).setVisible(true);
        }
        roadButton.setVisible(false);
        roadButtonClose.setVisible(true);
    }

    @FXML
    public void buildRoadBtnCloseClicked() {
        for (int i = 0; i < roadSpotNodeList.size() ; i++) {
            roadSpotNodeList.get(i).setVisible(false);
        }
        roadButton.setVisible(true);
        roadButtonClose.setVisible(false);
    }

    @FXML
    public void buildSettlementBtnClicked() {
        for (int i = 0; i < vertexNodeList.size(); i++) {
            vertexNodeList.get(i).setVisible(true);
        }
        settlementButton.setVisible(false);
        settlementButtonClose.setVisible(true);
    }

    @FXML
    public void buildSettlementBtnCloseClicked() {
        for (int i = 0; i < vertexNodeList.size() ; i++) {
            vertexNodeList.get(i).setVisible(false);
        }
        settlementButton.setVisible(true);
        settlementButtonClose.setVisible(false);
    }

    @FXML
    public void upgradeSettlementBtnClicked() {
        /* TODO  show all villages that can be upgraded*/
        for (int i = 0; i < vertexNodeList.size(); i++) {
            vertexNodeList.get(i).setVisible(true);
        }
        upgradeButton.setVisible(false);
        upgradeButtonClose.setVisible(true);
    }

    @FXML
    public void upgradeSettlementBtnCloseClicked() {
        for (int i = 0; i < vertexNodeList.size(); i++) {
            vertexNodeList.get(i).setVisible(false);
        }
        upgradeButton.setVisible(true);
        upgradeButtonClose.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializePlacementSpots();
        initializeRoads();
        ArrayList<Polygon> tiles = addAllTilesToArray();
        ArrayList<Label> labels = addAllTileNumbersToArray();

        //TODO: changing the seed to the gamecode!
        long seed = CreateGameCode.randomCodeGen(6);

        RandomizeBoard.setRandomTiles(tiles, labels, seed);

        //tile1.setFill(Color.BROWN);
        initializeButtons();
    }

    private void initializePlacementSpots(){
        Collections.addAll(vertexNodeList,
                vertex1, vertex2, vertex3 ,vertex4, vertex5, vertex6, vertex7, vertex8, vertex9, vertex10,
                vertex11, vertex12, vertex13, vertex14, vertex15, vertex16, vertex17, vertex18, vertex19, vertex20,
                vertex21, vertex22, vertex23, vertex24, vertex25, vertex26, vertex27, vertex28, vertex29, vertex30,
                vertex31, vertex32, vertex33, vertex34, vertex35, vertex36, vertex37, vertex38, vertex39, vertex40,
                vertex41, vertex42, vertex43, vertex44, vertex45, vertex46, vertex47, vertex48, vertex49, vertex50,
                vertex51, vertex52, vertex53, vertex54
        );

        Collections.addAll(roadSpotNodeList,
                roadSpot1, roadSpot2, roadSpot3 ,roadSpot4, roadSpot5, roadSpot6, roadSpot7, roadSpot8, roadSpot9, roadSpot10,
                roadSpot11, roadSpot12, roadSpot13, roadSpot14, roadSpot15, roadSpot16, roadSpot17, roadSpot18, roadSpot19, roadSpot20,
                roadSpot21, roadSpot22, roadSpot23, roadSpot24, roadSpot25, roadSpot26, roadSpot27, roadSpot28, roadSpot29, roadSpot30,
                roadSpot31, roadSpot32, roadSpot33, roadSpot34, roadSpot35, roadSpot36, roadSpot37, roadSpot38, roadSpot39, roadSpot40,
                roadSpot41, roadSpot42, roadSpot43, roadSpot44, roadSpot45, roadSpot46, roadSpot47, roadSpot48, roadSpot49, roadSpot50,
                roadSpot51, roadSpot52, roadSpot53, roadSpot54, roadSpot55, roadSpot56, roadSpot57, roadSpot58, roadSpot59, roadSpot60,
                roadSpot61, roadSpot62, roadSpot63, roadSpot64, roadSpot65, roadSpot66, roadSpot67, roadSpot68, roadSpot69, roadSpot70,
                roadSpot71, roadSpot72
        );

        for (int i = 0; i < vertexNodeList.size() ; i++) {
            vertexNodeList.get(i).setVisible(false);
        }

        for (int i = 0; i < roadSpotNodeList.size(); i++) {
            roadSpotNodeList.get(i).setVisible(false);
        }
    }

    private void initializeRoads() {
        Collections.addAll(roadNodeList,
                road1, road2, road3 ,road4, road5, road6, road7, road8, road9, road10,
                road11, road12, road13, road14, road15, road16, road17, road18, road19, road20,
                road21, road22, road23, road24, road25, road26, road27, road28, road29, road30,
                road31, road32, road33, road34, road35, road36, road37, road38, road39, road40,
                road41, road42, road43, road44, road45, road46, road47, road48, road49, road50,
                road51, road52, road53, road54, road55, road56, road57, road58, road59, road60,
                road61, road62, road63, road64, road65, road66, road67, road68, road69, road70,
                road71, road72
        );

        for (int i = 0; i < roadNodeList.size(); i++) {
            roadNodeList.get(i).setVisible(false);
        }
    }

    private void initializeButtons() {
        upgradeButtonClose.setVisible(false);
        settlementButtonClose.setVisible(false);
        roadButtonClose.setVisible(false);
    }

//    private Speler getSpeler() {
//        return Speler; // Dit moet worden gewijzigd
//    }


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
