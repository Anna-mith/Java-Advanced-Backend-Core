package com.epam.jmp;

import com.epam.jmp.impl.Example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * use ConcurrentHashMap  to solve the issue
 */

public class ConcurrentApp {
    public static void main(String[] args) {
        var concMap = new ConcurrentHashMap<Integer, Integer>();
        var concSum = new ConcurrentHashMap<Map.Entry<Integer, Integer>, Integer>();
        var concMapExample = new Example(concMap, concSum, false);
        concMapExample.run();
    }
}
