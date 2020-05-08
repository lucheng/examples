package org.cheng.concurrent.abstractqueuedsynchronizer.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionCommuniction {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    business.sub(i);
                }
            }
        }).start();

        for (int i = 0; i < 50; i++) {
            business.main(i);
        }
    }


    static class Business{
        private Lock lock = new ReentrantLock();
        private boolean isMain = true;
        private Condition condition = lock.newCondition();
        public void main(int i){
            lock.lock();
            try {
                if(!isMain){
                    condition.await();
                }
                System.out.println("main is looping  : " + i);
                isMain = false;
                condition.signal();
            } catch (Exception e) {
                // TODO: handle exception
            } finally{
                lock.unlock();
            }
        }

        public void sub(int i){
            lock.lock();
            try {
                if(isMain){
                    condition.await();
                }
                System.out.println("sub is looping  :" + i);
                isMain = true;
                condition.signal();
            } catch (Exception e) {
                // TODO: handle exception
            } finally{
                lock.unlock();
            }
        }
    }
}