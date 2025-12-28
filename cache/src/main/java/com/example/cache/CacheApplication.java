package com.example.cache;

import com.example.cache.factory.CacheFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CacheApplication {

	public static void main(String[] args) {



		SpringApplication.run(CacheApplication.class, args);

		CacheFactory cacheFactory = CacheFactory.getInstance();

		Cache cache = cacheFactory.getCache(5, "LRU", "HASHMAP");

		cache.put(1, "One");
		cache.put(2, "Two");
		cache.put(3, "Three");
		System.out.println("Get key 2: " + cache.get(2));
		cache.put(4, "Four");
		cache.put(5, "Five");
		cache.put(6, "Six");
		System.out.println("Get key 1: " + cache.get(1));



	}

}




//

//CacheManager-> Cache, CacheFactory
// Cache:
//    Storage<Key, Value>
//    Policy: EvictionPolicy<Key>







//Storage-> HashMapStorage, InMemoryStorage
// EvictionPolicy-> LRUEvictionPolicy, LFUEvictionPolicy

