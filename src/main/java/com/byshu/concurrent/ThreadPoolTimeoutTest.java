package com.byshu.concurrent;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTimeoutTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(5));
        // executor.allowCoreThreadTimeOut(true);

        long startTime = System.currentTimeMillis();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(i);
                }
            }
        });
        while (System.currentTimeMillis() - startTime < 20000) {
            System.out.println("waiting..");
        }
        System.out.println("status :" + executor.isShutdown());
        System.out.println("core pool size :" + executor.getCorePoolSize());
        System.out.println("active count :" + executor.getActiveCount());
        /*executor.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(i);
                }
            }
        });*/
    }

}
