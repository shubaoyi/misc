package com.byshu.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	public static void main(String[] args) {
		// 工人数目
		int N = 8;
		// 机器数目
		Semaphore semaphore = new Semaphore(5);
		final CountDownLatch latch = new CountDownLatch(N);
		for (int i = 0; i < N; i++) {
			new Worker(i, semaphore, latch).start();
		}
		// 子线程执行结束后计算可用许可数量
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(semaphore.availablePermits());
	}

}

class Worker extends Thread {
	private int num;
	private Semaphore semaphore;
	private CountDownLatch latch;

	public Worker(int num, Semaphore semaphore, CountDownLatch latch) {
		this.num = num;
		this.semaphore = semaphore;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			semaphore.acquire();
			System.out.println("工人" + this.num + "占用一个机器在生产...");
			Thread.sleep(2000);
			System.out.println("工人" + this.num + "释放出机器");
			semaphore.release();
			latch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

