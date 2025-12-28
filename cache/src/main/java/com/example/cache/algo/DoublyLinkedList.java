package com.example.cache.algo;

public class DoublyLinkedList<K> {

    private final DoublyLinkedListNode<K> head;
    private final DoublyLinkedListNode<K> tail;

    public DoublyLinkedList() {
        head = new DoublyLinkedListNode<>(null);
        tail = new DoublyLinkedListNode<>(null);
        head.next = tail;
        tail.prev = head;
    }


    public void detachNode(DoublyLinkedListNode<K> node) {
        if (node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void addNodeAtLast(DoublyLinkedListNode<K> node) {
        DoublyLinkedListNode<K> tailPrev = tail.prev;
        tailPrev.next = node;
        node.prev = tailPrev;
        node.next = tail;
        tail.prev = node;
    }

    public DoublyLinkedListNode<K> addElementAtLast(K element) {
        DoublyLinkedListNode<K> newNode = new DoublyLinkedListNode<>(element);
        addNodeAtLast(newNode);
        return newNode;
    }

    private boolean isItemPresent() {
        return head.next != tail;
    }

    public DoublyLinkedListNode<K> getFirstNode() {
        if (!isItemPresent()) {
            return null;
        }
        return head.next;
    }

    public DoublyLinkedListNode<K> getLastNode() {
        if (!isItemPresent()) {
            return null;
        }
        return tail.prev;
    }


}
