package com.epam.jmp.impl;

import java.util.Map;

public class Example {
    private final Map<Integer, Integer> map;
    private final Map<Map.Entry<Integer, Integer>, Integer> sum;
    private final Thread firstThread;
    private final Thread secondThread;
    public Example(Map<Integer, Integer> map, Map<Map.Entry<Integer, Integer>, Integer> sum, boolean synced) {
        this.map = map;
        this.sum = sum;
        firstThread = new Thread(new FirstThread(this.map, synced, this), "Thread 1");
        secondThread = new Thread(new SecondThread(this.map, this.sum, synced, this), "Thread 2");
    }

    public void run() {
        firstThread.start();
        secondThread.start();
        try {
            firstThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            secondThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sum.values().stream().limit(6).forEach(System.out::println);
    }
}
