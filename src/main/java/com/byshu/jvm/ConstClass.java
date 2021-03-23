package com.byshu.jvm;

public class ConstClass {

    static {
        System.out.println("ConstClass init!");
    }

    public static final String HELLO = "hello";
}
