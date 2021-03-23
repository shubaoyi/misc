package com.byshu.jvm;


public class VolatileTest {

    public static volatile boolean initFlag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("t1 start");
            while (initFlag) {
                // System.out.println("what?");
            }
            System.out.println("t1 stop");
        }).start();

        Thread.sleep(1000);
        initFlag = false;
    }

}
