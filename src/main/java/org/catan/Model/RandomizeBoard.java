package org.catan.Model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import org.catan.App;

import java.util.*;

/**
 * Used to generate random resources and tile numbers on tiles.
 * Gets called when the game gets created.
 *
 * @Author Gijs van der Weijden
 */
public class RandomizeBoard {

    public static ArrayList<Tile> ALL_TILES = new ArrayList<Tile>();

    /**
     *  This method gets called when a new game gets created. It places
     *  Resources and numbers on the tiles.
     * @param tiles
     * @param labels
     * @param seed
     * @author Gijs
     */
    public static void setRandomTiles(ArrayList<Polygon> tiles, ArrayList<Label> labels, long seed){
        // In this arraylist all the tiles are stored containing a hashmap with (name_of_resource, img_of_resource)
        // Every time an item of this arraylist gets placed on the board the item will be removed from the ArrayList
        ArrayList<HashMap<String, Image>> tileArray = createTileArray();

        // The seed is the same as the game code, so every player gets to see the same board.
        Random randomBoard = new Random(seed);
        // Here all the tiles which have been set are getting added to the list.
        // It contains all the id's of the tiles which have been used.
        HashMap<String, String> tileHashmap = createResourcesOnTiles(randomBoard, tiles, tileArray);
        HashMap<String, Integer> labelHashmap = createNumberOnTiles(randomBoard, labels);

        addItemsToTile(tileHashmap, labelHashmap);
    }

    private static HashMap<String, String> createResourcesOnTiles(Random randomBoard, ArrayList<Polygon> tiles, ArrayList<HashMap<String, Image>> tileArray){
        HashMap<String, String> tileHashmap = new HashMap<>();
        ArrayList<String> filledTiles = new ArrayList<>();
        // These are all the different types of resources
        String[] resourceTypes = {"ore", "brick", "wheat", "wood", "wool"};

        // Set all tiles
        int filledTilesCount = 0;
        while (filledTilesCount != 18){
            for (Polygon tile : tiles) {
                Integer randint = randomBoard.nextInt(5);
                for (int i = 0; i < tileArray.size(); i++) {
                    // check if the item from the tilearray has not been removed yet and if the tile not has been filled yet.
                    if(tileArray.get(i).get(resourceTypes[randint])!= null && !checkIfTileInArray(filledTiles, tile.getId())){
                        tile.setFill(new ImagePattern(tileArray.get(i).get(resourceTypes[randint])));
                        //setTile(resourceTypes[randint], tile.getId());
                        tileHashmap.put(tile.getId(), resourceTypes[randint]);
                        filledTiles.add(tile.getId());
                        tileArray.remove(tileArray.get(i));
                        filledTilesCount += 1;
                    }
                }
            }
        }

        return tileHashmap;
    }

    private static HashMap<String, Integer> createNumberOnTiles(Random randomBoard, ArrayList<Label> labels){
        ArrayList<Integer> tileNumbers = new ArrayList<>();
        Collections.addAll(tileNumbers, 2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12);
        ArrayList<String> filledLabels = new ArrayList<>();
        HashMap<String, Integer> labelHashmap = new HashMap<>();

        // Set all labels
        int filledLabelsCount = 0;
        while(filledLabelsCount != 18){
            for(Label label : labels){
                int randInt = randomBoard.nextInt(tileNumbers.size());
                for (int i = 0; i < tileNumbers.size(); i++) {
                    if(!checkIfLabelInArray(filledLabels, label.getId())){
                        label.setText(tileNumbers.get(randInt).toString());
                        if(label.getText().equals("6") || label.getText().equals("8")){
                            label.setTextFill(Color.RED);
                        }
                        filledLabels.add(label.getId());
                        labelHashmap.put(label.getId(), tileNumbers.get(randInt));
                        tileNumbers.remove(randInt);
                        filledLabelsCount += 1;
                    }
                }
            }
        }

        return labelHashmap;
    }


    private static void addItemsToTile(HashMap<String, String> tileHashmap, HashMap<String, Integer> labelHashmap) {
        for(Map.Entry<String, String> tile : tileHashmap.entrySet()){
            for(Map.Entry<String, Integer> label : labelHashmap.entrySet()){
                String labelId = label.getKey().replace("num", "");
                if(tile.getKey().equals(labelId)){
                    Tile tileObject = new Tile(tile.getValue(), tile.getKey(), label.getValue());
                    ALL_TILES.add(tileObject);
                }
            }
        }
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

        return tileArray;
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
