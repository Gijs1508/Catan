package org.catan.Controller;

import java.util.*;

public class ThiefController {

    private void keyHandler() {
    }

    public static List<Integer> placeThief(String PolygonID) {
        HashMap<String, List<Integer>> idAndCoordinates = new HashMap<>();
        Integer[] coordinate1 = {238, 66};
        Integer[] coordinate2 = {341, 66};
        Integer[] coordinate3 = {445, 66};
        Integer[] coordinate4 = {187, 147};
        Integer[] coordinate5 = {290, 147};
        Integer[] coordinate6 = {392, 147};
        Integer[] coordinate7 = {495, 147};
        Integer[] coordinate8 = {137, 229};
        Integer[] coordinate9 = {240, 229};
        Integer[] coordinate10 = {327, 211};
        Integer[] coordinate11 = {444, 229};
        Integer[] coordinate12 = {546, 229};
        Integer[] coordinate13 = {187, 310};
        Integer[] coordinate14 = {289, 310};
        Integer[] coordinate15 = {392, 310};
        Integer[] coordinate16 = {495, 310};
        Integer[] coordinate17 = {239, 392};
        Integer[] coordinate18 = {341, 392};
        Integer[] coordinate19 = {445, 392};

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

        return idAndCoordinates.get(PolygonID);
    }

}
