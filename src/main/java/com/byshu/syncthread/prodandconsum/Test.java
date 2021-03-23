package com.byshu.syncthread.prodandconsum;

public class Test {

    public static void main(String[] args) {
        Basket bk = new Basket();
        Producer p = new Producer(bk);
        Consumer c = new Consumer(bk);
        new Thread(p).start();
        new Thread(c).start();
    }
}
