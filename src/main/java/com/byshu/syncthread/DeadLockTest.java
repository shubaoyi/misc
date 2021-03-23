package com.byshu.syncthread;

public class DeadLockTest implements Runnable {
    String name = "";
    int flag = 1;
    static Object o1 = new Object(), o2 = new Object();


    public DeadLockTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if (flag == 1) {
            synchronized (o1) {
                System.out.println(name + "锁定o1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println(name + "锁定o2, 执行结束");
                }
            }

        } else if (flag == 2) {
            synchronized (o2) {
                System.out.println(name + "锁定o2");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println(name + "锁定o1, 执行结束");
                }
            }
        }

    }

    public static void main(String[] args) {
        DeadLockTest r1 = new DeadLockTest("线程1");
        DeadLockTest r2 = new DeadLockTest("线程2");
        r1.flag = 1;
        r2.flag = 2;
        Thread td1 = new Thread(r1);
        Thread td2 = new Thread(r2);
        td1.start();
        td2.start();
    }

}
