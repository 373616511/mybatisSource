package com.asyf.demo.multithreading.callableDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * demo4和demo3的不同之处在于线程创建方式不同使用ExecutorService和Future
 * 
 * 多个线程测试
 * 
 * callable和future获得结果
 * 
 * 将多个线程的future存放到list中，循环获得结果。
 * 
 * 或得到结果说明线程已经执行完毕，然后可以继续开启进程
 * 
 * @author Administrator
 *
 */
public class demo3 {
	/**
	 * MICROSECONDS 微秒 一百万分之一秒（就是毫秒/1000） MILLISECONDS 毫秒 千分之一秒 NANOSECONDS 毫微秒
	 * 十亿分之一秒（就是微秒/1000） SECONDS 秒 MINUTES 分钟 HOURS 小时 DAYS 天
	 * 使用ExecutorService创建线程
	 * 
	 * 
	 * 
	 * //创建可以容纳3个线程的线程池
	 * 
	 * ExecutorService fixedThreadPool= Executors.newFixedThreadPool(3);
	 * 
	 * //线程池的大小会根据执行的任务动态的分配
	 * 
	 * ExecutorService cacheThreadPool=Executors.newCachedThreadPool();
	 * 
	 * //创建单个线程的线程池,如果当前线程在执行任务时突然中断,则会创建一个新的线程替换它继续执行. ExecutorService
	 * 
	 * singleThreadPool=Executors.newSingleThreadExecutor();
	 * 
	 * //效果类似于Timer定时器
	 * 
	 * ScheduledExecutorService
	 * scheduledThreadPool=Executors.newScheduledThreadPool(3);
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		// 使用场景：1. 耗时较短的任务。
		// 2. 任务处理速度 > 任务提交速度 ，这样才能保证不会不断创建新的进程，避免内存被占满。
		// 取名为cached-threadpool的原因在于线程池中的线程是被线程池缓存了的，
		// 也就是说，线程没有任务要执行时，便处于空闲状态，处于空闲状态的线程并不会被立即销毁（会被缓存住），
		// 只有当空闲时间超出一段时间(默认为60s)后，线程池才会销毁该线程（相当于清除过时的缓存）。
		// 新任务到达后，线程池首先会让被缓存住的线程（空闲状态）去执行任务，如果没有可用线程（无空闲线程），
		// 便会创建新的线程
		// 能reuse的线程，必须是timeout
		// IDLE内的池中线程，缺省timeout是60s，超过这个IDLE时长，线程实例将被终止及移出池。
		// 放入CachedThreadPool的线程不必担心其结束，超过TIMEOUT不活动，其会自动被终止。
		// 提交了多少线程就是多个个线程一起并发
		// 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
		// 项目中避免使用此方式
		// ExecutorService executorService = Executors.newCachedThreadPool();

		// 每次只并发运行三个线程
		// 固定数目
		// 注意：所以FixedThreadPool多数针对一些很稳定很固定的正规并发线程，多用于服务器
		// 打印的线程号码，都是1，2，3顺序打印，说明只有3个线程在并发，
		ExecutorService executorService = Executors.newFixedThreadPool(5);

		// SingleThreadExecutor得到的是一个单个的线程，这个线程会保证你的任务执行完成。
		// 如果当前线程意外终止，会创建一个新线程继续执行任务，这和我们直接创建线程不同，
		// 也和newFixedThreadPool(1)不同。
		// 打印的线程号码，都是1，说明只有一个线程在并发
		// ExecutorService executorService =
		// Executors.newSingleThreadExecutor();

		// ScheduledThreadPool是一个固定大小的线程池，与FixedThreadPool类似，执行的任务是定时执行。
		// 与前三种不同的是：executorService.schedule提交线程。
		// ScheduledExecutorService executorService =
		// Executors.newScheduledThreadPool(2);

		while (true) {
			// 存放Future对象
			List<Future<Integer>> futures = new ArrayList<>();

			// 设置线程数
			int threadNum = 10;
			for (int i = 0; i < threadNum; i++) {
				CallableTask callableTask = new CallableTask();
				// 注释掉submit。就是延时执行
				// executorService.schedule(callableTask, 10, TimeUnit.SECONDS);
				Future<Integer> future = executorService.submit(callableTask);
				futures.add(future);
			}
			// shutdown() 方法在终止前允许执行以前提交的任务，而 shutdownNow()
			// 方法阻止等待任务启动并试图停止当前正在执行的任务。
			// executorService.shutdown();
			// executorService.shutdownNow();
			/*
			 * try { Thread.sleep(1000 * 60); } catch (InterruptedException e1)
			 * { e1.printStackTrace(); }
			 */

			System.out.println(futures.size());
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