package org.cheng.concurrent.collections;

import java.util.concurrent.BlockingQueue;

public class GetThread extends Thread {
    private BlockingQueue<Integer> abq;

    public GetThread(BlockingQueue<Integer> abq) {
        this.abq = abq;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("take " + abq.take());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
