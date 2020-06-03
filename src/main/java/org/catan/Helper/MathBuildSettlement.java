package org.catan.Helper;

import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class MathBuildSettlement {

    // Returns the circles that are next to the circle you put in the function
    // x and y are the selected circle, ArrayList is the list from player, Type is what you want it to check road/village road/road ect
    public ArrayList<Circle> circlesInRadius(double x, double y, ArrayList<Circle> a, String type) {
        int radius = radius(type);
        ArrayList<Circle> inRadius = new ArrayList<>();
        for (Circle circle : a) {
            if (circle.getLayoutX() == x && circle.getLayoutY() == y) {
            } else {
                double distance = distance(x, y, circle.getLayoutX(), circle.getLayoutY());
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

    // Measures the distance between points
    public double distance(double xa, double ya, double xb, double yb) {
        double xTotal = Math.pow(xb - xa, 2);
        double yTotal = Math.pow(yb - ya, 2);
        return Math.sqrt(xTotal + yTotal);
    }


}
