package com.documents.management.system.engine.algorithms;

import com.documents.management.system.engine.factories.SortAlgorithmInterface;
import com.documents.management.system.engine.structures.CustomLinkedList;
import com.documents.management.system.models.Document;

import java.util.Comparator;

public class SelectionSort implements SortAlgorithmInterface {

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

        CustomLinkedList.Node<Document> current = list.head;
        while (current != null) {
            CustomLinkedList.Node<Document> minNode = current;
            CustomLinkedList.Node<Document> next = current.next;

            while (next != null) {
                if (comparator.compare(next.value, minNode.value) < 0) {
                    minNode = next;
                }
                next = next.next;
            }

            if (minNode != current) {
                Document temp = current.value;
                current.value = minNode.value;
                minNode.value = temp;
            }

            current = current.next;
        }

        return list;
    }
}
