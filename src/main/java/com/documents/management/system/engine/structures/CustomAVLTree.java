package com.documents.management.system.engine.structures;

public class CustomAVLTree<T extends Comparable<T>> {

    private class Node {
        T key;
        Node left, right;
        int height;

        Node(T d) {
            key = d;
            height = 1;
        }
    }

    private Node root;

    public void insert(T key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, T key) {
        if (node == null)
            return new Node(key);

        if (key.compareTo(node.key) < 0)
            node.left = insertRec(node.left, key);
        else if (key.compareTo(node.key) > 0)
            node.right = insertRec(node.right, key);
        else
            return node; 

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key.compareTo(node.left.key) < 0)
            return rightRotate(node);

        if (balance < -1 && key.compareTo(node.right.key) > 0)
            return leftRotate(node);

        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public boolean contains(T key) {
        return containsRec(root, key);
    }

    private boolean containsRec(Node node, T key) {
        if (node == null)
            return false;
        if (key.compareTo(node.key) < 0)
            return containsRec(node.left, key);
        else if (key.compareTo(node.key) > 0)
            return containsRec(node.right, key);
        else
            return true;
    }

    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(Node node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.print(node.key + " ");
            inOrderRec(node.right);
        }
    }

    public void addAllToList(CustomLinkedList<T> lista) {
        addAllToListRec(root, lista);
    }

    private void addAllToListRec(Node node, CustomLinkedList<T> list) {
        if (node != null) {
            addAllToListRec(node.left, list);
            list.add(node.key);
            addAllToListRec(node.right, list);
        }
    }

    public CustomLinkedList<T> toLinkedList() {
        CustomLinkedList<T> list = new CustomLinkedList<>();
        
        addAllToList(list);
        
        return list;
    }
}