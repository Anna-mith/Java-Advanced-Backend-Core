package com.epam.jmp;

import com.epam.jmp.impl.Example;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * use Collections.synchronizedMap() to solve issue
 */
public class SyncApp {
    public static void main(String[] args) {
        var map = new HashMap<Integer, Integer>();
        var sum = new HashMap<Map.Entry<Integer, Integer>, Integer>();
        var syncMap = Collections.synchronizedMap(map);
        var syncSum = Collections.synchronizedMap(sum);
        var syncExample = new Example(syncMap, syncSum, true);
        syncExample.run();
    }
}
