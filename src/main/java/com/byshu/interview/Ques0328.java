package com.byshu.interview;

public class Ques0328 {

    public static void main(String[] args) {
        new Ques0328();
    }

    Ques0328() {
        Ques0328 t1 = this;
        Ques0328 t2 = this;

        synchronized (t1) {
            try {
                t2.wait();
                System.out.println("wait");
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            } catch (Exception e) {
                System.out.println("Exception");
            } finally {
                System.out.println("Finally");
            }
        }
        System.out.println("OK");
    }

}
