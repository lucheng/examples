package org.cheng.concurrent.abstractqueuedsynchronizer.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("我需要等一个信号"+this);
                    condition.await();
                    System.out.println("我拿到一个信号"+this);
                } catch (Exception e) {
                    // TODO: handle exception
                } finally{
                    lock.unlock();
                }
            }
        }, "thread1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("我拿到了锁");
                    Thread.sleep(500);
                    System.out.println("我发出一个信号");
                    condition.signal();
                } catch (Exception e) {
                    // TODO: handle exception
                } finally{
                    lock.unlock();
                }


            }
        }, "thread2");

        thread1.start();
        thread2.start();
    }
}