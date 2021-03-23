package com.byshu.jvm;

/**
 * @author byshu
 * @desc
 */
public class GCTest {


    private static final int _1MB = 1024 * 1024;

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
     * -XX:PretenureSizeThreshold=5242880 (直接进入老年代的门槛：5M)
     * Serial + SerialOld
     *
     * -XX:CMSInitiatingOccupancyFraction= （1.6 92%）
     * -XX:+UseCMSCompactAtFullCollection (默认开启)
     * -XX:CMSFullGCsBeforeCompaction= (执行多少次不压缩的fullgc后 执行一次带压缩的  默认0 每次都整理)
     *
     * -XX:MaxGCPauseMillis= (PS 最大gc停顿时间 期望值)
     * -XX:GCTimeRatio= (0-100 默认99 gc时间占总时间的比例 1/1+99)
     * -XX:UseAdaptiveSizePolicy( PS收集器自适应调节新生代大小 E,S区比例，晋升老年代对象年龄等参数)
     */
    public static void main(String[] args) {
        byte[] alloc1 = new byte[2 * _1MB], alloc2 = new byte[2 * _1MB], alloc3 = new byte[2 * _1MB];
        byte[] alloc4 = new byte[4 * _1MB]; // 发生MinorGC
    }
}
