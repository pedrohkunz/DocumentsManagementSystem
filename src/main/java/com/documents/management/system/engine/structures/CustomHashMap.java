package com.documents.management.system.engine.structures;

public class CustomHashMap<K, V> {
    private int size;
    private Node<K, V>[] table;

    private static final float LOAD_FACTOR = 0.75f;

    public CustomHashMap() {
        this.size = 0;
        this.table = new Node[16];
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public void put(K key, V value) {
        resizeIfNeeded();

        int index = getIndex(key);
        Node<K, V> newNode = new Node<>(key, value);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node<K, V> current = table[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                if (current.next == null) {
                    current.next = newNode;
                    break;
                }
                current = current.next;
            }
        }
        size++;
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    public int size() {
        return size;
    }

    public void forEach(java.util.function.BiConsumer<? super K, ? super V> action) {
        for (Node<K, V> node : table) {
            while (node != null) {
                action.accept(node.key, node.value);
                node = node.next;
            }
        }
    }

    private void resizeIfNeeded() {
        if ((float) size / table.length > LOAD_FACTOR) {
            int newCapacity = table.length * 2;
            Node<K, V>[] oldTable = table;
            table = new Node[newCapacity];
            size = 0;

            for (Node<K, V> node : oldTable) {
                while (node != null) {
                    put(node.key, node.value);
                    node = node.next;
                }
            }
        }
    }

}
