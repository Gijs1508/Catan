package org.catan.Helper;

import javafx.scene.shape.Circle;

import java.util.ArrayList;

/**
 * This controller calculates distances and what nodes are close to eachother
 * This is used in the BuildSettlementController
 * @author Jan
 */
public class MathBuildSettlement {

    /**
     * Returns the nodes that are next to the circle you put in the function
     * @param x coordinate from your node
     * @param y coordinate from your node
     * @param a the ArrayList with nodes you want to see if it is close to
     * @param type the nodes you want to check (close to roads or villages)
     * @return an ArrayList with all the nodes that are close
     */
    public ArrayList<Circle> circlesInRadius(double x, double y, ArrayList<Circle> a, String type) {
        int radius = radius(type);
        ArrayList<Circle> inRadius = new ArrayList<>();
        for (Circle circle : a) {
            if (circle.getLayoutX() != x || circle.getLayoutY() != y) {
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
            return 45; // Road next to village and visa versa
        }
    }

    // Measures the distance between points

    /**
     * Measures the distance between points
     * @return The distance
     */
    public double distance(double xa, double ya, double xb, double yb) {
        double xTotal = Math.pow(xb - xa, 2);
        double yTotal = Math.pow(yb - ya, 2);
        return Math.sqrt(xTotal + yTotal);
    }


}
