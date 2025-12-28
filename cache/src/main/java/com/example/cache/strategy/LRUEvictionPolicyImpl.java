package com.example.cache.strategy;

import com.example.cache.algo.DoublyLinkedList;
import com.example.cache.algo.DoublyLinkedListNode;

import java.util.HashMap;

public class LRUEvictionPolicyImpl<K> implements EvictionPolicy<K> {

   private final DoublyLinkedList<K> dll;
   private final HashMap<K, DoublyLinkedListNode<K>> dllMap;

    public LRUEvictionPolicyImpl() {
        this.dll = new DoublyLinkedList<>();
        this.dllMap = new HashMap<>();
    }


    @Override
    public K evictKey() {
        if(isPresentItem()){
            K key = dll.getFirstNode().getElement();
            dll.detachNode(dll.getFirstNode());
            return key;
        }

        return null;
    }

    private boolean isPresentItem() {
        return dll.getFirstNode() != null;
    }

    @Override
    public void keyAccessed(K key) {

        if(dllMap.containsKey(key)){
            DoublyLinkedListNode<K> node = dllMap.get(key);
            dll.detachNode(node);
            dll.addNodeAtLast(node);
            dllMap.put(key, node);
        } else {
            DoublyLinkedListNode<K> newNode = new DoublyLinkedListNode<>(key);
            dll.addNodeAtLast(newNode);
            dllMap.put((K) key, newNode);
        }


    }
}
