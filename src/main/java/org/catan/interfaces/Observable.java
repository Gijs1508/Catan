package org.catan.interfaces;

import org.catan.Model.Game;

import java.io.IOException;

public interface Observable {
    void update(Game game) throws IOException;
}