package com.asyf.demo.multithreading.callableDemo;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Integer result = 0;
        Thread currentThread = Thread.currentThread();
        result = new Random().nextInt(100);
        // Thread.sleep(100 * result);
        Thread.sleep(100);
        String name = currentThread.getName();
        System.out.println("线程name:" + name + "--执行完毕");
        return result;
    }
}
