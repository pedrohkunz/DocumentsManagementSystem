package com.documents.management.system.engine.factories;

import com.documents.management.system.engine.structures.CustomLinkedList;
import com.documents.management.system.models.Document;

public interface SortAlgorithmInterface {
    CustomLinkedList<Document> sortByName(CustomLinkedList<Document> list);
    CustomLinkedList<Document> sortByCreatedAt(CustomLinkedList<Document> list);
    CustomLinkedList<Document> sortByFileSize(CustomLinkedList<Document> list);
}
