package com.byshu.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTest {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		// Future方式
		// Future<Integer> result = executor.submit(new Task());
		// FutureTask方式
		FutureTask<Integer> futureTask = new FutureTask<Integer>(new Task());
		executor.submit(futureTask);
		executor.shutdown();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("主线程在执行任务");
		try {
			System.out.println("task运行结果" + futureTask.get());
			// System.out.println("task运行结果" + result.get(1000, TimeUnit.MILLISECONDS));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("所有任务执行完毕");
	}

}

class Task implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println("子线程在进行计算");
		Thread.sleep(3000);
		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			sum += i;
		}
		return sum;
	}

}