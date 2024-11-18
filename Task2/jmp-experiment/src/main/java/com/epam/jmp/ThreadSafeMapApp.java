package com.epam.jmp;

import com.epam.jmp.impl.Example;
import com.epam.jmp.impl.ThreadSafeMap;

import java.util.Map;

public class ThreadSafeMapApp {
    public static void main(String[] args) {
        var threadSafeMap = new ThreadSafeMap<Integer, Integer>();
        var ThreadSafeSum = new ThreadSafeMap<Map.Entry<Integer, Integer>, Integer>();
        var concMapExample = new Example(threadSafeMap, ThreadSafeSum, true);
        concMapExample.run();
    }
}
