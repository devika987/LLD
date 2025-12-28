package com.example.cache.factory;

import com.example.cache.Cache;
import com.example.cache.DefaultCache;
import com.example.cache.enums.EvictionPolicyEnum;
import com.example.cache.enums.StorageEnum;
import com.example.cache.strategy.EvictionPolicy;
import com.example.cache.strategy.HashMapBasedStorage;
import com.example.cache.strategy.LRUEvictionPolicyImpl;
import com.example.cache.strategy.Storage;

public class CacheFactory {

    private static CacheFactory instance = null;

    private CacheFactory() {
    }

    public static CacheFactory getInstance() {
        if (instance == null) {
            instance = new CacheFactory();
        }
        return instance;
    }




        public Cache getCache(int capacity, String lru, String hashMap) {

            if (EvictionPolicyEnum.LRU.toString().equals(lru) && StorageEnum.HASHMAP.toString().equals(hashMap)) {
                EvictionPolicy evictionPolicy = new LRUEvictionPolicyImpl<>();
                Storage storage = new HashMapBasedStorage<>(capacity);
                return new DefaultCache(storage, evictionPolicy);
            }
            // Add more conditions for different policies and storage types
            throw new IllegalArgumentException("Unsupported cache configuration");
        }


}
