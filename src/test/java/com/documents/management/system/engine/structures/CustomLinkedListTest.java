package com.documents.management.system.engine.structures;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CustomLinkedListTest {
    @Test
    public void testAddElement() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");
        list.add("Element 2");
        list.add("Element 3");

        assertTrue(list.contains("Element 1"));
        assertTrue(list.contains("Element 2"));
        assertTrue(list.contains("Element 3"));
    }

    @Test
    public void testRemoveElement() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");
        list.add("Element 2");
        list.add("Element 3");

        list.remove("Element 2");

        assertTrue(!list.contains("Element 2"));
        assertTrue(list.contains("Element 1"));
        assertTrue(list.contains("Element 3"));
    }

    @Test
    public void testContainsElement() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");
        list.add("Element 2");

        assertTrue(list.contains("Element 1"));
        assertTrue(list.contains("Element 2"));
        assertTrue(!list.contains("Element 3"));
    }

    @Test
    public void testSize() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");
        list.add("Element 2");
        list.add("Element 3");

        assertTrue(list.size() == 3);

        list.remove("Element 2");
        assertTrue(list.size() == 2);
    }

    @Test
    public void testClear() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");
        list.add("Element 2");

        list.clear();

        assertTrue(list.size() == 0);
        assertTrue(!list.contains("Element 1"));
        assertTrue(!list.contains("Element 2"));
    }

    @Test
    public void testGetElement() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");
        list.add("Element 2");
        list.add("Element 3");

        assertTrue(list.get(0).equals("Element 1"));
        assertTrue(list.get(1).equals("Element 2"));
        assertTrue(list.get(2).equals("Element 3"));
    }

    @Test
    public void testGetElementOutOfBounds() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");
        list.add("Element 2");

        try {
            list.get(2);
            assertTrue(false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testIsEmpty() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        assertTrue(list.isEmpty());

        list.add("Element 1");
        assertTrue(!list.isEmpty());

        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testToMatrix() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");
        list.add("Element 2");

        String[][] matrix = list.toMatrix();
        assertTrue(matrix.length == 2);
        assertTrue(matrix[0][0].equals("Element 1"));
        assertTrue(matrix[1][0].equals("Element 2"));
    }

    @Test
    public void testToMatrixEmpty() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        String[][] matrix = list.toMatrix();
        assertTrue(matrix.length == 0);
    }

    @Test
    public void testRemoveNonExistentElement() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");
        list.add("Element 2");

        list.remove("Element 3");

        assertTrue(list.size() == 2);
        assertTrue(list.contains("Element 1"));
        assertTrue(list.contains("Element 2"));
    }

    @Test
    public void testRemoveFromEmptyList() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.remove("Element 1");
        assertTrue(list.size() == 0);
    }

    @Test
    public void testInsertAt() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");
        list.add("Element 2");
        list.add("Element 3");

        list.insertAt(1, "Inserted Element");

        assertTrue(list.get(1).equals("Inserted Element"));
        assertTrue(list.get(2).equals("Element 2"));
    }

    @Test
    public void testInsertAtOutOfBounds() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");

        try {
            list.insertAt(2, "Inserted Element");
            assertTrue(false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testInsertAtNegativeIndex() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");

        try {
            list.insertAt(-1, "Inserted Element");
            assertTrue(false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testRemoveLast() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");
        list.add("Element 2");
        list.add("Element 3");

        list.removeLast();

        assertTrue(list.size() == 2);
        assertTrue(list.contains("Element 1"));
        assertTrue(list.contains("Element 2"));
        assertTrue(!list.contains("Element 3"));
    }

    @Test
    public void testRemoveLastFromEmptyList() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.removeLast();
        assertTrue(list.size() == 0);
    }

    @Test
    public void testRemoveLastSingleElement() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");

        list.removeLast();

        assertTrue(list.size() == 0);
        assertTrue(!list.contains("Element 1"));
    }

    @Test
    public void testForEach() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");
        list.add("Element 2");
        list.add("Element 3");

        StringBuilder result = new StringBuilder();
        list.forEach(value -> result.append(value).append(" "));

        assertTrue(result.toString().trim().equals("Element 1 Element 2 Element 3"));
    }

    @Test
    public void testForEachEmptyList() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        StringBuilder result = new StringBuilder();
        list.forEach(value -> result.append(value).append(" "));

        assertTrue(result.toString().trim().isEmpty());
    }

    @Test
    public void testRemoveAt() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");
        list.add("Element 2");
        list.add("Element 3");

        list.removeAt(1);

        assertTrue(list.size() == 2);
        assertTrue(list.get(0).equals("Element 1"));
        assertTrue(list.get(1).equals("Element 3"));
    }

    @Test
    public void testRemoveAtOutOfBounds() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");
        list.add("Element 2");

        try {
            list.removeAt(2);
            assertTrue(false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testRemoveAtNegativeIndex() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Element 1");

        try {
            list.removeAt(-1);
            assertTrue(false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }
}