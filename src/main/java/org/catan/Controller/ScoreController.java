package org.catan.Controller;

//import model.Speler;

import org.catan.Model.Game;
import org.catan.interfaces.Observable;

public class ScoreController implements Observable {
//    private Speler speler;
    private int score;

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateScore() {

    }

    @Override
    public void update(Game game) {

    }
}
