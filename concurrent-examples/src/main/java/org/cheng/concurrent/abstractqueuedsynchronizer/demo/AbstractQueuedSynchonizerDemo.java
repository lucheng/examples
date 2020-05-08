package org.cheng.concurrent.abstractqueuedsynchronizer.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AbstractQueuedSynchonizerDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        MyThread t1 = new MyThread("t1", lock);
        MyThread t2 = new MyThread("t2", lock);
        t1.start();
        t2.start();
    }
}
