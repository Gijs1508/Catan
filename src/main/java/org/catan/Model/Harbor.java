package org.catan.Model;

import java.util.HashMap;

public class Harbor {

    private String resource;
    private int ratio;  // ex. 1 : <2>
    private HashMap<String, Integer> resourceToRatio;

    public Harbor(HashMap<String, Integer> resourceToRatio) {
        this.resourceToRatio = resourceToRatio;
        this.resource = resourceToRatio.keySet().toArray()[0].toString();
        this.ratio = resourceToRatio.get(resource);
    }

    public String getResource() {
        return resource;
    }

    public int getRatio() {
        return ratio;
    }
}
