package org.cheng.threadpool.examples;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABATest {

    private static AtomicInteger atomicInt = new AtomicInteger(100);

    private static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<Integer>(100, 0);

    public static void main(String[] args) throws InterruptedException {

       Thread intT1 = new Thread(new Runnable() {

           @Override
           public void run() {
        	   System.out.println(atomicInt+ " 1- " + atomicInt.compareAndSet(100, 101));
        	   System.out.println(atomicInt+ " 2- " + atomicInt.compareAndSet(101, 100));
        	   System.out.println(atomicInt);
           }

       });

       Thread intT2 = new Thread(new Runnable() {

           @Override

           public void run() {
              try {
                  TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {

              }
              System.out.println(atomicInt);
              boolean c3 = atomicInt.compareAndSet(100, 101);
              System.out.println(atomicInt.get() + " " + c3); // true
           }
       });

       intT1.start();
       intT2.start();
       intT1.join();
       intT2.join();
       Thread refT1 = new Thread(new Runnable() {

           @Override
           public void run(){
              try {
                  TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
              }
              atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
              atomicStampedRef.compareAndSet(101, 100, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
           }

       });

       Thread refT2 = new Thread(new Runnable() {

           @Override
           public void run() {

              int stamp = atomicStampedRef.getStamp();

              try {
                  TimeUnit.SECONDS.sleep(2);
              } catch (InterruptedException e) {
              }
              boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
              
              System.out.println(atomicStampedRef.getReference() + " "+ c3); // false
              stamp = atomicStampedRef.getStamp();
              boolean c4 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
              
              System.out.println(atomicStampedRef.getReference() + " "+ c4); // false
           }
       });
 
       refT1.start();
       refT2.start();
    }
}