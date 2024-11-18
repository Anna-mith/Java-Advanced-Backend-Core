package com.epam.jmp.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public record ThirdThread(List<Double> list, AtomicBoolean running) implements Runnable {
    @Override
    public void run() {
        while (running.get()) {
            double sumOfSquares = list.stream().mapToDouble(num -> Math.pow(num, 2)).sum();
            double squareRoot = Math.sqrt(sumOfSquares);
            System.out.println("Square root of sum of squares: " + squareRoot);

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}