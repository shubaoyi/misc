package com.byshu.test;


import java.util.*;

public class CollectionTest {

    public static void main(String[] args) {
        Hashtable table = new Hashtable<>();
        // key,value都不能为null
        table.put(null, "1");
        table.put(null, null);

        HashMap map = new HashMap<>();
        // key,value都可以为null
        map.put(null, null);

        /*TreeMap treeMap = new TreeMap<>();
        // key不能为null(要作compare) value能为null
        treeMap.put(null, null);*/

        LinkedList linkedList = new LinkedList<>();
        // 链式结构 没有扩容的问题
        linkedList.add(null);

        ArrayList arrayList = new ArrayList();
        // newCapacity = oldCapacity + (oldCapacity >> 1)
        // 扩容时增加当前的一半
        arrayList.add(null);

        Vector vector = new Vector();
        // newCapacity = oldCapacity + ((capacityIncrement > 0) ? capacityIncrement : oldCapacity)
        // 扩容时默认翻一倍
        vector.add(null);

    }

}
