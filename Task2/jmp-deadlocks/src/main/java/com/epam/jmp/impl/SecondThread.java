package com.epam.jmp.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public record SecondThread(List<Double> list, AtomicBoolean running) implements Runnable {

    @Override
    public void run() {
        while (running.get()) {
            double sum = list.stream().mapToDouble(Double::doubleValue).sum();
            System.out.println("Sum: " + sum);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}