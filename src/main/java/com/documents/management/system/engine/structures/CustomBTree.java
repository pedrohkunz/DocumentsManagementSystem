package com.documents.management.system.engine.structures;

public class CustomBTree<T extends Comparable<T>> {
    private static final int DEFAULT_T = 2;
    private int t;
    private Node<T> root;

    public CustomBTree() {
        this(DEFAULT_T);
    }

    public CustomBTree(int t) {
        this.t = t;
        this.root = new Node<>(true);
    }

    private static class Node<T extends Comparable<T>> {
        CustomLinkedList<T> keys;
        CustomLinkedList<Node<T>> children;
        boolean isLeaf;

        Node(boolean isLeaf) {
            this.isLeaf = isLeaf;
            this.keys = new CustomLinkedList<>();
            this.children = new CustomLinkedList<>();
        }

        int findKeyIndex(T key) {
            int idx = 0;
            while (idx < keys.size() && key.compareTo(keys.get(idx)) > 0) {
                idx++;
            }
            return idx;
        }
    }

    public void insert(T key) {
        Node<T> r = root;
        if (r.keys.size() == 2 * t - 1) {
            Node<T> s = new Node<>(false);
            s.children.add(r);
            splitChild(s, 0);
            insertNonFull(s, key);
            root = s;
        } else {
            insertNonFull(r, key);
        }
    }

    private void insertNonFull(Node<T> node, T key) {
        int i = node.keys.size() - 1;
        if (node.isLeaf) {
            for (i = node.keys.size() - 1; i >= 0 && key.compareTo(node.keys.get(i)) < 0; i--) {
            }
            node.keys.insertAt(i + 1, key);
        } else {
            int idx = node.findKeyIndex(key);
            Node<T> child = node.children.get(idx);
            if (child.keys.size() == 2 * t - 1) {
                splitChild(node, idx);
                if (key.compareTo(node.keys.get(idx)) > 0) {
                    idx++;
                }
            }
            insertNonFull(node.children.get(idx), key);
        }
    }

    private void splitChild(Node<T> parent, int i) {
        Node<T> y = parent.children.get(i);
        Node<T> z = new Node<>(y.isLeaf);

        T middleKey = y.keys.get(t - 1);

        for (int j = 0; j < t - 1; j++) {
            z.keys.add(y.keys.get(t + j));
        }

        if (!y.isLeaf) {
            for (int j = 0; j < t; j++) {
                z.children.add(y.children.get(t + j));
            }
        }

        while (y.keys.size() > t - 1) {
            y.keys.removeLast();
        }
        while (!y.isLeaf && y.children.size() > t) {
            y.children.removeLast();
        }

        parent.children.insertAt(i + 1, z);
        parent.keys.insertAt(i, middleKey);
    }

    public void traverse() {
        traverse(root);
    }

    private void traverse(Node<T> node) {
        for (int i = 0; i < node.keys.size(); i++) {
            if (!node.isLeaf) {
                traverse(node.children.get(i));
            }
            System.out.print(node.keys.get(i) + " ");
        }

        if (!node.isLeaf) {
            traverse(node.children.get(node.children.size() - 1));
        }
    }

    public boolean contains(T key) {
        return contains(root, key);
    }

    private boolean contains(Node<T> node, T key) {
        int idx = node.findKeyIndex(key);
        if (idx < node.keys.size() && node.keys.get(idx).compareTo(key) == 0) {
            return true;
        } else if (node.isLeaf) {
            return false;
        } else {
            return contains(node.children.get(idx), key);
        }
    }

    private void collectValues(Node<T> node, CustomLinkedList<T> list) {
        for (int i = 0; i < node.keys.size(); i++) {
            if (!node.isLeaf) {
                collectValues(node.children.get(i), list);
            }
            list.add(node.keys.get(i));
        }
        if (!node.isLeaf) {
            collectValues(node.children.get(node.children.size() - 1), list);
        }
    }

    public CustomLinkedList<T> toLinkedList() {
        CustomLinkedList<T> list = new CustomLinkedList<T>();
        collectValues(root, list);
        return list;
    }
}
