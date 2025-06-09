package com.documents.management.system.engine.singletons;

import com.documents.management.system.engine.structures.CustomBtreePlus;
import com.documents.management.system.models.Document;

public class DocumentBtreePlusSingleton {
    private static CustomBtreePlus<Document> instance;

    private DocumentBtreePlusSingleton() {

    }

    public static CustomBtreePlus<Document> getInstance() {
        if (instance == null) {
            instance = new CustomBtreePlus<Document>();
        }

        return instance;
    }

    public static void resetInstance() {
        instance = new CustomBtreePlus<Document>();
    }

    public static void setInstance(CustomBtreePlus<Document> btreePlus) {
        instance = btreePlus;
    }
}
