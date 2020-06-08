package org.catan.Controller;

import java.util.*;

public class ThiefController {
    private static int currentPosition = 10;

    private void keyHandler() {
    }

    public static boolean checkThiefPosition(int PolygonID){
        if (PolygonID == currentPosition){
            return false;  //if you can NOT place the thief on this position
        } else {
            return  true;  //if you CAN place the thief on this position
        }
    }

    public static List<Integer> placeThief(int PolygonID) {
        HashMap<Integer, List<Integer>> idAndCoordinates = new HashMap<>();
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

        idAndCoordinates.put(1, Arrays.asList(coordinate1));
        idAndCoordinates.put(2, Arrays.asList(coordinate2));
        idAndCoordinates.put(3, Arrays.asList(coordinate3));
        idAndCoordinates.put(4, Arrays.asList(coordinate4));
        idAndCoordinates.put(5, Arrays.asList(coordinate5));
        idAndCoordinates.put(6, Arrays.asList(coordinate6));
        idAndCoordinates.put(7, Arrays.asList(coordinate7));
        idAndCoordinates.put(8, Arrays.asList(coordinate8));
        idAndCoordinates.put(9, Arrays.asList(coordinate9));
        idAndCoordinates.put(10, Arrays.asList(coordinate10));
        idAndCoordinates.put(11, Arrays.asList(coordinate11));
        idAndCoordinates.put(12, Arrays.asList(coordinate12));
        idAndCoordinates.put(13, Arrays.asList(coordinate13));
        idAndCoordinates.put(14, Arrays.asList(coordinate14));
        idAndCoordinates.put(15, Arrays.asList(coordinate15));
        idAndCoordinates.put(16, Arrays.asList(coordinate16));
        idAndCoordinates.put(17, Arrays.asList(coordinate17));
        idAndCoordinates.put(18, Arrays.asList(coordinate18));
        idAndCoordinates.put(19, Arrays.asList(coordinate19));

        currentPosition = PolygonID;
        return idAndCoordinates.get(PolygonID);
    }

}
