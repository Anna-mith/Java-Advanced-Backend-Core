package com.epam.jmp.impl;

import java.util.*;

/**
 * Only methods used in examples are made thread safe.
 */

public class SynchronizedThreadSafeMap <K, V> implements Map<K, V> {
    private final Map<K, V> map;

    public SynchronizedThreadSafeMap() {
        this.map = Collections.synchronizedMap(new HashMap<>());
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public synchronized V put(K key, V value) {
        return map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public synchronized Set<K> keySet() {
        return Collections.synchronizedSet(map.keySet());
    }

    @Override
    public synchronized Collection<V> values() {
        return Collections.synchronizedCollection(map.values());
    }

    @Override
    public synchronized Set<Entry<K, V>> entrySet() {
        return Collections.synchronizedSet(map.entrySet());
    }


    @Override
    public synchronized V get(Object key) {
        return map.get(key);
    }
}