package org.catan.Model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import org.catan.App;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class RandomizeBoard {

    public static ArrayList<Tile> ALL_TILES = new ArrayList<Tile>();
    //TODO: add  the gamecode as parameter
    public static void setRandomTiles(ArrayList<Polygon> tiles, ArrayList<Label> labels){
        // In this arraylist all the tiles are stored containing a hashmap with (name_of_resource, img_of_resource)
        // Every time an item of this arraylist gets placed on the board the item will be removed from the ArrayList
        ArrayList<HashMap<String, Image>> tileArray = createTileArray();
        
        // These are all the different types of resources
        String[] resourceTypes = {"ore", "brick", "wheat", "wood", "wool"};

        //TODO: changing the seed to the gamecode!
        long seed = 689374L;
        // The seed is the same as the game code, so every player gets to see the same board.
        Random randomBoard = new Random(seed);
        // Here all the tiles which have been set are getting added to the list.
        // It contains all the id's of the tiles which have been used.
        ArrayList<String> filledTiles = new ArrayList<>();

        ArrayList<Integer> tileNumbers = new ArrayList<>();
        Collections.addAll(tileNumbers, 2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12);
        ArrayList<String> filledLabels = new ArrayList<>();

        // Set all tiles
        int filledTilesCount = 0;
        while (filledTilesCount != 18){
            for (Polygon tile : tiles) {
                Integer randint = randomBoard.nextInt(5);
                for (int i = 0; i < tileArray.size(); i++) {
                    // check if the item from the tilearray has not been removed yet and if the tile not has been filled yet.
                    if(tileArray.get(i).get(resourceTypes[randint])!= null && !checkIfTileInArray(filledTiles, tile.getId())){
                        tile.setFill(new ImagePattern(tileArray.get(i).get(resourceTypes[randint])));
                        setTile(resourceTypes[randint], tile.getId());
                        filledTiles.add(tile.getId());
                        tileArray.remove(tileArray.get(i));
                        filledTilesCount += 1;
                    }
                }
            }
        }

        // Set all labels
        int filledLabelsCount = 0;
        while(filledLabelsCount != 18){
            for(Label label : labels){
                int randInt = randomBoard.nextInt(tileNumbers.size());
                for (int i = 0; i < tileNumbers.size(); i++) {
                    if(!checkIfLabelInArray(filledLabels, label.getId())){
                        System.out.println(tileNumbers);
                        label.setText(tileNumbers.get(randInt).toString());
                        if(label.getText().equals("6") || label.getText().equals("8")){
                            label.setTextFill(Color.RED);
                        }
                        filledLabels.add(label.getId());
                        
                        tileNumbers.remove(randInt);
                        filledLabelsCount += 1;
                    }
                }
            }
        }

        // Test loop to check if the tile objects are created
//        for (Tile allTile : ALL_TILES) {
//            System.out.println(allTile.getType());
//        }

    }

    private static void setTile(String type, String id){
        Tile tile = new Tile(type, id);
        ALL_TILES.add(tile);
    }

    public static ArrayList<Tile> getTiles(){
        return ALL_TILES;
    }

    private static ArrayList<HashMap<String, Image>> createTileArray(){
        ArrayList<HashMap<String, Image>> tileArray = new ArrayList<HashMap<String, Image>>();
        // Image sources
        Image ore_img = new Image(String.valueOf(App.class.getResource("assets/img/tiles/ore.png")));
        Image brick_img = new Image(String.valueOf(App.class.getResource("assets/img/tiles/brick.png")));
        Image wheat_img = new Image(String.valueOf(App.class.getResource("assets/img/tiles/wheat.png")));
        Image wood_img = new Image(String.valueOf(App.class.getResource("assets/img/tiles/wood.png")));
        Image wool_img = new Image(String.valueOf(App.class.getResource("assets/img/tiles/wool.png")));

        // Add ore to tile array
        for (int i = 0; i < 3; i++) {
            HashMap<String, Image> tempHash = new HashMap<>();
            tempHash.put("ore", ore_img);
            tileArray.add(tempHash);
        }
        // Add bricks to tile array
        for (int i = 0; i < 3; i++) {
            HashMap<String, Image> tempHash = new HashMap<>();
            tempHash.put("brick", brick_img);
            tileArray.add(tempHash);
        }
        // Add wheat to tile array
        for (int i = 0; i < 4; i++) {
            HashMap<String, Image> tempHash = new HashMap<>();
            tempHash.put("wheat", wheat_img);
            tileArray.add(tempHash);
        }
        // Add wood to tile array
        for (int i = 0; i < 4; i++) {
            HashMap<String, Image> tempHash = new HashMap<>();
            tempHash.put("wood", wood_img);
            tileArray.add(tempHash);
        }
        // Add wool to tile array
        for (int i = 0; i < 4; i++) {
            HashMap<String, Image> tempHash = new HashMap<>();
            tempHash.put("wool", wool_img);
            tileArray.add(tempHash);
        }
        //printAll(tileArray);

        return tileArray;
    }

    private static void printAll(ArrayList<HashMap<String, Image>> tileArray){
        //System.out.println("hoi");
        for (HashMap<String, Image> stringImageHashMap : tileArray) {
            System.out.println(stringImageHashMap.keySet());
        }
        System.out.println("end");
    }

    private static boolean checkIfTileInArray(ArrayList<String> tiles, String tile){
        boolean isInArray = false;

        for (String s : tiles) {
            if (s.equals(tile)) {
                isInArray = true;
                break;
            }
        }

        return isInArray;
    }

    private static boolean checkIfLabelInArray(ArrayList<String> labels, String label){
        boolean isInArray = false;

        for (String s : labels) {
            if (s.equals(label)) {
                isInArray = true;
                break;
            }
        }

        return isInArray;
    }
}
