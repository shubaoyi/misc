package com.byshu.jvm;

/**
 * -Xss128k
 * -XX:+HeapDumpOnOutOfMemoryError 对栈溢出并不管用
 * HotSpot的实现并不区分虚拟机栈和本地方法栈 所以设置-Xoss参数实际实际上是无效的
 */
public class StackOverFlowTest {

    public int length = 1;

    public void stackLeak() {
        length++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackOverFlowTest test = new StackOverFlowTest();
        try {
            test.stackLeak();
        } catch (Throwable e) {
            System.out.println("length : " + test.length);
            e.printStackTrace();
        }
    }

}
