package com.epam.jmp.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Example {
    private final Thread firstThread;
    private final Thread secondThread;
    private final Thread thirdThread;
    AtomicBoolean running;

    public Example(List<Double> list, AtomicBoolean running) {
        this.running = running;
        this.firstThread = new Thread(new FirstThread(list, this.running));
        this.secondThread = new Thread(new SecondThread(list, this.running));
        this.thirdThread = new Thread(new ThirdThread(list, this.running));
    }

    public void run() {
        this.firstThread.start();
        this.secondThread.start();
        this.thirdThread.start();

        try {
            Thread.sleep(10_000);
            running.set(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            this.firstThread.join();
            this.secondThread.join();
            this.thirdThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Execution finished.");
    }
}
