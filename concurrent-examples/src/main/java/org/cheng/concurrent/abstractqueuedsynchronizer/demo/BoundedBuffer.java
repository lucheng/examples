package org.cheng.concurrent.abstractqueuedsynchronizer.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
    final Lock lock = new ReentrantLock();//锁对象
    final Condition notFull = lock.newCondition();//写线程条件
    final Condition notEmpty = lock.newCondition();//读线程条件

    final Object[] items = new Object[10];//缓存队列
    int putptr/*写索引*/, takeptr/*读索引*/, count/*队列中存在的数据个数*/;

    public static void main(String[] args) {
        BoundedBuffer boundedBuffer = new BoundedBuffer();

        Thread putThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<20;i++){
                    try {
                        boundedBuffer.put(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "put-thread");

        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<30;i++){
                    try {
                        Thread.sleep(1000L);
                        boundedBuffer.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "take-thread1");

        putThread.start();
        takeThread.start();
    }

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length){//如果队列满了
                System.out.println("队列已满");
                notFull.await();//阻塞写线程
            }
            System.out.println("put:" + x);
            items[putptr] = x;//赋值
            if (++putptr == items.length) putptr = 0;//如果写索引写到队列的最后一个位置了，那么置为0
            ++count;//个数++
            notEmpty.signal();//唤醒读线程
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {//如果队列为空
                System.out.println("队列已空");
                notEmpty.await();//阻塞读线程
            }
            Object x = items[takeptr];//取值
            System.out.println("take:" + x);
            if (++takeptr == items.length) takeptr = 0;//如果读索引读到队列的最后一个位置了，那么置为0
            --count;//个数--
            notFull.signal();//唤醒写线程
            return x;
        } finally {
            lock.unlock();
        }
    }
}