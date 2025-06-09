package com.documents.management.system.engine.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomBtreePlus<T extends Comparable<T>> {
    private static final int DEFAULT_ORDER = 3;

    private final int order;
    private Node root;

    public CustomBtreePlus() {
        this(DEFAULT_ORDER);
    }

    public CustomBtreePlus(int order) {
        this.order = order;
        this.root = new LeafNode();
    }

    private abstract class Node {
        List<T> keys;

        Node() {
            keys = new ArrayList<>();
        }

        abstract boolean isLeaf();
        abstract void insert(T key);
        abstract LeafNode search(T key);
    }

    private class LeafNode extends Node {
        List<T> values;
        LeafNode next;

        LeafNode() {
            super();
            values = new ArrayList<>();
        }

        @Override
        boolean isLeaf() {
            return true;
        }

        @Override
        void insert(T key) {
            int index = Collections.binarySearch(keys, key);
            if (index >= 0) {
                values.set(index, key);
            } else {
                index = -index - 1;
                keys.add(index, key);
                values.add(index, key);
            }

            if (keys.size() > order * 2 - 1) {
                splitLeaf();
            }
        }

        private void splitLeaf() {
            LeafNode newLeaf = new LeafNode();
            int mid = keys.size() / 2;

            newLeaf.keys.addAll(keys.subList(mid, keys.size()));
            newLeaf.values.addAll(values.subList(mid, values.size()));
            keys.subList(mid, keys.size()).clear();
            values.subList(mid, values.size()).clear();

            newLeaf.next = this.next;
            this.next = newLeaf;

            if (this == root) {
                InternalNode newRoot = new InternalNode();
                newRoot.keys.add(newLeaf.keys.get(0));
                newRoot.children.add(this);
                newRoot.children.add(newLeaf);
                root = newRoot;
            } else {
                insertInParent(this, newLeaf.keys.get(0), newLeaf);
            }
        }

        @Override
        LeafNode search(T key) {
            return this;
        }
    }

    private class InternalNode extends Node {
        List<Node> children;

        InternalNode() {
            super();
            children = new ArrayList<>();
        }

        @Override
        boolean isLeaf() {
            return false;
        }

        @Override
        void insert(T key) {
            int i = findChildIndex(key);
            Node child = children.get(i);
            child.insert(key);

            if (children.size() > order * 2) {
                splitInternal();
            }
        }

        private void splitInternal() {
            int mid = keys.size() / 2;
            T midKey = keys.get(mid);

            InternalNode newInternal = new InternalNode();
            newInternal.keys.addAll(keys.subList(mid + 1, keys.size()));
            newInternal.children.addAll(children.subList(mid + 1, children.size()));

            keys.subList(mid, keys.size()).clear();
            children.subList(mid + 1, children.size()).clear();

            if (this == root) {
                InternalNode newRoot = new InternalNode();
                newRoot.keys.add(midKey);
                newRoot.children.add(this);
                newRoot.children.add(newInternal);
                root = newRoot;
            } else {
                insertInParent(this, midKey, newInternal);
            }
        }

        @Override
        LeafNode search(T key) {
            int i = findChildIndex(key);
            return children.get(i).search(key);
        }

        private int findChildIndex(T key) {
            int i = 0;
            while (i < keys.size() && key.compareTo(keys.get(i)) >= 0) {
                i++;
            }
            return i;
        }
    }

    public void insert(T key) {
        root.insert(key);
    }

    public boolean contains(T key) {
        LeafNode leaf = root.search(key);
        return leaf.keys.contains(key);
    }

    public T search(T key) {
        LeafNode leaf = root.search(key);
        int idx = Collections.binarySearch(leaf.keys, key);
        return (idx >= 0) ? leaf.values.get(idx) : null;
    }

    public CustomLinkedList<T> toLinkedList() {
        CustomLinkedList<T> list = new CustomLinkedList<>();
        collectValues(root, list);
        return list;
    }

    private void collectValues(Node node, CustomLinkedList<T> list) {
        if (node.isLeaf()) {
            LeafNode leaf = (LeafNode) node;
            for (int i = 0; i < leaf.keys.size(); i++) {
                list.add(leaf.values.get(i));
            }
        } else {
            InternalNode internal = (InternalNode) node;
            for (int i = 0; i < internal.children.size(); i++) {
                collectValues(internal.children.get(i), list);
                if (i < internal.keys.size()) {
                    list.add(internal.keys.get(i));
                }
            }
        }
    }

    private void insertInParent(Node left, T key, Node right) {
        InternalNode parent = findParent(root, left);
        if (parent == null) {
            InternalNode newRoot = new InternalNode();
            newRoot.keys.add(key);
            newRoot.children.add(left);
            newRoot.children.add(right);
            root = newRoot;
            return;
        }

        int idx = parent.children.indexOf(left);
        parent.keys.add(idx, key);
        parent.children.add(idx + 1, right);

        if (parent.keys.size() > order * 2 - 1) {
            parent.splitInternal();
        }
    }

    private InternalNode findParent(Node current, Node child) {
        if (current.isLeaf()) return null;

        InternalNode internal = (InternalNode) current;
        for (Node node : internal.children) {
            if (node == child) return internal;
            InternalNode found = findParent(node, child);
            if (found != null) return found;
        }

        return null;
    }
    
}
