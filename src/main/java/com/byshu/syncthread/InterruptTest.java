package com.byshu.syncthread;


/**
 * 首先，一个线程不应该由其他线程来强制中断或停止，而是应该由线程自己自行停止。
 * 所以，Thread.stop, Thread.suspend, Thread.resume 都已经被废弃了。
 * 而 Thread.interrupt 的作用其实也不是中断线程，而是「通知线程应该中断了」，具体到底中断还是继续运行，应该由被通知的线程自己处理。
 * 具体来说，当对一个线程，调用 interrupt() 时，
 * ① 如果线程处于被阻塞状态（例如处于sleep, wait, join 等状态），那么线程将立即退出被阻塞状态，并抛出一个InterruptedException异常。仅此而已。
 * ② 如果线程处于正常活动状态，那么会将该线程的中断标志设置为 true，仅此而已。被设置中断标志的线程将继续正常运行，不受影响。
 * interrupt() 并不能真正的中断线程，需要被调用的线程自己进行配合才行。
 * 也就是说，一个线程如果有被中断的需求，那么就可以这样做。
 * ① 在正常运行任务时，经常检查本线程的中断标志位，如果被设置了中断标志就自行停止线程。
 * ② 在调用阻塞方法时正确处理InterruptedException异常。（例如，catch异常后就结束线程。）
 * @createTime 2018年1月16日
 */
public class InterruptTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Thread t = new Thread() {
            public void run() {
                // Thread.interrupted()会清除中断标志，如果不想清除：Thread.currentThread().isInterrupted(false)
                while (!Thread.interrupted()) { // 没有设置中断标识
                  System.out.println(getName() + " is running..");
                }
                /*try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("被打断后的interrput状态 : " + Thread.currentThread().isInterrupted());
                    System.err.println(getName() + " stop running");
                    Thread.currentThread().interrupt();
                    System.out.println("重新设置被打断后的interrput状态 : " + Thread.currentThread().isInterrupted());
                }*/
            }
        };

        t.start();

        boolean flag = true;
        while (flag) {
            if (System.currentTimeMillis() - start > 2000) {
                t.interrupt(); // 设置中断标识  另一个线程调用interrupt去打断正在阻塞的线程
                flag = false;
            }
        }
    }

}
