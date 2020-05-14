package org.cheng.distributed.locks.redis;

import org.cheng.distributed.locks.Callback;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDistributedLockTemplateTest {
    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1024);
        config.setMaxIdle(10);
        config.setMaxWaitMillis(1000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

        String ip = "127.0.0.1";
        int port = 6379;
        int timeOut = 2000;
        String password = "123456";
        JedisPool jedisPool = new JedisPool(config,ip,port,timeOut,password);

        final RedisDistributedLockTemplate template = new RedisDistributedLockTemplate(jedisPool);// 本类多线程安全,可通过spring注入
        template.execute("lockid01", 10000, new Callback() {
            @Override
            public Object onGetLock() throws InterruptedException {
                // TODO 获得锁后要做的事
                System.out.println("获取了lock");
                return null;
            }

            @Override
            public Object onTimeout() throws InterruptedException {
                // TODO 获得锁超时后要做的事
                System.out.println("lock超时");
                return null;
            }
        });
        System.out.println("end");
    }
}
