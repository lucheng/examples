package org.cheng.concurrent.collections;

import java.util.concurrent.BlockingQueue;

public class PutThread extends Thread {

    private BlockingQueue<Integer> abq;

    public PutThread(BlockingQueue<Integer> abq) {
        this.abq = abq;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("put " + i);
                abq.put(i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
