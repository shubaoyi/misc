package com.byshu.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @desc 字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用
 * @author byshu
 * @createTime 2018年3月23日
 */
public class CyclicBarrierTest {

	public static void main(String[] args) {
		int N = 4;
		/*CyclicBarrier barrier = new CyclicBarrier(N, new Runnable() {
			@Override
			public void run() {
				System.err.println("线程" + Thread.currentThread().getName() + "执行写入完成后的额外操作...");   
			}
		});*/
		CyclicBarrier barrier = new CyclicBarrier(N);
		for (int i = 0; i < N; i++)
			new Writer(barrier).start();
		
		/*try {
      barrier.await();
    } catch (InterruptedException | BrokenBarrierException e1) {
      e1.printStackTrace();
    }*/
		System.out.println("主线程在干活...");
		try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
		System.out.println("主线程干活结束");
	}

}

class Writer extends Thread {

	private CyclicBarrier barrier;

	public Writer(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	@Override
	public void run() {
		System.out.println("线程" + Thread.currentThread().getName()
				+ "正在写入数据...");
		try {
			// 以睡眠来模拟写入数据操作
			Thread.sleep(5000);
			System.out.println("线程" + Thread.currentThread().getName()
					+ "写入数据完毕，等待其他线程写入完毕");
			// 当前线程等待直到关联这个barrier的所有线程都调用了await()
			barrier.await();
			// barrier.await(2000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} 
		System.out.println("所有线程写入完毕，线程" + Thread.currentThread().getName() + "继续处理其他任务...");
	}
}
