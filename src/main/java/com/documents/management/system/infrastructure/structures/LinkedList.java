package com.documents.management.system.infrastructure.structures;

import com.documents.management.system.models.Document;

public class LinkedList {
    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    private static class Node {
        Document document;
        Node next;

        Node(Document document) {
            this.document = document;
            this.next = null;
        }
    }

    public void add(Document document) {
      // a fazer
    }

    public void remove(Document document) {
          // a fazer
    }

    public Document get(int index) {
        // a fazer
        return new Document();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(Document document) {
        // a fazer
        return true;
    }
}
