package org.catan.Controller;

import java.util.*;

public class ThiefController {

    private void keyHandler() {
    }

    public static List<Integer> placeThief(String PolygonID) {
        HashMap<String, List<Integer>> idAndCoordinates = new HashMap<>();
        Integer[] coordinate1 = {238, 66};
        Integer[] coordinate2 = {238, 66};
        Integer[] coordinate3 = {238, 66};
        Integer[] coordinate4 = {238, 66};
        Integer[] coordinate5 = {238, 66};
        Integer[] coordinate6 = {666, 66};
        Integer[] coordinate7 = {238, 66};
        Integer[] coordinate8 = {238, 66};
        Integer[] coordinate9 = {238, 66};
        Integer[] coordinate10 = {238, 66};
        Integer[] coordinate11 = {238, 66};
        Integer[] coordinate12 = {238, 66};
        Integer[] coordinate13 = {238, 66};
        Integer[] coordinate14 = {238, 66};
        Integer[] coordinate15 = {238, 66};
        Integer[] coordinate16 = {238, 66};
        Integer[] coordinate17 = {238, 66};
        Integer[] coordinate18 = {238, 66};
        Integer[] coordinate19 = {238, 66};

        idAndCoordinates.put("tile1", Arrays.asList(coordinate1));
        idAndCoordinates.put("tile2", Arrays.asList(coordinate2));
        idAndCoordinates.put("tile3", Arrays.asList(coordinate3));
        idAndCoordinates.put("tile4", Arrays.asList(coordinate4));
        idAndCoordinates.put("tile5", Arrays.asList(coordinate5));
        idAndCoordinates.put("tile6", Arrays.asList(coordinate6));
        idAndCoordinates.put("tile7", Arrays.asList(coordinate7));
        idAndCoordinates.put("tile8", Arrays.asList(coordinate8));
        idAndCoordinates.put("tile9", Arrays.asList(coordinate9));
        idAndCoordinates.put("tile10", Arrays.asList(coordinate10));
        idAndCoordinates.put("tile11", Arrays.asList(coordinate11));
        idAndCoordinates.put("tile12", Arrays.asList(coordinate12));
        idAndCoordinates.put("tile13", Arrays.asList(coordinate13));
        idAndCoordinates.put("tile14", Arrays.asList(coordinate14));
        idAndCoordinates.put("tile15", Arrays.asList(coordinate15));
        idAndCoordinates.put("tile16", Arrays.asList(coordinate16));
        idAndCoordinates.put("tile17", Arrays.asList(coordinate17));
        idAndCoordinates.put("tile18", Arrays.asList(coordinate18));
        idAndCoordinates.put("tile19", Arrays.asList(coordinate19));

        return idAndCoordinates.get("tile6");
    }

}
