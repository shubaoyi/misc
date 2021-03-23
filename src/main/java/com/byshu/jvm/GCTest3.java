package com.byshu.jvm;

/**
 * @author byshu
 * @desc -XX:MaxTenuringThreshold=1 / -XX:MaxTenuringThreshold=15
 */
public class GCTest3 {

    //private static final int _1MB = 1024 * 1024;


    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
     * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     * -XX:PretenureSizeThreshold=5242880 (直接进入老年代的门槛：5M)
     * Serial + SerialOld
     */
    public static void main(String[] args) {
        // 256KB survivor可以容纳
        byte[] alloc1 = new byte[262144];
        byte[] alloc2 = new byte[4 * 1048576];
        // eden已放不下 发生MinorGC alloc1进入survivor alloc2进入老年代(survivor放不下？) alloc3进入新生代
        byte[] alloc3 = new byte[4 * 1048576];
        // alloc3仍在新生代
        alloc3 = null;
        /**
         * eden又放不下 再次MinorGC alloc3(old)被回收  alloc1.age=2 进入老年代
         * 此时老年代 alloc1 + alloc2
         * 新生代 alloc3(new)
         */
        // alloc3 = new byte[3980000];
        alloc3 = new byte[4 * 1048576];
    }
}
