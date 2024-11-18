package com.epam.jmp.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public record FirstThread(List<Double> list, AtomicBoolean running) implements Runnable {

    @Override
    public void run() {
        while (running.get()) {
            double randomNumber = Math.random();
            list.add(randomNumber);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
