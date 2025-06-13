package com.documents.management.system.engine.structures;

import org.junit.jupiter.api.Test;

public class CustomBtreePlusTest {
    @Test
    public void testInsertAndSearch() {
        CustomBtreePlus<Integer> btree = new CustomBtreePlus<>(3);
        btree.insert(10);
        btree.insert(20);
        btree.insert(5);
        btree.insert(6);
        btree.insert(12);
        btree.insert(30);
        btree.insert(7);
        btree.insert(17);

        assert btree.search(10) != null;
        assert btree.search(20) != null;
        assert btree.search(5) != null;
        assert btree.search(6) != null;
        assert btree.search(12) != null;
        assert btree.search(30) != null;
        assert btree.search(7) != null;
        assert btree.search(17) != null;
    }

    @Test
    public void testInsertAndSearchNonExistent() {
        CustomBtreePlus<Integer> btree = new CustomBtreePlus<>(3);
        btree.insert(10);
        btree.insert(20);
        btree.insert(5);

        assert btree.search(15) == null;
        assert btree.search(25) == null;
    }

    @Test
    public void testInsertAndSearchDuplicates() {
        CustomBtreePlus<Integer> btree = new CustomBtreePlus<>(3);
        btree.insert(10);
        btree.insert(20);
        btree.insert(10);

        assert btree.search(10) != null;
        assert btree.search(20) != null;
    }

    @Test
    public void testInsertAndSplit() {
        CustomBtreePlus<Integer> btree = new CustomBtreePlus<>(3);
        for (int i = 1; i <= 10; i++) {
            btree.insert(i);
        }

        assert btree.search(1) != null;
        assert btree.search(5) != null;
        assert btree.search(10) != null;
        assert btree.search(11) == null;
    }

    @Test
    public void testContains() {
        CustomBtreePlus<Integer> btree = new CustomBtreePlus<>(3);
        btree.insert(10);
        btree.insert(20);
        btree.insert(5);

        assert btree.contains(10);
        assert btree.contains(20);
        assert btree.contains(5);
        assert !btree.contains(15);
    }

    @Test
    public void testEmptyTree() {
        CustomBtreePlus<Integer> btree = new CustomBtreePlus<>(3);
        assert btree.search(10) == null;
        assert !btree.contains(10);
    }

    @Test
    public void testSingleElementTree() {
        CustomBtreePlus<Integer> btree = new CustomBtreePlus<>(3);
        btree.insert(10);
        assert btree.search(10) != null;
        assert btree.contains(10);
        assert !btree.contains(20);
    }

    @Test
    public void testMultipleElementsWithSameKey() {
        CustomBtreePlus<Integer> btree = new CustomBtreePlus<>(3);
        btree.insert(10);
        btree.insert(10);
        btree.insert(20);

        assert btree.search(10) != null;
        assert btree.contains(10);
        assert btree.contains(20);
        assert !btree.contains(30);
    }

    @Test
    public void testToLinkedList() {
        CustomBtreePlus<Integer> btree = new CustomBtreePlus<>(3);
        btree.insert(10);
        btree.insert(20);
        btree.insert(5);
        btree.insert(6);
        btree.insert(12);

        CustomLinkedList<Integer> list = btree.toLinkedList();
        assert list.contains(10);
        assert list.contains(20);
        assert list.contains(5);
        assert list.contains(6);
        assert list.contains(12);
    }

    @Test
    public void testToLinkedListEmpty() {
        CustomBtreePlus<Integer> btree = new CustomBtreePlus<>(3);
        CustomLinkedList<Integer> list = btree.toLinkedList();
        assert list.isEmpty();
    }

    @Test
    public void testToLinkedListSingleElement() {
        CustomBtreePlus<Integer> btree = new CustomBtreePlus<>(3);
        btree.insert(10);
        CustomLinkedList<Integer> list = btree.toLinkedList();
        assert list.size() == 1;
        assert list.contains(10);
    }
}