package com.byshu.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
//        CountDownLatch latch = new CountDownLatch(15);
		/*ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(5), new ThreadPoolExecutor.CallerRunsPolicy());*/

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(5));
        executor.allowCoreThreadTimeOut(false);
        executor.execute(() -> {
            System.out.println("haha..");
        });
		/*ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
				new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());*/
		
		/*//创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
		Executors.newCachedThreadPool();
		//创建容量为1的缓冲池
		Executors.newSingleThreadExecutor();
		//创建固定容量大小的缓冲池
		Executors.newFixedThreadPool(int);*/

//        for (int i = 0; i < 15; i++) {
//            MyTask task = new MyTask(i, latch);
//            executor.execute(task);
//            System.err.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
//                    executor.getQueue().size() + "，已执行完的任务数目：" +
//                    executor.getCompletedTaskCount());
//        }
//        latch.await();
//        System.err.println(executor.getCompletedTaskCount());
//        executor.shutdown();
    }


}


/**
 * @author byshu
 * @desc 任务类
 */
class MyTask implements Runnable {
    int num;
    CountDownLatch latch;

    public MyTask(int num, CountDownLatch latch) {
        this.num = num;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("正在执行task" + num + "，执行任务的线程为：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + num + "执行完毕");
        latch.countDown();
    }

}