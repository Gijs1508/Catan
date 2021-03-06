package org.catan.Model;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.catan.App;

import java.util.*;

/**
 * Generates a random combination of harbors at the start of the game.
 *
 * @author Jeroen
 */

public class RandomizeHarbors {

    private static Random random = new Random(App.getCurrentGame().getCode());
    private static HashMap<String, Integer> harborTypeToCount = new HashMap<>() {{
        put("wood", 1);   put("brick", 1);
        put("ore", 1);    put("wool", 1);
        put("wheat", 1);  put("any", 4);
    }};
    private static HashMap<Integer, String> harborNumToResource = new HashMap<>();


    /** Distribute random harbor types over random harbor numbers
     * @return ArrayList of the randomized Harbor objects */
    public static ArrayList<Harbor> randomizeHarbors() {
        ArrayList<Harbor> harbors = new ArrayList<>();

        int harborNum = 1;
        for (int i = 0; i < 9; i++) {   // There are 9 harbors
            Object[] harborTypes = harborTypeToCount.keySet().toArray();
            Object harborType = harborTypes[random.nextInt(harborTypes.length)];    // Random harborType
            if (harborTypeToCount.get(harborType.toString()) > 0) {                  // The count of this harborType is > 0
                harborTypeToCount.replace(harborType.toString(), harborTypeToCount.get(harborType) - 1);
                harborNumToResource.put(harborNum, harborType.toString());          // Assigns a type to each harbor num
                harborNum++;
            } else i--;
        }

        // Create Harbor objects and add them to harbors
        List<Map.Entry<Integer, String>> randomizedList = new ArrayList<>(harborNumToResource.entrySet());
        for (Map.Entry<Integer, String> entry : randomizedList)
            harbors.add(new Harbor(entry.getKey(), entry.getValue()));

        return harbors;
    }
}
