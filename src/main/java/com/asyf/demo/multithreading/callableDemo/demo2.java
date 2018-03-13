package com.asyf.demo.multithreading.callableDemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 单个线程测试
 * 
 * callable和future获得结果
 * 
 * @author Administrator
 *
 */
public class demo2 {
	/**
	 * MICROSECONDS 微秒 一百万分之一秒（就是毫秒/1000） MILLISECONDS 毫秒 千分之一秒 NANOSECONDS 毫微秒
	 * 十亿分之一秒（就是微秒/1000） SECONDS 秒 MINUTES 分钟 HOURS 小时 DAYS 天
	 * 使用ExecutorService创建线程
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		CallableTask callableTask = new CallableTask();
		Future<Integer> future = executorService.submit(callableTask);
		executorService.shutdown();
		// 通过future查看进程
		// isDone()：判断任务是否已完成
		boolean done = future.isDone();
		System.out.println("isDone()--" + done);
		// isCancel()：判断任务是否在正常执行完前被取消的，如果是则返回true
		boolean cancelled = future.isCancelled();
		System.out.println("isCancelled()--" + cancelled);
		// get()：等待计算结果的返回，如果计算被取消了则抛出
		/*
		 * try { Integer a = future.get(); System.out.println("执行结果a--" + a); }
		 * catch (InterruptedException e) { e.printStackTrace(); } catch
		 * (ExecutionException e) { e.printStackTrace(); }
		 */
		// get(long timeout,TimeUtil
		// unit)：设定计算结果的返回时间，如果在规定时间内没有返回计算结果则抛出TimeOutException
		try {
			// 线程睡眠时间大于等待时间则报异常
			// Integer b = future.get(500, TimeUnit.MILLISECONDS);
			Integer b = future.get(10000, TimeUnit.MILLISECONDS);
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
}
