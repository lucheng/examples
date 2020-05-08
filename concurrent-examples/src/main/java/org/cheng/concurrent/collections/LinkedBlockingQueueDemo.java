package org.cheng.concurrent.collections;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> abq = new LinkedBlockingQueue<Integer>(10);
        PutThread p1 = new PutThread(abq);
        GetThread g1 = new GetThread(abq);
        p1.start();
        g1.start();
    }
}
