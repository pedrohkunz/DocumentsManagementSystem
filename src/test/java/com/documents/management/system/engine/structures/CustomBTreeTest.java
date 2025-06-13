package com.documents.management.system.engine.structures;

import org.junit.jupiter.api.Test;

public class CustomBTreeTest {
    @Test
    public void testInsertAndContains() {
        CustomBTree<Integer> bTree = new CustomBTree<>(2);
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(5);
        bTree.insert(6);
        bTree.insert(12);
        bTree.insert(30);
        bTree.insert(7);
        bTree.insert(17);

        assert bTree.contains(10);
        assert bTree.contains(20);
        assert bTree.contains(5);
        assert bTree.contains(6);
        assert bTree.contains(12);
        assert bTree.contains(30);
        assert bTree.contains(7);
        assert bTree.contains(17);
    }

    @Test
    public void testInsertAndContainsNonExistent() {
        CustomBTree<Integer> bTree = new CustomBTree<>(2);
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(5);

        assert !bTree.contains(15);
        assert !bTree.contains(25);
    }

    @Test
    public void testInsertAndContainsDuplicates() {
        CustomBTree<Integer> bTree = new CustomBTree<>(2);
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(10);

        assert bTree.contains(10);
        assert bTree.contains(20);
    }

    @Test
    public void testInsertAndSplit() {
        CustomBTree<Integer> bTree = new CustomBTree<>(2);
        for (int i = 1; i <= 10; i++) {
            bTree.insert(i);
        }

        assert bTree.contains(1);
        assert bTree.contains(5);
        assert bTree.contains(10);
        assert !bTree.contains(11);
    }

    @Test
    public void testContains() {
        CustomBTree<Integer> bTree = new CustomBTree<>(2);
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(5);

        assert bTree.contains(10);
        assert bTree.contains(20);
        assert bTree.contains(5);
    }

    @Test
    public void testContainsNonExistent() {
        CustomBTree<Integer> bTree = new CustomBTree<>(2);
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(5);

        assert !bTree.contains(15);
        assert !bTree.contains(25);
    }

    @Test
    public void testEmptyTree() {
        CustomBTree<Integer> bTree = new CustomBTree<>(2);
        assert !bTree.contains(10);
    }

    @Test
    public void testSingleElementTree() {
        CustomBTree<Integer> bTree = new CustomBTree<>(2);
        bTree.insert(10);
        assert bTree.contains(10);
        assert !bTree.contains(20);
    }

    @Test
    public void testMultipleElements() {
        CustomBTree<Integer> bTree = new CustomBTree<>(2);
        for (int i = 1; i <= 100; i++) {
            bTree.insert(i);
        }

        for (int i = 1; i <= 100; i++) {
            assert bTree.contains(i);
        }
        assert !bTree.contains(101);
    }

    @Test
    public void testToLinkedList() {
        CustomBTree<Integer> bTree = new CustomBTree<>(2);
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(5);
        bTree.insert(6);
        bTree.insert(12);

        CustomLinkedList<Integer> linkedList = bTree.toLinkedList();
        assert linkedList.size() == 5;
        assert linkedList.contains(10);
        assert linkedList.contains(20);
        assert linkedList.contains(5);
        assert linkedList.contains(6);
        assert linkedList.contains(12);
    }

    @Test
    public void testToLinkedListEmpty() {
        CustomBTree<Integer> bTree = new CustomBTree<>(2);
        CustomLinkedList<Integer> linkedList = bTree.toLinkedList();
        assert linkedList.isEmpty();
    }

    @Test
    public void testToLinkedListSingleElement() {
        CustomBTree<Integer> bTree = new CustomBTree<>(2);
        bTree.insert(10);
        CustomLinkedList<Integer> linkedList = bTree.toLinkedList();
        assert linkedList.size() == 1;
        assert linkedList.contains(10);
    }

    @Test
    public void testToLinkedListMultipleElements() {
        CustomBTree<Integer> bTree = new CustomBTree<>(2);
        for (int i = 1; i <= 5; i++) {
            bTree.insert(i);
        }
        CustomLinkedList<Integer> linkedList = bTree.toLinkedList();
        assert linkedList.size() == 5;
        for (int i = 1; i <= 5; i++) {
            assert linkedList.contains(i);
        }
    }
}