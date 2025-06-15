package com.documents.management.system.engine.algorithms;

import com.documents.management.system.engine.factories.SortAlgorithmInterface;
import com.documents.management.system.engine.structures.CustomLinkedList;
import com.documents.management.system.models.Document;

public class QuickSort implements SortAlgorithmInterface {
    @Override
    public CustomLinkedList<Document> sortByName(CustomLinkedList<Document> list) {
        // Implementation of heap sort by name
        return list; // Placeholder return
    }

    @Override
    public CustomLinkedList<Document> sortByCreatedAt(CustomLinkedList<Document> list) {
        // Implementation of heap sort by created date
        return list; // Placeholder return
    }

    @Override
    public CustomLinkedList<Document> sortByFileSize(CustomLinkedList<Document> list) {
        // Implementation of heap sort by file size
        return list; // Placeholder return
    }
}
