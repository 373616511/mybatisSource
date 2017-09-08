package com.asyf.jdk.thread;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 3) {
                Thread myThread1 = new MyThread();     // 创建一个新的线程  myThread1  此线程进入新建状态
                myThread1.start();                     // 调用start()方法使得线程进入就绪状态
                myThread1.join();
            }
        }
        System.err.println(Thread.currentThread().getPriority());
    }
}
