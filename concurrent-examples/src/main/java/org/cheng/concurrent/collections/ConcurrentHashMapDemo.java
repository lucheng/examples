package org.cheng.concurrent.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;
import java.util.Collections;
import java.util.Hashtable;

public class ConcurrentHashMapDemo {
    static final int THREADNUMBER = 50;
    static final int NUMBER = 5000;

    public static void main(String[] args) throws Exception {
        Map<String, Integer> hashmapSync = Collections
                .synchronizedMap(new HashMap<String, Integer>());
        Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<String, Integer>();
        Map<String, Integer> hashtable = new Hashtable<String, Integer>();
        Map<String, Integer> concurrentSkipListMap = new ConcurrentSkipListMap<String, Integer>();
        long totalA = 0L;
        long totalB = 0L;
        long totalC = 0L;
        long totalD = 0L;
        for (int i = 0; i <= 100; i++) {
            totalA += put(hashmapSync);
            totalB += put(concurrentHashMap);
            totalC += put(hashtable);
            totalD += put(concurrentSkipListMap);
        }
        System.out.println("put time HashMapSync = " + totalA + "ms.");
        System.out.println("put time ConcurrentHashMap = " + totalB + "ms.");
        System.out.println("put time Hashtable = " + totalC + "ms.");
        System.out.println("put time ConcurrentSkipListMap = " + totalD + "ms.");
        totalA = 0;
        totalB = 0;
        totalC = 0;
        totalD = 0;
        for (int i = 0; i <= 100; i++) {
            totalA += get(hashmapSync);
            totalB += get(concurrentHashMap);
            totalC += get(hashtable);
            totalD += get(concurrentSkipListMap);
        }
        System.out.println("get time HashMapSync=" + totalA + "ms.");
        System.out.println("get time ConcurrentHashMap=" + totalB + "ms.");
        System.out.println("get time Hashtable=" + totalC + "ms.");
        System.out.println("get time ConcurrentSkipListMap=" + totalD + "ms.");
    }

    public static long put(Map<String, Integer> map) throws Exception {
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(THREADNUMBER);
        for (int i = 0; i < THREADNUMBER; i++) {
            new PutThread(map, countDownLatch).start();
        }
        countDownLatch.await();
        return System.currentTimeMillis() - start;
    }

    public static long get(Map<String, Integer> map) throws Exception {
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(THREADNUMBER);
        for (int i = 0; i < THREADNUMBER; i++) {
            new GetThread(map, countDownLatch).start();
        }
        countDownLatch.await();
        return System.currentTimeMillis() - start;
    }


    private static class PutThread extends Thread {
        private Map<String, Integer> map;
        private CountDownLatch countDownLatch;
        private String key = this.getId() + "";

        PutThread(Map<String, Integer> map, CountDownLatch countDownLatch) {
            this.map = map;
            this.countDownLatch = countDownLatch;
        }

        public void run() {
            for (int i = 1; i <= ConcurrentHashMapDemo.NUMBER; i++) {
                map.put(key, i);
            }
            countDownLatch.countDown();
        }
    }

    private static class GetThread extends Thread {
        private Map<String, Integer> map;
        private CountDownLatch countDownLatch;
        private String key = this.getId() + "";

        GetThread(Map<String, Integer> map, CountDownLatch countDownLatch) {
            this.map = map;
            this.countDownLatch = countDownLatch;
        }

        public void run() {
            for (int i = 1; i <= ConcurrentHashMapDemo.NUMBER; i++) {
                map.get(key);
            }
            countDownLatch.countDown();
        }
    }
}
