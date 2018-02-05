package com.asyf.demo.queue.blockingQueueTest;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class FileEnumerationTask implements Runnable {
    //哑元文件对象，放在阻塞队列最后，用来标示文件已被遍历完
    public static File DUMMY = new File("");

    private BlockingQueue<File> queue;
    private File startingDirectory;

    public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory) {
        this.queue = queue;
        this.startingDirectory = startingDirectory;
    }

    public void run() {
        try {
            enumerate(startingDirectory);
            queue.put(DUMMY);//执行到这里说明指定的目录下文件已被遍历完
        } catch (InterruptedException e) {
        }
    }

    // 将指定目录下的所有文件以File对象的形式放入阻塞队列中
    public void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory())
                enumerate(file);
            else
                //将元素放入队尾，如果队列满，则阻塞
                queue.put(file);
        }
    }
}
