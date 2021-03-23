package com.byshu.jvm;


/**
 * JDK1.7之前intern()会把常量池中不存在的字符串复制到常量池中（存在则返回常量池中的字符串）
 * 1.7之后则只会复制引用
 */
public class InternTest {

    public static void main(String[] args) {
        String h1 = new String("hello");
        String h2 = new String("hello");
        // hello 编译阶段已放入常量池
        System.out.println(h1.intern() == h1); // false h1指向的是堆中的对象
        System.out.println(h2.intern() == h1.intern()); // true

        String str1 = new StringBuilder("hel").append("lo").toString();
        String str2 = new StringBuilder("ja").append("va").toString();
        // String str2 = "java"; // 直接定义的字符串编译阶段就放入了常量池 不会在堆中创建对象

        System.out.println(str1.intern() == str1); // true(前5行注释，hello预先不在常量池，str1把自己的引用复制进去)/false
        System.out.println(str2.intern() == str2); // false('java'预先放入了常量池？)
    }

}
