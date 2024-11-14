package com.epam.jmp;

import com.epam.jmp.impl.Example;

import java.util.HashMap;
import java.util.Map;

/**
 * run Simple example - trying to get Threads should work before catching ConcurrentModificationException
 *
 */
public class SimpleApp
{
    public static void main( String[] args )
    {
        var map = new HashMap<Integer, Integer>();
        var sum = new HashMap<Map.Entry<Integer, Integer>, Integer>();
        var simpleExample = new Example(map, sum, false);
        simpleExample.run();
    }
}
