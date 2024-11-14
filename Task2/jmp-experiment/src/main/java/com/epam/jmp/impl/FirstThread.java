package com.epam.jmp.impl;

import java.util.ConcurrentModificationException;
import java.util.Map;

public record FirstThread(Map<Integer, Integer> map, boolean synced, Object obj) implements Runnable {
    @Override
    public void run() {
        try {
            if(synced) {
                addElementsSynced();
            } else {
                addElements();
            }
        } catch (ConcurrentModificationException exc) {
            System.out.println(Thread.currentThread().getName() + " threw an exception");
            exc.printStackTrace();
        }
    }

    private void addElements() {
        var mapLimit = 10000;

        for (int i = 0; i < mapLimit; ++i) {
            map.put(i, i * 5);
        }
    }
    private void addElementsSynced() {
        var mapLimit = 100000000;

        for (int i = 0; i < mapLimit; ++i) {
            synchronized (obj) {
                map.put(i, i * 5);
            }
        }
    }

}
