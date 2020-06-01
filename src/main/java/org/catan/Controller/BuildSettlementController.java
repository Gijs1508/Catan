package org.catan.Controller;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import java.util.ArrayList;

public class BuildSettlementController {
//    private Speler player;
    private ArrayList<Circle> VertexNodeList = new ArrayList<>();
    private ArrayList<Circle> RoadSpotNodeList = new ArrayList<>();
    private ArrayList<ImageView> RoadNodeList = new ArrayList<>();

    public BuildSettlementController(ArrayList<Circle> vertexNodeList, ArrayList<Circle> roadSpotNodeList, ArrayList<ImageView> roadNodeList) {
        VertexNodeList = vertexNodeList;
        RoadSpotNodeList = roadSpotNodeList;
        RoadNodeList = roadNodeList;

        ArrayList<Circle> testArray = circlesInRadius(201.0,52.0, vertexNodeList, "huis");
        printArray(RoadSpotNodeList);
    }

    private void printArray(ArrayList<Circle> array) {
        for (Circle circle : array) {
            System.out.println("This is X: " + circle.getLayoutX());
            System.out.println("This is Y: " + circle.getLayoutY());
        }
    }

    // Returns the circles that are next to the circle you put in the function
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
        return inRadius;
    }

    // Returns the correct radius for that type
    private int radius(String type) {
        if (type.equals("Road") || type.equals("road"))
            return 63;
        else if (type.equals("Village") || type.equals("village"))
            return 69;
        else {
            return 41;
        }
    }

}
