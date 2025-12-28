package com.example.cache.strategy;

public interface Storage<K, V> {

    public void put(K k, V v);

    public V get(K k);

    public void remove(K k);

}
