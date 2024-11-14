package com.epam.jmp.impl;

import java.util.ConcurrentModificationException;
import java.util.Map;

public record SecondThread(Map<Integer, Integer> map,
                           Map<Map.Entry<Integer, Integer>, Integer> sum, boolean synced,
                           Object obj) implements Runnable {

    @Override
    public void run() {
        try {
            if (synced) {
                sumMapSynced();
            } else {
                sumMap();
            }
        } catch (ConcurrentModificationException exc) {
            System.out.println(Thread.currentThread().getName() + " threw an exception");
            exc.printStackTrace();
        }
    }


    private void sumMap() {
        var entrySet = map.entrySet();
        for (var entry : entrySet) {
            var res = entry.getKey() + entry.getValue();
            sum.put(entry, res);
        }
    }

    private void sumMapSynced() {
        var entrySet = map.entrySet();
        synchronized (obj) {
            for (Map.Entry<Integer, Integer> entry : entrySet) {
                sum.put(entry, entry.getValue() + entry.getKey());
            }
        }
    }
}
