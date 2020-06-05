package org.catan.Model;

import java.util.HashMap;

public class Harbor {

    private int harborNum;
    private String type;
    private int ratio;  // ex. 1 : <2>

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

}
