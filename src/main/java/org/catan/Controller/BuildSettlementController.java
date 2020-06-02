package org.catan.Controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import java.util.ArrayList;

public class BuildSettlementController {
//    private Speler player;
    private ArrayList<Circle> VertexNodeList = new ArrayList<>();
    private ArrayList<Circle> RoadSpotNodeList = new ArrayList<>();
    private ArrayList<ImageView> RoadNodeList = new ArrayList<>();

    @FXML private Pane objectsPane;

    public BuildSettlementController(ArrayList<Circle> vertexNodeList, ArrayList<Circle> roadSpotNodeList, ArrayList<ImageView> roadNodeList) {
        VertexNodeList = vertexNodeList;
        RoadSpotNodeList = roadSpotNodeList;
        RoadNodeList = roadNodeList;
    }

    // Returns the circles that are next to the circle you put in the function
    // x and y are the selected circle, ArrayList is the list from player, Type is what you want it to check road/village road/road ect
    private ArrayList<Circle> circlesInRadius(double x, double y, ArrayList<Circle> a, String type) {
        int radius = radius(type);
        ArrayList<Circle> inRadius = new ArrayList<>();
        for (Circle circle : a) {
            if (circle.getLayoutX() == x && circle.getLayoutY() == y) {
            } else {
                double xTotal = Math.pow(x - circle.getLayoutX(), 2);
                double yTotal = Math.pow(y - circle.getLayoutY(), 2);
                double distance = Math.sqrt(xTotal + yTotal);
                if (distance <= radius) {
                    inRadius.add(circle);
                }
            }
        }
        return inRadius; // Nodes that are in the radius
    }

    // Returns the correct radius for that type
    private int radius(String type) {
        if (type.equals("Road") || type.equals("road")) // Road next to road
            return 63;
        else if (type.equals("Village") || type.equals("village")) // Village next to village
            return 69;
        else {
            return 41; // Road next to village and visa versa
        }
    }

    public void buildVillage(double x, double y) {

//        objectsPane.getChildren().add()
    }


}
