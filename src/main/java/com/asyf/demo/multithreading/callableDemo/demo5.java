package com.asyf.demo.multithreading.callableDemo;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * demo说明缓存型和固定大小的线程池的线程缓存机制以及使用场景
 * 
 * @author Administrator
 *
 */
public class demo5 {

	// 单个线程测试，future的get方法过期会timeout异常
	public static void main(String[] args) {
		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() throws Exception {
				Thread currentThread = Thread.currentThread();
				System.out.println(currentThread.getName() + "线程开始");
				// 设置线程为61s，newCachedThreadPool()缓存型数据池缺省timeout只有60s
				currentThread.sleep(1000 * 1);
				System.out.println(currentThread.getName() + "线程结束");
				return new Random().nextInt(100);
			}
		};
		ExecutorService excutor = Executors.newCachedThreadPool();
		/**
		 * 重点：都是thread-1（因为睡眠了60s后才提交的第二个线程） 
		 * 
		 * pool-1-thread-1线程开始 pool-1-thread-1线程结束 main
		 * pool-1-thread-1线程开始 6 ------------------- main pool-1-thread-1线程结束 87
		 */

		// ExecutorService excutor = Executors.newFixedThreadPool(10);
		/*
		 * 重点：thread-1 thread-2并未释放线程池
		 * 
		 * Executors.newFixedThreadPool(10)运行结果 pool-1-thread-1线程开始
		 * pool-1-thread-1线程结束 main pool-1-thread-2线程开始 89 -------------------
		 * main pool-1-thread-2线程结束 15
		 */
		Future<Integer> future = excutor.submit(callable);
		// 睡眠60s再提交线程，测试缓存型线程池的性能
		try {
			Thread.sleep(1000 * 61);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Future<Integer> future2 = excutor.submit(callable);
		try {
			Thread currentThread = Thread.currentThread();
			System.out.println(currentThread.getName());
			Thread.sleep(500);// 可能做一些事情
			// Integer integer = future.get();
			Integer integer = future.get();
			System.out.println(integer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------");
		try {
			Thread currentThread = Thread.currentThread();
			System.out.println(currentThread.getName());
			Thread.sleep(500);// 可能做一些事情
			// Integer integer = future.get();
			Integer integer = future2.get();
			System.out.println(integer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
