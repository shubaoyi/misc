package com.byshu.jvm;

/**
 * 静态分派  典型应用-重载
 */
public class StaticDispatch {

    static abstract class Human {
    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    public void sayHello(Human human) {
        System.out.println("hello guy!");
    }

    public void sayHello(Man man) {
        System.out.println("hello gentleman!");
    }

    public void sayHello(Woman woman) {
        System.out.println("hello lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sd = new StaticDispatch();
        // 编译阶段能确定的man和woman的静态类型是Human
        sd.sayHello(man);
        sd.sayHello(woman);
    }

}
