package com.epam.jmp;

import com.epam.jmp.impl.Example;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<Double> list = new CopyOnWriteArrayList<>();
        AtomicBoolean running = new AtomicBoolean(true);
        Example example = new Example(list, running);
        example.run();
    }
}
