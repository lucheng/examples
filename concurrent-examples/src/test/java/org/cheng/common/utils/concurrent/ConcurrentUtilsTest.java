package org.cheng.common.utils.concurrent;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ConcurrentUtilsTest {

	@Test
    public void testExeSameTask() throws Exception {
        //ConcurrentUtils.pool = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        List<Boolean> result = ConcurrentUtils.concurrentExecuteSame(
                () -> delay(1),
                () -> delay(1)
        );

        System.out.println(result);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        result = ConcurrentUtils.concurrentExecuteSame(
                () -> delay(1),
                () -> delay(1)
        );
        System.out.println(result);
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void testExeDiffTask() throws Exception {
        long start = System.currentTimeMillis();
        List<Object> result = ConcurrentUtils.concurrentExecuteDiffs(
                () -> delay(1),
                () -> delay2(1)
        );
        System.out.println(result);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        result = ConcurrentUtils.concurrentExecuteDiffs(
                () -> delay(1),
                () -> delay2(1),
                () -> delay(1)
        );
        System.out.println(result);
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void testAggregate() throws Exception {
        long start = System.currentTimeMillis();
        int result = ConcurrentUtils.concurrentExecuteSame(
                () -> delay2(1),
                () -> delay2(1),
                (r1, r2) -> r1 + r2
        );

        System.out.println(result);
        System.out.println(System.currentTimeMillis() - start);

    }

    @Test
    public void testRace() throws Exception {
        long start = System.currentTimeMillis();
        int result = ConcurrentUtils.concurrentExecuteRace(
                () -> delay2(1),
                () -> delay2(2)
        );

        System.out.println(result);
        System.out.println(System.currentTimeMillis() - start);

    }

    private boolean delay(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }

    private int delay2(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return time;
    }
}
