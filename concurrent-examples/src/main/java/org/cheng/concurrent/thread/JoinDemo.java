package org.cheng.concurrent.thread;

public class JoinDemo {

    private final static class Child extends Thread {

        public Child(String name){
            super(name);
        }

        public void run() {
           System.out.println(Thread.currentThread().getName() +  " running");
            try {
                sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建child对象，此时child表示的线程处于NEW状态
        Child child = new Child("t1");
        // child表示的线程转换为RUNNABLE状态
        child.start();

        System.out.println(Thread.currentThread().getName() + " wait");
        // 等待child线程运行完再继续运行
        child.join();

        System.out.println(Thread.currentThread().getName() + " finish");
    }
}
