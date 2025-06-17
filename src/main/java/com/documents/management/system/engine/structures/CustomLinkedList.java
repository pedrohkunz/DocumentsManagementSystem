package com.documents.management.system.engine.structures;

import com.documents.management.system.common.Utils;
import com.documents.management.system.models.Document;

public class CustomLinkedList<T> {
    public Node<T> head;
    public Node<T> tail;
    public int size;

    public CustomLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public static class Node<T> {
        public T value;
        public Node<T> next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    public void add(T value) {
        Node<T> newNode = new Node<T>(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void remove(T value) {
        if (head == null)
            return;

        if (head.value.equals(value)) {
            head = head.next;
            if (head == null)
                tail = null;
            size--;
            return;
        }

        Node<T> current = head;
        while (current.next != null) {
            if (current.next.value.equals(value)) {
                current.next = current.next.next;
                if (current.next == null)
                    tail = current;
                size--;
                return;
            }
            current = current.next;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
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

    public boolean contains(T value) {
        Node<T> current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public String[][] toMatrix() {
        String[][] matrix = new String[size][5];
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            if (current.value instanceof Document) {
                Document doc = (Document) current.value;
                matrix[index][0] = doc.getTitle();
                matrix[index][1] = doc.getContent();
                matrix[index][2] = String.valueOf(doc.getContentSize());
                matrix[index][3] = String.valueOf(doc.getContentSizeAfterCompress());
                matrix[index][4] = Utils.formatDate(doc.getCreatedAt());
            } else {
                matrix[index][0] = current.value.toString();
                matrix[index][1] = "";
            }
            current = current.next;
            index++;
        }

        return matrix;
    }

    public void forEach(java.util.function.Consumer<? super T> action) {
        Node<T> current = head;
        while (current != null) {
            action.accept(current.value);
            current = current.next;
        }
    }

    public void insertAt(int index, T value) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        Node<T> newNode = new Node<>(value);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            if (size == 0) tail = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            if (newNode.next == null) tail = newNode;
        }
        size++;
    }

    public void removeLast() {
        if (size == 0) return;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node<T> current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }
        size--;
    }

    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (index == 0) {
            head = head.next;
            if (head == null) tail = null;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            if (current.next == null) tail = current;
        }

        size--;
    }

    public CustomLinkedList<T> clone() {
        CustomLinkedList<T> clonedList = new CustomLinkedList<>();
        Node<T> current = head;
        while (current != null) {
            clonedList.add(current.value);
            current = current.next;
        }
        return clonedList;
    }

}
