package com.epam.jmp;

import com.epam.jmp.impl.Example;
import com.epam.jmp.impl.SynchronizedThreadSafeMap;

import java.util.Map;

public class SyncThreadSafeMapApp {
    public static void main(String[] args) {
        var threadSafeMap = new SynchronizedThreadSafeMap<Integer, Integer>();
        var ThreadSafeSum = new SynchronizedThreadSafeMap<Map.Entry<Integer, Integer>, Integer>();
        var concMapExample = new Example(threadSafeMap, ThreadSafeSum, true);
        concMapExample.run();
    }
}