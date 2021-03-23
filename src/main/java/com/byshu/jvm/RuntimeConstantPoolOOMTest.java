package com.byshu.jvm;


import java.util.ArrayList;
import java.util.List;

/**
 * -XX:PermSize=10M -XX:MaxPermSize=10M  （永久代）方法区固定10M
 * JDK1.8以后无效 已经移除永久代的概念
 * 改为元数据空间 -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10m（到大max后FullGC）
 */
public class RuntimeConstantPoolOOMTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList();
        int i = 0;
        while (true) {
            // 通过intern方法往运行时常量池添加数据 膨胀方法区
            list.add(String.valueOf(i++).intern());
        }
    }

}
