package com.asyf.demo.multithreading.callableDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class demo4 {

	/**
	 * demo4和demo3的不同之处在于线程创建方式不同使用FutureTask和Thread
	 *
	 * 
	 * MICROSECONDS 微秒 一百万分之一秒（就是毫秒/1000） MILLISECONDS 毫秒 千分之一秒 NANOSECONDS 毫微秒
	 * 十亿分之一秒（就是微秒/1000） SECONDS 秒 MINUTES 分钟 HOURS 小时 DAYS 天
	 * 使用ExecutorService创建线程
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		while (true) {
			// 存放Future对象
			List<Future<Integer>> futures = new ArrayList<>();

			// 设置线程数
			int threadNum = 10;
			for (int i = 0; i < threadNum; i++) {
				CallableTask callableTask = new CallableTask();
				// 执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
				FutureTask<Integer> future = new FutureTask<>(callableTask);
				new Thread(future).start();
				futures.add(future);
			}
			// shutdown() 方法在终止前允许执行以前提交的任务，而 shutdownNow()
			// 方法阻止等待任务启动并试图停止当前正在执行的任务。
			// executorService.shutdown();
			// executorService.shutdownNow();

			for (int i = 0; i < futures.size(); i++) {
				Future<Integer> future = futures.get(i);
				try {
					Integer b = future.get(1, TimeUnit.MINUTES);
					System.out.println("执行结果b--" + b);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (TimeoutException e) {
					// 任务超时取消任务
					future.cancel(true);
					e.printStackTrace();
				}
			}
			futures = null;
			System.out.println("\r\n线程" + Thread.currentThread().getName() + "执行=====\r\n\r\n");
			try {
				Thread.sleep(1000 * 2);
				// Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
