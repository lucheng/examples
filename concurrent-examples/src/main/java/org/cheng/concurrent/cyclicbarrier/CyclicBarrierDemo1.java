package org.cheng.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo1 {
    private static int SIZE = 3;
    private static CyclicBarrier cb;

    public static void main(String[] args) {

        cb = new CyclicBarrier(SIZE);

        // 新建3个任务
        for (int i = 0; i < SIZE; i++)
            new InnerThread("thread" + i, cb).start();
    }
}

class InnerThread extends Thread {
    private CyclicBarrier cb;

    public InnerThread(String name, CyclicBarrier cb) {
        super(name);
        this.cb = cb;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " wait for CyclicBarrier.");

            // 将cb的参与者数量加1
            cb.await();

            // cb的参与者数量等于3时，才继续往后执行
            System.out.println(Thread.currentThread().getName() + " continued.");
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
