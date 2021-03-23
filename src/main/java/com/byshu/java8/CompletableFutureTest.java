package com.byshu.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class CompletableFutureTest {

    public static void main(String[] args) throws Exception {
//        // 结果集
//        List<String> list = new ArrayList<>();
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//
//        List<Integer> taskList = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
//        // 全流式处理转换成CompletableFuture[]+组装成一个无返回值CompletableFuture，join等待执行完毕。返回结果whenComplete获取
//        CompletableFuture[] cfs = taskList.stream()
//                .map(integer -> CompletableFuture.supplyAsync(() -> calc(integer), executorService)
//                        .thenApply(h -> Integer.toString(h))
//                        .whenComplete((s, e) -> {
//                            System.err.println("任务" + s + "完成! return: " + s);
//                            list.add(s);
//                        })).toArray(CompletableFuture[]::new);
//        // 封装后无返回值，必须自己whenComplete()获取
//        CompletableFuture.allOf(cfs).join();
//        System.out.println("list=" + list);


        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println(Thread.currentThread().getName() + " is daemon: " + Thread.currentThread().isDaemon());
                return null;
            }
        });

        final CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName() + " is daemon: " + Thread.currentThread().isDaemon());
            return "result1";
        });

//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        final CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.err.println(Thread.currentThread().getName() + " is daemon: " + Thread.currentThread().isDaemon());
//            return "result2";
//        }, executorService);

        //System.out.println(future1.get());
        //System.out.println(future2.get());
        executorService.shutdown();

    }

    public static int calc(Integer i) {
        try {
            if (i == 1) {
                Thread.sleep(3000);//任务1耗时3秒
            } else if (i == 5) {
                Thread.sleep(5000);//任务5耗时5秒
            } else {
                Thread.sleep(1000);//其它任务耗时1秒
            }
            System.out.println(Thread.currentThread().getName()
                    + " 任务" + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }

}
