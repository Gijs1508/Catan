package org.catan.Model;

import java.io.* ;

/**
 * Class that creates a thread when the user sends a message.
 * It starts a timeout so the user can not keep spamming messages.
 *
 * @author Gijs
 */

public class ChatThread implements Runnable  {
    private String threadName;
    private int timeout;
    Thread t;

    public ChatThread(String threadName, int timeout){
        this.threadName = threadName;
        this.timeout = timeout;
        t = new Thread(this, threadName);
        t.start();
    }

    public void run() {
        try {
            System.out.println(this.threadName);
            Thread.sleep(this.timeout);
        }catch (InterruptedException e) {
            System.out.println(this.threadName + "Interrupted");
        }
        System.out.println(this.threadName + " exiting.");
    }

    public boolean timer(int timeout) throws InterruptedException {
        Thread.sleep(timeout);
        return true;
    }
}
