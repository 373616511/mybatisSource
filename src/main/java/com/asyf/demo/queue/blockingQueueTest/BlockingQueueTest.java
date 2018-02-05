package com.asyf.demo.queue.blockingQueueTest;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter base directory (e.g. /usr/local/jdk5.0/src): ");
        String directory = in.nextLine();
        System.out.print("Enter keyword (e.g. volatile): ");
        String keyword = in.nextLine();

        final int FILE_QUEUE_SIZE = 10;// 阻塞队列大小
        final int SEARCH_THREADS = 100;// 关键字搜索线程个数

        // 基于ArrayBlockingQueue的阻塞队列
        BlockingQueue<File> queue = new ArrayBlockingQueue<File>(
                FILE_QUEUE_SIZE);

        //只启动一个线程来搜索目录
        FileEnumerationTask enumerator = new FileEnumerationTask(queue,
                new File(directory));
        new Thread(enumerator).start();

        //启动100个线程用来在文件中搜索指定的关键字
        for (int i = 1; i <= SEARCH_THREADS; i++)
            new Thread(new SearchTask(queue, keyword)).start();
    }

}
