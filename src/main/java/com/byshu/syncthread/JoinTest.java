package com.byshu.syncthread;

public class JoinTest extends Thread {

    public JoinTest(String s) {
        super(s);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            System.err.println("I`m " + getName());
        }
    }

    public static void main(String[] args) {
        JoinTest t = new JoinTest("a");
        JoinTest t2 = new JoinTest("b");
        t.start();
        t2.start();
        try {
            t2.join(); // 主线程阻塞在此  但t仍然在执行
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("I`m main thread");
        }
    }

}
