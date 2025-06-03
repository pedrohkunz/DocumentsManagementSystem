package com.documents.management.system.infrastructure.structures;

import com.documents.management.system.models.Document;

public class LinkedList {
    public Node head;
    public Node tail;
    public int size;

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
        Node newNode = new Node(document);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void remove(Document document) {
        if (head == null)
            return;

        if (head.document.equals(document)) {
            head = head.next;
            if (head == null)
                tail = null;
            size--;
            return;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.document.equals(document)) {
                current.next = current.next.next;
                if (current.next == null)
                    tail = current;
                size--;
                return;
            }
            current = current.next;
        }
    }

    public Document get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.document;
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
        Node current = head;
        while (current != null) {
            if (current.document.equals(document)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public String[][] toMatrix() {
        String[][] matrix = new String[size][2];
        Node current = head;
        int index = 0;
        while (current != null) {
            matrix[index][0] = current.document.getTitle();
            matrix[index][1] = current.document.getContent();
            current = current.next;
            index++;
        }
        return matrix;
    }
    
}
