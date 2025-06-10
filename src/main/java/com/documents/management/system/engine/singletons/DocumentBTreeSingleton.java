package com.documents.management.system.engine.singletons;

import com.documents.management.system.engine.structures.CustomBTree;
import com.documents.management.system.models.Document;

public class DocumentBTreeSingleton {
    private static CustomBTree<Document> instance;

    private DocumentBTreeSingleton() {
    }

    public static CustomBTree<Document> getInstance() {
        if (instance == null) {
            instance = new CustomBTree<Document>();
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    public static void setInstance(CustomBTree<Document> btreePlus) {
        instance = btreePlus;
    }

}
