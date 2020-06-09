package org.catan.Model;

import javafx.scene.image.Image;
import org.catan.App;

import java.util.HashMap;

/**
 * Harbor model that contains all required information about a single harbor.
 *
 * @author Jeroen
 * @version 0.5
 */

public class Harbor {

    private int harborNum;
    private String type;
    private int ratio;  // ex. 1 : <2>

    private static HashMap<String, Image> resourceToImage = new HashMap<>() {{
        put("wood", new Image(String.valueOf(App.class.getResource("assets/img/woodHarbor.png"))));
        put("brick", new Image(String.valueOf(App.class.getResource("assets/img/brickHarbor.png"))));
        put("ore", new Image(String.valueOf(App.class.getResource("assets/img/oreHarbor.png"))));
        put("wool", new Image(String.valueOf(App.class.getResource("assets/img/sheepHarbor.png"))));
        put("wheat", new Image(String.valueOf(App.class.getResource("assets/img/wheatHarbor.png"))));
        put("any", new Image(String.valueOf(App.class.getResource("assets/img/anyHarbor.png"))));
    }};

    public Harbor(int harborNum, String type) {
        this.harborNum = harborNum;
        this.type = type;

        findRatio();
    }

    private void findRatio() {
        if (type.equals("any")) {
            ratio = 3;
            return; }
        ratio = 2;
    }

    public int getRatio() {
        return ratio;
    }

    public String getType() {
        return type;
    }

    public int getHarborNum() {
        return harborNum;
    }

    public static HashMap<String, Image> getResourceToImage() {
        return resourceToImage;
    }

}
