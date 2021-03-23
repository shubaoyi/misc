package com.byshu.jvm;

public class ConstClassTest {

    public static void main(String[] args) throws Exception {
        // 输出hello 没有触发ConstClass加载
        System.out.println(ConstClass.HELLO);
    }
}
