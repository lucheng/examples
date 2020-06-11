package org.cheng.common.utils;

import java.time.Instant;

/**
 * Created by whf on 8/5/16.
 */
public class TimeUtils {
    private TimeUtils() {
    }

    /**
     * 给定一个时间戳, 判断时间戳是否超时
     * @param timestamp
     * @param interval 超时时间, 秒
     * @return 超时返回true
     */
    public static boolean isIntervalMoreThan(long timestamp, int interval) {
        Instant now = Instant.now();
        long nowTimeStamp = now.getEpochSecond();

        return nowTimeStamp - timestamp >= interval;
    }
}
