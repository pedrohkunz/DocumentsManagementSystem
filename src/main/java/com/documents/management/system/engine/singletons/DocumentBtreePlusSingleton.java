package com.documents.management.system.engine.singletons;

import com.documents.management.system.engine.structures.BtreePlus;
import com.documents.management.system.models.Document;

public class DocumentBtreePlusSingleton {
    private static BtreePlus<Document> instance;

    private DocumentBtreePlusSingleton() {

    }

    public static BtreePlus<Document> getInstance() {
        if (instance == null) {
            instance = new BtreePlus<Document>();
        }

        return instance;
    }

    public static void resetInstance() {
        instance = new BtreePlus<Document>();
    }

    public static void setInstance(BtreePlus<Document> btreePlus) {
        instance = btreePlus;
    }
}
