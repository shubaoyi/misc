package com.byshu.syncthread.prodandconsum;

public class Basket {

    int index = 0;
    Bread[] arr = new Bread[6];

    public synchronized void push(Bread b) {
        try {
            while (index == arr.length) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("生产了:" + b);
        arr[index++] = b;
        // index++;
        this.notify();
    }

    public synchronized void pop() {
        try {
            while (index == 0) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // index--;
        System.out.println("消费了:" + arr[--index]);
        arr[index] = null;
        this.notify();
    }

}
