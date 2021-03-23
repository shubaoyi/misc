package com.byshu.jvm;

public class InitializeTest {

    public static class A {
        static {
            System.out.println("parent class initializing..");
        }

        // public static int a = 123;
        /**
         * 如果加上final A类也不会初始化 因为 static final的变量在编译阶段可以确定值
         * 在类加载的准备阶段已赋初始值123 所以直接引用a不会触发A类的初始化
         */
        public static final int a = 123;
    }

    public static class B extends A {
        static {
            System.out.println("sub class initializing..");
        }
    }

    public static void main(String[] args) {
        // 没有触发B的初始化
        System.out.println(B.a);
    }

}
