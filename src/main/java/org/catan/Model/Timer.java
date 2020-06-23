package org.catan.Model;

import org.catan.App;
import org.catan.logic.DatabaseConnector;

/**
 * Timer to ensure trade offers won't softlock the game for whatever reason, resets the trade status after 25 seconds
 * @author Kaz
 */

public class Timer extends Thread{

    private Thread t;

    public Timer(){
        start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(25000); // 25 seconds
            if(App.getCurrentGame().getTradeStatus().equals("pending")){
                App.getCurrentGame().setTradeStatus("timeout");
                DatabaseConnector.getInstance().updateGame(App.getCurrentGame());
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }
}
