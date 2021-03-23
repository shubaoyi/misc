package com.byshu.concurrent;

/**
 * ThreadLocal:为每个访问ThreadLocal中的变量的线程创建副本，每个线程操作自己的副本不会影响其他线程的副本
 * 适用于存储需要并发访问但又无需排队操作（当前线程的操作必须建立在前一个线程修改后的基础上）的变量
 */
public class ThreadLocalTest {

    /*ThreadLocal<Integer> num = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };*/

    ThreadLocal<Integer> num = ThreadLocal.withInitial(() -> {
        return 0;
    });


    class MyThread extends Thread {

        public MyThread() {

        }
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                num.set(num.get() + 1);
                System.out.println(Thread.currentThread().getName() + " : " + num.get());
            }
        }
    }

    public static void main(String[] args) {
        ThreadLocalTest test = new ThreadLocalTest();
        test.new MyThread().start();
        test.new MyThread("thread2").start();
        test.new MyThread("thread3").start();
    }
}
