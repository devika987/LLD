package com.example.cache.strategy;

public interface EvictionPolicy<K> {

    public K evictKey();

    public void keyAccessed(K key);
}
