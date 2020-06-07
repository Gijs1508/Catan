package org.catan.Helper;

import org.catan.Model.Village;

import java.util.ArrayList;

public class BuildVillages {
    private static ArrayList<Village> buildVillages;

    public void setBuildVillages(ArrayList<Village> v) {
        buildVillages = v;
    }

    public static ArrayList<Village> getBuildVillages() {
        return buildVillages;
    }

}
