package com.byshu.concurrent;

import java.util.concurrent.atomic.LongAdder;

public class LongAddrTest {

    public static void main(String[] args) {
        LongAdder adder = new LongAdder();
        adder.add(100);
        adder.add(200);
        System.out.println(adder.sum());
    }

}
