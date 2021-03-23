package com.byshu.interview;

public class FinalTest {
    public final double i = Math.random();
    public static double j = Math.random();


    public static void main(String[] args) {
        FinalTest myClass1 = new FinalTest();
        FinalTest myClass2 = new FinalTest();

        System.out.println(myClass1.i);
        System.err.println(FinalTest.j);
        System.out.println(myClass2.i);
        System.err.println(FinalTest.j);
    }
}
