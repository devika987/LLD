package com.example.cache.strategy;

import com.example.cache.exception.KeyNotFoundException;
import com.example.cache.exception.StorageFullException;

import java.util.HashMap;

public class HashMapBasedStorage<K, V> implements Storage<K, V> {

    private final int capacity;
    private final HashMap<K, V> storageMap;

    public HashMapBasedStorage(int capacity) {
        this.capacity = capacity;
        this.storageMap = new HashMap<>();
    }

    @Override
    public void put(K key, V value) {
        if(isFull()) throw new StorageFullException("Storage is full");
        storageMap.put(key, value);

    }

    @Override
    public V get(K key) {
        if(!isPresentKey(key)) throw new KeyNotFoundException("Key not found: " + key);
        return storageMap.get(key);

    }

    private boolean isPresentKey(K key) {
        return storageMap.containsKey(key);
    }

    @Override
    public void remove(K key) {
        if(!isPresentKey(key)) throw new KeyNotFoundException("Key not found: " + key);
        storageMap.remove(key);
    }

    private boolean isFull() {
        return storageMap.size() == capacity;
    }
}
