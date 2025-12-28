package com.example.cache;

import com.example.cache.exception.KeyNotFoundException;
import com.example.cache.exception.StorageFullException;
import com.example.cache.strategy.EvictionPolicy;
import com.example.cache.strategy.Storage;

public class DefaultCache <K, V> implements Cache<K, V>{
    private Storage<K, V> storage;
    private EvictionPolicy<K> evictionPolicy;

    public DefaultCache(Storage<K, V> storage, EvictionPolicy<K> evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public void put(K key, V value) {
        try {
            storage.put(key, value);
            evictionPolicy.keyAccessed(key);
        } catch (StorageFullException e) {
            System.out.println("Storage is full, attempting to evict a key.");
            K keyToEvict = evictionPolicy.evictKey();
            if (keyToEvict != null) {
                try {
                    storage.remove(keyToEvict);
                    System.out.println("Evicted key: " + keyToEvict);
                    put(key, value);
                } catch (KeyNotFoundException ex) {
                    throw new RuntimeException("Unexpected error during eviction.", ex);
                }
            } else {
                throw new RuntimeException("No key available for eviction.");
            }
        }
    }

    @Override
    public V get(K key) {
        try {
            V value = storage.get(key);
            evictionPolicy.keyAccessed(key);
            return value;
        } catch (KeyNotFoundException e) {
            System.out.println("Key: " + key + " not found in cache.");
            return null;
        }
    }





}
