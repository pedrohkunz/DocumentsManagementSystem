package com.documents.management.system.engine.singletons;

import com.documents.management.system.engine.structures.CustomAVLTree;
import com.documents.management.system.models.Document;

public class DocumentAVLTreeSingleton {
    private static CustomAVLTree<Document> instance;

    private DocumentAVLTreeSingleton() {
    }

    public static CustomAVLTree<Document> getInstance() {
        if (instance == null) {
            instance = new CustomAVLTree<Document>();
        }
        return instance;
    }

    public static void resetInstance() {
        instance = new CustomAVLTree<>();
    }

    public static void setInstance(CustomAVLTree<Document> avlTree) {
        instance = avlTree;
    }
}
