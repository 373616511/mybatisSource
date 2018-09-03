package com.asyf.demo.redis;

import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.UUID;

public class DoMain extends Thread {

    private static int num = 100;

    public static void main(String[] args) {

        Jedis j = new Jedis("127.0.0.1", 6379);
        j.set("hello", "world");
        System.out.println(j.get("lock"));
        String requestId = UUID.randomUUID().toString();
        boolean lock = RedisTool.tryGetDistributedLock(j, "lock", requestId, 10000);
        System.out.println(lock);
        RedisTool.releaseDistributedLock(j, "lock", requestId);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    consumer();
                }
            }).start();
        }
        j.close();
    }

    /**
     * 加锁的时候，打印的顺序是从100到1
     */
    public static void consumer() {
        String requestId = UUID.randomUUID().toString();
        Jedis j = new Jedis("127.0.0.1", 6379);
        getLock(requestId, j);
        System.out.println("num=" + num--);
        closeLock(requestId, j);
        j.close();
    }

    private static void closeLock(String requestId, Jedis j) {
        RedisTool.releaseDistributedLock(j, "lock", requestId);
    }

    public static boolean getLock(String requestId, Jedis j) {
        boolean lock = false;
        while (!lock) {
            lock = RedisTool.tryGetDistributedLock(j, "lock", requestId, 10000);
            if (!lock) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
