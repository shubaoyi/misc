package com.byshu.concurrent;

import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueTest {

    public static void main(String[] args) {
        Queue<String> q = new PriorityQueue<>();
        q.offer("apple");
        q.offer("pear");
        q.offer("banana");
        System.out.println(q.poll()); // apple
        System.out.println(q.poll()); // banana
        System.out.println(q.poll()); // pear
        System.out.println(q.poll()); // null
    }
}
