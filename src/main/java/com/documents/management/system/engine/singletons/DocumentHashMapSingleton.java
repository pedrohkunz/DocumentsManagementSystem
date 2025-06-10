package com.documents.management.system.engine.singletons;

import com.documents.management.system.engine.structures.CustomHashMap;
import com.documents.management.system.models.Document;

public class DocumentHashMapSingleton {
    private static CustomHashMap<String, Document> instance;

    private DocumentHashMapSingleton() {
    }

    public static CustomHashMap<String, Document> getInstance() {
        if (instance == null) {
            instance = new CustomHashMap<String, Document>();
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }
    
    public static void setInstance(CustomHashMap<String, Document> hashMap) {
        instance = hashMap;
    }
}
