package com.byshu.jvm;

/**
 * 动态分派  典型应用-重写
 */
public class DynamicDispatch {

    static abstract class Human {
        abstract void sayHello();
    }

    static class Man extends Human {
        public void sayHello() {
            System.out.println("man sayHello!");
        }
    }

    static class Woman extends Human {
        public void sayHello() {
            System.out.println("woman sayHello!");
        }
    }


    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
    }

}
