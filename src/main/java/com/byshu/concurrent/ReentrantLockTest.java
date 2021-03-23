package com.byshu.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
	
	public static void main(String[] args) {
		int N = 10000;
		final CountDownLatch latch = new CountDownLatch(N);
		Counter counter = new Counter();
		for (int i = 0; i < N; i++) {
			new MyThread(counter, latch).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(counter.num);
	}

}

class MyThread extends Thread {
	Counter counter;
	CountDownLatch latch;
	
	public MyThread(Counter counter, CountDownLatch latch) {
		this.counter = counter;
		this.latch = latch;
	}
	
	@Override
	public void run() {
		counter.count();
		latch.countDown();
	}
}

class Counter {
	public int num = 0;
	Lock lock = new ReentrantLock();
	
	public void count() {
	  lock.lock();
		try {
			num++;
		} finally {
			lock.unlock();
		}
	}
	
}
