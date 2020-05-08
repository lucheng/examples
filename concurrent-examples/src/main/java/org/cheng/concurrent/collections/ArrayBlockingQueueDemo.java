package org.cheng.concurrent.collections;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue<Integer>(10);
        PutThread p1 = new PutThread(abq);
        GetThread g1 = new GetThread(abq);

        p1.start();
        g1.start();
    }
}
