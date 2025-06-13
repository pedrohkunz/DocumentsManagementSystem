package com.documents.management.system.engine.structures;

import org.junit.jupiter.api.Test;

public class CustomAVLTreeTest {
    @Test
    public void testInsertAndContains() {
        CustomAVLTree<Integer> avlTree = new CustomAVLTree<>();
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(5);
        avlTree.insert(6);
        avlTree.insert(12);
        avlTree.insert(30);
        avlTree.insert(7);
        avlTree.insert(17);

        assert avlTree.contains(10);
        assert avlTree.contains(20);
        assert avlTree.contains(5);
        assert avlTree.contains(6);
        assert avlTree.contains(12);
        assert avlTree.contains(30);
        assert avlTree.contains(7);
        assert avlTree.contains(17);
    }

    @Test
    public void testInsertAndContainsNonExistent() {
        CustomAVLTree<Integer> avlTree = new CustomAVLTree<>();
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(5);

        assert !avlTree.contains(15);
        assert !avlTree.contains(25);
    }

    @Test
    public void testInsertAndContainsDuplicates() {
        CustomAVLTree<Integer> avlTree = new CustomAVLTree<>();
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(10);

        assert avlTree.contains(10);
        assert avlTree.contains(20);
    }

    @Test
    public void testInsertAndBalance() {
        CustomAVLTree<Integer> avlTree = new CustomAVLTree<>();
        for (int i = 1; i <= 10; i++) {
            avlTree.insert(i);
        }

        assert avlTree.contains(1);
        assert avlTree.contains(5);
        assert avlTree.contains(10);
        assert !avlTree.contains(11);
    }

    @Test
    public void testContains() {
        CustomAVLTree<Integer> avlTree = new CustomAVLTree<>();
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(5);

        assert avlTree.contains(10);
        assert avlTree.contains(20);
        assert avlTree.contains(5);
    }

    @Test
    public void testInsertAndSearchNonExistent() {
        CustomAVLTree<Integer> avlTree = new CustomAVLTree<>();
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(5);

        assert !avlTree.contains(15);
        assert !avlTree.contains(25);
    }

    @Test
    public void testInsertAndSearchDuplicates() {
        CustomAVLTree<Integer> avlTree = new CustomAVLTree<>();
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(10);

        assert avlTree.contains(10);
        assert avlTree.contains(20);
    }

    @Test
    public void testInsertAndSplit() {
        CustomAVLTree<Integer> avlTree = new CustomAVLTree<>();
        for (int i = 1; i <= 10; i++) {
            avlTree.insert(i);
        }

        assert avlTree.contains(1);
        assert avlTree.contains(5);
        assert avlTree.contains(10);
        assert !avlTree.contains(11);
    }

    @Test
    public void testContainsAfterMultipleInsertions() {
        CustomAVLTree<Integer> avlTree = new CustomAVLTree<>();
        for (int i = 1; i <= 100; i++) {
            avlTree.insert(i);
        }

        for (int i = 1; i <= 100; i++) {
            assert avlTree.contains(i);
        }
    }

    @Test
    public void testEmptyTree() {
        CustomAVLTree<Integer> avlTree = new CustomAVLTree<>();
        assert !avlTree.contains(10);
    }

    @Test
    public void testSingleElementTree() {
        CustomAVLTree<Integer> avlTree = new CustomAVLTree<>();
        avlTree.insert(10);
        assert avlTree.contains(10);
        assert !avlTree.contains(20);
    }

    @Test
    public void testAddAllToList() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        CustomAVLTree<Integer> avlTree = new CustomAVLTree<>();
        for (int i = 1; i <= 10; i++) {
            avlTree.insert(i);
        }

        avlTree.addAllToList(list);
        for (int i = 1; i <= 10; i++) {
            assert list.contains(i);
        }
        assert list.size() == 10;
    }

    @Test
    public void testToLinkedList() {
        CustomAVLTree<Integer> avlTree = new CustomAVLTree<>();
        for (int i = 1; i <= 10; i++) {
            avlTree.insert(i);
        }

        CustomLinkedList<Integer> list = avlTree.toLinkedList();
        for (int i = 1; i <= 10; i++) {
            assert list.contains(i);
        }
        assert list.size() == 10;
    }
}