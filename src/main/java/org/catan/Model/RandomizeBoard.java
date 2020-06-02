package org.catan.Model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import org.catan.App;

import java.util.ArrayList;
import java.util.Random;

public class RandomizeBoard {
    //TODO: add  the gamecode as parameter
    public static void setRandomTiles(ArrayList<Polygon> tiles, ArrayList<Label> labels){
        // Image sources
        Image ore_img = new Image(String.valueOf(App.class.getResource("assets/img/tiles/ore.png")));
        Image brick_img = new Image(String.valueOf(App.class.getResource("assets/img/tiles/brick.png")));
        Image wheat_img = new Image(String.valueOf(App.class.getResource("assets/img/tiles/wheat.png")));
        Image wood_img = new Image(String.valueOf(App.class.getResource("assets/img/tiles/wood.png")));
        Image wool_img = new Image(String.valueOf(App.class.getResource("assets/img/tiles/wool.png")));

        // Image source array
        Image[] images = {ore_img, brick_img, wheat_img, wood_img, wool_img};

        //TODO: changing the seed to the gamecode!
        long seed = 364494L;
        Random randomBoard = new Random(seed);


        for (Polygon tile : tiles) {
            Integer randint = randomBoard.nextInt(5);
            tile.setFill(new ImagePattern(images[randint]));
        }

        for(Label label : labels){
            Integer randint = randomBoard.nextInt(12);
            label.setText(randint.toString());
        }

    }
}
