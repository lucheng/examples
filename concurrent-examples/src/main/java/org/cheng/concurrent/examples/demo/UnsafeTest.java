package org.cheng.concurrent.examples.demo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {

    public volatile int state = 0;

    public static void main(String[] args) throws NoSuchFieldException {
        final Unsafe UNSAFE = getUnsafe();
//        long stateOffset = UNSAFE.objectFieldOffset
//                (UnsafeTest.class.getDeclaredField("state"));
//        System.out.println(stateOffset);
//        UnsafeTest unsafeTest = new UnsafeTest();
//        UNSAFE.compareAndSwapInt(unsafeTest ,stateOffset,0,2);
//        System.out.println(unsafeTest.state);
//        UNSAFE.compareAndSwapInt(unsafeTest ,stateOffset,unsafeTest.state,4);
//        System.out.println(unsafeTest.state);

        Thread currThread = Thread.currentThread();
//        UNSAFE.unpark(currThread);
//        UNSAFE.unpark(currThread);
//        UNSAFE.unpark(currThread);
//        UNSAFE.park(false, 0);
//        System.out.println("SUCCESS!!!");

//        new Thread(()->{
//            try {
//                Thread.sleep(3000);
////                currThread.interrupt();
////                UNSAFE.unpark(currThread);
//            } catch (Exception e) {}
//        }).start();

//        UNSAFE.park(false, 0);

//        System.out.println("SUCCESS!!!");

//        //相对时间后面的参数单位是纳秒
//        UNSAFE.park(false, 3000000000l);
//
//        System.out.println("SUCCESS!!!");

        long time = System.currentTimeMillis()+3000;

        //绝对时间后面的参数单位是毫秒
        UNSAFE.park(true, time);

        System.out.println("SUCCESS!!!");
    }

    public static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe)f.get(null);
        } catch (Exception e) {
            /* ... */
        }
        return null;
    }
}
