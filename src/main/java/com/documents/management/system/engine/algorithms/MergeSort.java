package com.documents.management.system.engine.algorithms;

import java.util.Comparator;

import com.documents.management.system.engine.factories.SortAlgorithmInterface;
import com.documents.management.system.engine.structures.CustomLinkedList;
import com.documents.management.system.models.Document;

public class MergeSort implements SortAlgorithmInterface {
    @Override
    public CustomLinkedList<Document> sortByName(CustomLinkedList<Document> list) {
        return sort(list, Comparator.comparing(doc -> doc.getTitle().toLowerCase()));
    }

    @Override
    public CustomLinkedList<Document> sortByCreatedAt(CustomLinkedList<Document> list) {
        return sort(list, Comparator.comparing(Document::getCreatedAt));
    }

    @Override
    public CustomLinkedList<Document> sortByFileSize(CustomLinkedList<Document> list) {
        return sort(list, Comparator.comparingLong(Document::getSizeInBytes));
    }

    private CustomLinkedList<Document> sort(CustomLinkedList<Document> list, Comparator<Document> comparator) {
        if (list == null || list.isEmpty() || list.size == 1) {
            return list;
        }

        CustomLinkedList<Document> left = new CustomLinkedList<>();
        CustomLinkedList<Document> right = new CustomLinkedList<>();

        int mid = list.size / 2;
        CustomLinkedList.Node<Document> current = list.head;
        for (int i = 0; i < mid; i++) {
            left.add(current.value);
            current = current.next;
        }
        while (current != null) {
            right.add(current.value);
            current = current.next;
        }

        left = sort(left, comparator);
        right = sort(right, comparator);

        return merge(left, right, comparator);
    }

    private CustomLinkedList<Document> merge(CustomLinkedList<Document> left, CustomLinkedList<Document> right, Comparator<Document> comparator) {
        CustomLinkedList<Document> merged = new CustomLinkedList<>();
        CustomLinkedList.Node<Document> leftNode = left.head;
        CustomLinkedList.Node<Document> rightNode = right.head;

        while (leftNode != null && rightNode != null) {
            if (comparator.compare(leftNode.value, rightNode.value) <= 0) {
                merged.add(leftNode.value);
                leftNode = leftNode.next;
            } else {
                merged.add(rightNode.value);
                rightNode = rightNode.next;
            }
        }

        while (leftNode != null) {
            merged.add(leftNode.value);
            leftNode = leftNode.next;
        }

        while (rightNode != null) {
            merged.add(rightNode.value);
            rightNode = rightNode.next;
        }

        return merged;
    }
}
