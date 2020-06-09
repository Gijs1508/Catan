package org.catan.Controller;

//import Model.Spel;
//import Model.Spelbord;
//import Model.Speler;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import org.catan.App;
import org.catan.Model.*;
import javafx.scene.media.*;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GameSchermController implements Initializable {

    private int aantalSpelers;
//    private Spelbord spelbord;
//    private Spel spel;

    // Tiles
    @FXML private Polygon tile1; @FXML private Polygon tile2; @FXML private Polygon tile3;
    @FXML private Polygon tile4; @FXML private Polygon tile5; @FXML private Polygon tile6;
    @FXML private Polygon tile7; @FXML private Polygon tile8; @FXML private Polygon tile9;
    @FXML private Polygon tile11; @FXML private Polygon tile12; @FXML private Polygon tile13;
    @FXML private Polygon tile14; @FXML private Polygon tile15; @FXML private Polygon tile16;
    @FXML private Polygon tile17; @FXML private Polygon tile18; @FXML private Polygon tile19;
    // This is the bandit
    @FXML private Polygon tile10;
    @FXML private ImageView thief;

    @FXML private Circle thiefTile1; @FXML private Circle thiefTile2; @FXML private Circle thiefTile3;
    @FXML private Circle thiefTile4; @FXML private Circle thiefTile5; @FXML private Circle thiefTile6;
    @FXML private Circle thiefTile7; @FXML private Circle thiefTile8; @FXML private Circle thiefTile9;
    @FXML private Circle thiefTile10; @FXML private Circle thiefTile11; @FXML private Circle thiefTile12;
    @FXML private Circle thiefTile13; @FXML private Circle thiefTile14; @FXML private Circle thiefTile15;
    @FXML private Circle thiefTile16; @FXML private Circle thiefTile17; @FXML private Circle thiefTile18;
    @FXML private Circle thiefTile19;

    // Tile numbers
    @FXML private Label tile1num; @FXML private Label tile2num; @FXML private Label tile3num;
    @FXML private Label tile4num; @FXML private Label tile5num; @FXML private Label tile6num;
    @FXML private Label tile7num; @FXML private Label tile8num; @FXML private Label tile9num;
    @FXML private Label tile11num; @FXML private Label tile12num; @FXML private Label tile13num;
    @FXML private Label tile14num; @FXML private Label tile15num; @FXML private Label tile16num;
    @FXML private Label tile17num; @FXML private Label tile18num; @FXML private Label tile19num;

    @FXML private Circle vertex1; @FXML private Circle vertex2; @FXML private Circle vertex3;
    @FXML private Circle vertex4; @FXML private Circle vertex5; @FXML private Circle vertex6;
    @FXML private Circle vertex7; @FXML private Circle vertex8; @FXML private Circle vertex9;
    @FXML private Circle vertex10; @FXML private Circle vertex11; @FXML private Circle vertex12;
    @FXML private Circle vertex13; @FXML private Circle vertex14; @FXML private Circle vertex15;
    @FXML private Circle vertex16; @FXML private Circle vertex17; @FXML private Circle vertex18;
    @FXML private Circle vertex19; @FXML private Circle vertex20; @FXML private Circle vertex21;
    @FXML private Circle vertex22; @FXML private Circle vertex23; @FXML private Circle vertex24;
    @FXML private Circle vertex25; @FXML private Circle vertex26; @FXML private Circle vertex27;
    @FXML private Circle vertex28; @FXML private Circle vertex29; @FXML private Circle vertex30;
    @FXML private Circle vertex31; @FXML private Circle vertex32; @FXML private Circle vertex33;
    @FXML private Circle vertex34; @FXML private Circle vertex35; @FXML private Circle vertex36;
    @FXML private Circle vertex37; @FXML private Circle vertex38; @FXML private Circle vertex39;
    @FXML private Circle vertex40; @FXML private Circle vertex41; @FXML private Circle vertex42;
    @FXML private Circle vertex43; @FXML private Circle vertex44; @FXML private Circle vertex45;
    @FXML private Circle vertex46; @FXML private Circle vertex47; @FXML private Circle vertex48;
    @FXML private Circle vertex49; @FXML private Circle vertex50; @FXML private Circle vertex51;
    @FXML private Circle vertex52; @FXML private Circle vertex53; @FXML private Circle vertex54;

    @FXML private Circle upgrade1; @FXML private Circle upgrade2; @FXML private Circle upgrade3;
    @FXML private Circle upgrade4; @FXML private Circle upgrade5; @FXML private Circle upgrade6;
    @FXML private Circle upgrade7; @FXML private Circle upgrade8; @FXML private Circle upgrade9;
    @FXML private Circle upgrade10; @FXML private Circle upgrade11; @FXML private Circle upgrade12;
    @FXML private Circle upgrade13; @FXML private Circle upgrade14; @FXML private Circle upgrade15;
    @FXML private Circle upgrade16; @FXML private Circle upgrade17; @FXML private Circle upgrade18;
    @FXML private Circle upgrade19; @FXML private Circle upgrade20; @FXML private Circle upgrade21;
    @FXML private Circle upgrade22; @FXML private Circle upgrade23; @FXML private Circle upgrade24;
    @FXML private Circle upgrade25; @FXML private Circle upgrade26; @FXML private Circle upgrade27;
    @FXML private Circle upgrade28; @FXML private Circle upgrade29; @FXML private Circle upgrade30;
    @FXML private Circle upgrade31; @FXML private Circle upgrade32; @FXML private Circle upgrade33;
    @FXML private Circle upgrade34; @FXML private Circle upgrade35; @FXML private Circle upgrade36;
    @FXML private Circle upgrade37; @FXML private Circle upgrade38; @FXML private Circle upgrade39;
    @FXML private Circle upgrade40; @FXML private Circle upgrade41; @FXML private Circle upgrade42;
    @FXML private Circle upgrade43; @FXML private Circle upgrade44; @FXML private Circle upgrade45;
    @FXML private Circle upgrade46; @FXML private Circle upgrade47; @FXML private Circle upgrade48;
    @FXML private Circle upgrade49; @FXML private Circle upgrade50; @FXML private Circle upgrade51;
    @FXML private Circle upgrade52; @FXML private Circle upgrade53; @FXML private Circle upgrade54;

    @FXML private Circle roadSpot1; @FXML private Circle roadSpot2; @FXML private Circle roadSpot3;
    @FXML private Circle roadSpot4; @FXML private Circle roadSpot5; @FXML private Circle roadSpot6;
    @FXML private Circle roadSpot7; @FXML private Circle roadSpot8; @FXML private Circle roadSpot9;
    @FXML private Circle roadSpot10; @FXML private Circle roadSpot11; @FXML private Circle roadSpot12;
    @FXML private Circle roadSpot13; @FXML private Circle roadSpot14; @FXML private Circle roadSpot15;
    @FXML private Circle roadSpot16; @FXML private Circle roadSpot17; @FXML private Circle roadSpot18;
    @FXML private Circle roadSpot19; @FXML private Circle roadSpot20; @FXML private Circle roadSpot21;
    @FXML private Circle roadSpot22; @FXML private Circle roadSpot23; @FXML private Circle roadSpot24;
    @FXML private Circle roadSpot25; @FXML private Circle roadSpot26; @FXML private Circle roadSpot27;
    @FXML private Circle roadSpot28; @FXML private Circle roadSpot29; @FXML private Circle roadSpot30;
    @FXML private Circle roadSpot31; @FXML private Circle roadSpot32; @FXML private Circle roadSpot33;
    @FXML private Circle roadSpot34; @FXML private Circle roadSpot35; @FXML private Circle roadSpot36;
    @FXML private Circle roadSpot37; @FXML private Circle roadSpot38; @FXML private Circle roadSpot39;
    @FXML private Circle roadSpot40; @FXML private Circle roadSpot41; @FXML private Circle roadSpot42;
    @FXML private Circle roadSpot43; @FXML private Circle roadSpot44; @FXML private Circle roadSpot45;
    @FXML private Circle roadSpot46; @FXML private Circle roadSpot47; @FXML private Circle roadSpot48;
    @FXML private Circle roadSpot49; @FXML private Circle roadSpot50; @FXML private Circle roadSpot51;
    @FXML private Circle roadSpot52; @FXML private Circle roadSpot53; @FXML private Circle roadSpot54;
    @FXML private Circle roadSpot55; @FXML private Circle roadSpot56; @FXML private Circle roadSpot57;
    @FXML private Circle roadSpot58; @FXML private Circle roadSpot59; @FXML private Circle roadSpot60;
    @FXML private Circle roadSpot61; @FXML private Circle roadSpot62; @FXML private Circle roadSpot63;
    @FXML private Circle roadSpot64; @FXML private Circle roadSpot65; @FXML private Circle roadSpot66;
    @FXML private Circle roadSpot67; @FXML private Circle roadSpot68; @FXML private Circle roadSpot69;
    @FXML private Circle roadSpot70; @FXML private Circle roadSpot71; @FXML private Circle roadSpot72;

    @FXML private ImageView ship1; @FXML private ImageView ship2; @FXML private ImageView ship3;
    @FXML private ImageView ship4; @FXML private ImageView ship5; @FXML private ImageView ship6;
    @FXML private ImageView ship7; @FXML private ImageView ship8; @FXML private ImageView ship9;
    @FXML private ImageView harbor1resource; @FXML private ImageView harbor2resource;
    @FXML private ImageView harbor3resource; @FXML private ImageView harbor4resource;
    @FXML private ImageView harbor5resource; @FXML private ImageView harbor6resource;
    @FXML private ImageView harbor7resource; @FXML private ImageView harbor8resource;
    @FXML private ImageView harbor9resource;
    @FXML private Label harbor1ratio; @FXML private Label harbor2ratio; @FXML private Label harbor3ratio;
    @FXML private Label harbor4ratio; @FXML private Label harbor5ratio; @FXML private Label harbor6ratio;
    @FXML private Label harbor7ratio; @FXML private Label harbor8ratio; @FXML private Label harbor9ratio;

    @FXML private ImageView roadButton; @FXML private ImageView settlementButton;
    @FXML private ImageView upgradeButton; @FXML private ImageView roadButtonClose;
    @FXML private ImageView settlementButtonClose; @FXML private ImageView upgradeButtonClose;

    @FXML private Pane objectsPane;

    //    private Spelbord spelbord;
//    private Spel spel;
    private ArrayList<Circle> vertexNodeList = new ArrayList<>();           // Probably needs to be in a HashMap later on to connect a model with the node.
    private ArrayList<Circle> roadSpotNodeList = new ArrayList<>();
    private ArrayList<Circle> upgradeNodeList = new ArrayList<>();
    private ArrayList<Circle> thiefTileNodeList = new ArrayList<>();
    private ArrayList<Label> tileNumNodeList = new ArrayList<>();
    private ArrayList<Polygon> tileNodeList = new ArrayList<>();

    private ArrayList<Harbor> harbors = new ArrayList<>();
    private ArrayList<ImageView> ships = new ArrayList<>();

    LogController logController = LogController.getInstance();

    private BuildSettlementController build;

    private static GameSchermController gameSchermController;

    public GameSchermController(){
        gameSchermController = this;
    }

    public static GameSchermController getInstance(){
        if(gameSchermController == null){
            gameSchermController = new GameSchermController();
        }
        return gameSchermController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializePlacementSpots();
//        initializeRoads();
        addAllTilesToArray();

        //TODO: changing the seed to the gamecode!
        long seed = CreateGameCode.randomCodeGen();

        RandomizeBoard.setRandomTiles(tileNodeList, tileNumNodeList, seed);
        this.build = new BuildSettlementController(vertexNodeList, roadSpotNodeList, upgradeNodeList);
        //tile1.setFill(Color.BROWN);
        initializeButtons();

        initializeHarbors();

        highlightTiles(10);
    }

    private void initializeHarbors() {

        // Contains the ImageViews for the ships
        Collections.addAll(ships, ship1, ship2, ship3, ship4, ship5, ship6, ship7, ship8, ship9);

        // Contains the children in the harbor panes
        List<Node> harbor1children = new ArrayList<>(); List<Node> harbor2children = new ArrayList<>();
        List<Node> harbor3children = new ArrayList<>(); List<Node> harbor4children = new ArrayList<>();
        List<Node> harbor5children = new ArrayList<>(); List<Node> harbor6children = new ArrayList<>();
        List<Node> harbor7children = new ArrayList<>(); List<Node> harbor8children = new ArrayList<>();
        List<Node> harbor9children = new ArrayList<>();

        // 0 > resource (ImageView)              1 > ratio (Label)
        harbor1children.add(harbor1resource); harbor1children.add(harbor1ratio);
        harbor2children.add(harbor2resource); harbor2children.add(harbor2ratio);
        harbor3children.add(harbor3resource); harbor3children.add(harbor3ratio);
        harbor4children.add(harbor4resource); harbor4children.add(harbor4ratio);
        harbor5children.add(harbor5resource); harbor5children.add(harbor5ratio);
        harbor6children.add(harbor6resource); harbor6children.add(harbor6ratio);
        harbor7children.add(harbor7resource); harbor7children.add(harbor7ratio);
        harbor8children.add(harbor8resource); harbor8children.add(harbor8ratio);
        harbor9children.add(harbor9resource); harbor9children.add(harbor9ratio);

        // Harbor number and its children
        HashMap<Integer, List<Node>> harborToChildren = new HashMap<>() {{
            put(1, harbor1children); put(2, harbor2children);
            put(3, harbor3children); put(4, harbor4children);
            put(5, harbor5children); put(6, harbor6children);
            put(7, harbor7children); put(8, harbor8children);
            put(9, harbor9children);
        }};

        harbors = RandomizeHarbors.randomizeHarbors();

        // Updates the view with randomized harbors
        for (Harbor harbor : harbors) {
//            System.out.println(harbor.getHarborNum() + ":" + harbor.getType());

            List<Node> nodes = harborToChildren.get(harbor.getHarborNum());
            ImageView harborResource = (ImageView) nodes.get(0);
            Label harborRatio = (Label) nodes.get(1);

            harborResource.setImage(Harbor.getResourceToImage().get(harbor.getType()));
            harborRatio.setText("1 : " + harbor.getRatio());
        }

        startShipAnimation();
    }

    //placeholder
    public ArrayList<Harbor> getHarbors() {
        return harbors;
    }

    private void startShipAnimation() {
        Image shipFrame1 = new Image(String.valueOf(App.class.getResource("assets/img/ships/ship-1.png")));
        Image shipFrame2 = new Image(String.valueOf(App.class.getResource("assets/img/ships/ship-2.png")));
        Image shipFrame3 = new Image(String.valueOf(App.class.getResource("assets/img/ships/ship-3.png")));

        // Ship animation with 1 frame per second (60 ticks)
        AnimationTimer shipAnimation = new AnimationTimer() {
            int tick;
            @Override
            public void handle(long l) {
                tick++;

                if(tick % 60 == 0) {
                    for(ImageView ship : ships)
                        ship.setImage(shipFrame1); }
                if(tick % 120 == 0) {
                    for(ImageView ship : ships)
                        ship.setImage(shipFrame2); }
                if(tick % 180 == 0) {
                    for(ImageView ship : ships)
                        ship.setImage(shipFrame3); }
                if(tick % 240 == 0) {
                    for(ImageView ship : ships)
                        ship.setImage(shipFrame2); }
            }
        };
        shipAnimation.start();
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
    private void placeThief(MouseEvent mouseEvent){
        Circle circle = (Circle) mouseEvent.getSource();
        thief.setLayoutX(circle.getLayoutX() - 26);
        thief.setLayoutY(circle.getLayoutY() - 33);
        unHighlightTiles();

        String circleID = circle.getId().replaceAll("[^\\d.]", "");; //Tile string opschoonen tot alleen een getal
        int tileID;
        try {
            tileID = Integer.parseInt(circleID);
        }
        catch (NumberFormatException e)
        {
            tileID = 1;
        }
        Thief.setTile(tileID);
    }

    public void highlightTiles(int tileId) {
        System.out.println("2");
        for (Circle thiefTile : thiefTileNodeList) {
            System.out.println("in loop");
            if (!thiefTile.getId().equals("thiefTile" + tileId)) {
                System.out.println("3");
                thiefTile.setVisible(true);
            }
        }
    }

    private void unHighlightTiles() {
        for (Circle thiefTile : thiefTileNodeList) {
            thiefTile.setVisible(false);
        }
    }

    public static void startHighlightTiles(int tileId){
        System.out.println("1");
        GameSchermController game = new GameSchermController();
        game.highlightTiles(tileId);
    }

    @FXML
    private void emphasizeTile(MouseEvent mouseEvent) {
        Circle circle = (Circle) mouseEvent.getSource();
        circle.setOpacity(0.9);
        circle.setScaleX(1.1);
        circle.setScaleY(1.1);
    }

    @FXML
    private void undoEmphasizeTile(MouseEvent mouseEvent) {
        Circle circle = (Circle) mouseEvent.getSource();
        circle.setOpacity(0.7);
        circle.setScaleX(1);
        circle.setScaleY(1);
    }

    // TODO Jan
    @FXML
    public void buildSettlement(MouseEvent mouseEvent) {
        logController.logSettlementEvent();

        Circle circle = (Circle) mouseEvent.getSource(); // The vertex node that is clicked
        placeVillage(build.buildVillage(circle));
        buildSettlementBtnCloseClicked();
    }

    private void placeVillage(Village village) {
        for (int i=0; i < 54; i++) {
            if (objectsPane.getChildren().get(i).getLayoutX() == village.getX() && objectsPane.getChildren().get(i).getLayoutY() == village.getY()) {
                ImageView imageView = (ImageView) objectsPane.getChildren().get(i);
                Image image = new Image(String.valueOf(App.class.getResource(village.getImgPath())));
                imageView.setLayoutX(village.getX() - 18);
                imageView.setLayoutY(village.getY() - 20);
                imageView.setImage(image);
                break;
            }
        }
    }

    private void placeCity(Village village) {
        for (int i=0; i < 54; i++) {
            if (objectsPane.getChildren().get(i).getLayoutX() == village.getX() - 18 && objectsPane.getChildren().get(i).getLayoutY() == village.getY() - 20) {
                ImageView imageView = (ImageView) objectsPane.getChildren().get(i);
                Image image = new Image(String.valueOf(App.class.getResource(village.getImgPath())));
                imageView.setImage(image);
                break;
            }
        }
    }

    private void placeRoad(Road road) {
        for (int i=0; i < 127; i++) {
            if (objectsPane.getChildren().get(i).getLayoutX() == road.getX() && objectsPane.getChildren().get(i).getLayoutY() == road.getY()) {
                ImageView imageView = (ImageView) objectsPane.getChildren().get(i);
                Image image = new Image(String.valueOf(App.class.getResource(road.getImgPath())));
                imageView.setLayoutX(road.getX() - 3);
                imageView.setLayoutY(road.getY() - 17);
                imageView.setImage(image);
                break;
            }
        }
    }

    @FXML
    public void buildRoad(MouseEvent mouseEvent) {
        Circle circle = (Circle) mouseEvent.getSource(); // The roadSpot node that is clicked
        placeRoad(build.buildRoad(circle));
        buildRoadBtnCloseClicked();
        logController.logRoadEvent();
    }

    @FXML
    public void showVertex() {
    }

    @FXML
    public void hideVertex() {
    }

    @FXML
    public void upgradeSettlement(MouseEvent mouseEvent) {
        Circle circle = (Circle) mouseEvent.getSource(); // The upgrade node that is clicked
        placeCity(build.buildUpgrade(circle));
        upgradeSettlementBtnCloseClicked();
        logController.logUpgradeEvent();
    }

    @FXML
    public void endTurn() {
        Sound.playEndTurnJingle();

        logController.logEndTurnEvent();
        TurnManager.nextTurn(Player.getActivePlayer());
        //TODO
    }

    @FXML // When you hover over a circle when road is selected
    public void emphasizeRoad(MouseEvent mouseEvent) {
        Circle circle = (Circle) mouseEvent.getSource();
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
        // todo Placeholder for building next to a harbor
        BuildSettlementController.getInstance().updatePlayerFromHarbor(harbors.get(ThreadLocalRandom.current().nextInt(0, 8 + 1)));

        Sound.playClick();

        try {
            ArrayList<Circle> nodes = build.showRoadSpots();
            for (Circle node : nodes) {
                node.setVisible(true);
            }
        } catch (Exception e){
        }
        roadButton.setVisible(false);
        roadButtonClose.setVisible(true);
    }

    @FXML
    public void buildRoadBtnCloseClicked() {
        Sound.playClick();

        for (Circle circle : roadSpotNodeList) {
            circle.setVisible(false);
        }
        roadButton.setVisible(true);
        roadButtonClose.setVisible(false);
    }

    @FXML
    public void buildSettlementBtnClicked() {
        Sound.playClick();

        try {
            ArrayList<Circle> availableSpots = build.showVillageSpots();
            for (Circle availableSpot : availableSpots) {
                availableSpot.setVisible(true);
            }
        } catch (Exception e) {
        }
        settlementButton.setVisible(false);
        settlementButtonClose.setVisible(true);
    }

    @FXML
    public void buildSettlementBtnCloseClicked() {
        Sound.playClick();

        for (Circle circle : vertexNodeList) {
            circle.setVisible(false);
        }
        settlementButton.setVisible(true);
        settlementButtonClose.setVisible(false);
    }

    @FXML
    public void upgradeSettlementBtnClicked() {
        Sound.playClick();

        try {
            ArrayList<Circle> nodes = build.showUpgradeableVillages();
            for (Circle node : nodes) {
                node.setVisible(true);
            }

        } catch (Exception e) {
        }
        upgradeButton.setVisible(false);
        upgradeButtonClose.setVisible(true);
    }

    @FXML
    public void upgradeSettlementBtnCloseClicked() {
        Sound.playClick();

        for (Circle circle : upgradeNodeList) {
            circle.setVisible(false);
        }
        upgradeButton.setVisible(true);
        upgradeButtonClose.setVisible(false);
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

        Collections.addAll(upgradeNodeList,
                upgrade1, upgrade2, upgrade3 ,upgrade4, upgrade5, upgrade6, upgrade7, upgrade8, upgrade9, upgrade10,
                upgrade11, upgrade12, upgrade13, upgrade14, upgrade15, upgrade16, upgrade17, upgrade18, upgrade19, upgrade20,
                upgrade21, upgrade22, upgrade23, upgrade24, upgrade25, upgrade26, upgrade27, upgrade28, upgrade29, upgrade30,
                upgrade31, upgrade32, upgrade33, upgrade34, upgrade35, upgrade36, upgrade37, upgrade38, upgrade39, upgrade40,
                upgrade41, upgrade42, upgrade43, upgrade44, upgrade45, upgrade46, upgrade47, upgrade48, upgrade49, upgrade50,
                upgrade51, upgrade52, upgrade53, upgrade54
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

        Collections.addAll(thiefTileNodeList,
                thiefTile1, thiefTile2, thiefTile3, thiefTile4, thiefTile5, thiefTile6, thiefTile7, thiefTile8,
                thiefTile9, thiefTile10, thiefTile11, thiefTile12, thiefTile13, thiefTile14, thiefTile15,
                thiefTile16, thiefTile17, thiefTile18, thiefTile19);

        for (Circle item : vertexNodeList) {
            item.setVisible(false);
        }

        for (Circle value : upgradeNodeList) {
            value.setVisible(false);
        }

        for (Circle circle : roadSpotNodeList) {
            circle.setVisible(false);
        }

        for (Circle circle : thiefTileNodeList) {
            circle.setVisible(false);
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


    private void addAllTilesToArray() {

        Collections.addAll(tileNodeList, tile1, tile2, tile3, tile4, tile5,
                tile6, tile7, tile8, tile9, tile11, tile12, tile13,
                tile14, tile15,tile16, tile17, tile18, tile19);

        Collections.addAll(tileNumNodeList, tile1num, tile2num, tile3num, tile4num,
                tile5num, tile6num, tile7num, tile8num, tile9num,
                tile11num, tile12num, tile13num, tile14num, tile15num,
                tile16num, tile17num, tile18num, tile19num);
    }

//    private Speler getSpeler() {
//        return Speler; // Dit moet worden gewijzigd
//    }
}
