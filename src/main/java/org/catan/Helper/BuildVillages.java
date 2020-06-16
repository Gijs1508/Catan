package org.catan.Helper;

import org.catan.Model.Village;

import java.util.ArrayList;

public class BuildVillages {
    private static ArrayList<Village> buildVillages;

    // Updates the array when I new village is build
    public void setBuildVillages(ArrayList<Village> v) {
        buildVillages = v;
    }

    /**
     * Returns the build villages
     * Can be accessed in every class that needs it
     * @return an ArrayList type Village
     */
    public static ArrayList<Village> getBuildVillages() {
        return buildVillages;
    }

}
