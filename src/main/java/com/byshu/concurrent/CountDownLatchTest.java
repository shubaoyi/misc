package com.byshu.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(2);

		new Thread() {
			public void run() {
				System.out.println("子线程" + Thread.currentThread().getName()
						+ "正在执行");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 计数栓的数量减一，为0的时候释放所有等待的线程
				latch.countDown();
				System.out.println("子线程" + Thread.currentThread().getName()
						+ "执行完毕");
			};
		}.start();

		new Thread() {
			public void run() {
				System.out.println("子线程" + Thread.currentThread().getName()
						+ "正在执行");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				latch.countDown();
				System.out.println("子线程" + Thread.currentThread().getName()
						+ "执行完毕");
			};
		}.start();

		System.out.println("等待2个子线程执行完毕...");
		try {
			// 当前线程等待直到计数栓数量为0，除非线程被打断
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("2个子线程已经执行完毕");
		System.out.println("继续执行主线程");
	}

}
