package com.asyf.demo.multithreading.callableDemo;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 内部类实现单个线程的demo
 * 
 * @author Administrator
 *
 */
public class demo {

	// 单个线程测试，future的get方法过期会timeout异常
	public static void main(String[] args) {
		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() throws Exception {
				Thread currentThread = Thread.currentThread();
				System.out.println(currentThread.getName());
				currentThread.sleep(1000 * 5);
				return new Random().nextInt(100);
			}
		};
		FutureTask<Integer> future = new FutureTask<Integer>(callable);
		new Thread(future).start();
		try {
			Thread currentThread = Thread.currentThread();
			System.out.println(currentThread.getName());
			Thread.sleep(500);// 可能做一些事情
			// Integer integer = future.get();
			Integer integer = future.get(6, TimeUnit.SECONDS);
			System.out.println(integer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
}
