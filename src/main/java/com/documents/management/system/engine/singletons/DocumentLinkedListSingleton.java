package com.documents.management.system.engine.singletons;

import com.documents.management.system.engine.structures.CustomLinkedList;
import com.documents.management.system.models.Document;

public class DocumentLinkedListSingleton {
    private static CustomLinkedList<Document> instance;

    private DocumentLinkedListSingleton() {
    }

    public static CustomLinkedList<Document> getInstance() {
        if (instance == null) {
            instance = new CustomLinkedList<Document>();
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    public static void setInstance(CustomLinkedList<Document> linkedList) {
        instance = linkedList;
    }
}
