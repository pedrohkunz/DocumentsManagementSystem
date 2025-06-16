package com.documents.management.system.engine.factories;

import com.documents.management.system.engine.algorithms.HeapSort;
import com.documents.management.system.engine.algorithms.MergeSort;
import com.documents.management.system.engine.algorithms.QuickSort;
import com.documents.management.system.engine.algorithms.SelectionSort;
import com.documents.management.system.engine.structures.CustomLinkedList;
import com.documents.management.system.models.Document;

public class SortAlgorithmFactory {
    public static SortAlgorithmInterface createSortAlgorithm(String algorithmType) {
        switch(algorithmType) {
            case "HeapSort":
                return new HeapSort();
            case "MergeSort":
                return new MergeSort();
            case "QuickSort":
                return new QuickSort();
            case "SelectionSort":
                return new SelectionSort();
            default:
                throw new IllegalArgumentException("Unknown sorting algorithm type: " + algorithmType);
        }
    }

    public static SortAlgorithmInterface createOptimizedSortAlgorithm(CustomLinkedList<Document> list) {
        if (list.size() <= 10) {
            return new SelectionSort();
        }

        if (isPartiallySorted(list) || hasManyDuplicates(list)) {
            return new MergeSort();
        }

        if (list.size() > 1000) {
            return new HeapSort();
        }

        return new QuickSort();
    }

    private static boolean isPartiallySorted(CustomLinkedList<Document> list) {
        if (list.size() < 2) return false;

        int sortedCount = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).getTitle().compareTo(list.get(i + 1).getTitle()) <= 0) {
                sortedCount++;
            }
        }
        
        return sortedCount >= list.size() / 2;
    }

    private static boolean hasManyDuplicates(CustomLinkedList<Document> list) {
        if (list.size() < 2) return false;

        int duplicateCount = 0;
        for (int i = 0; i < list.size(); i++) {
            Document current = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                if (current.getTitle().equals(list.get(j).getTitle())) {
                    duplicateCount++;
                    if (duplicateCount > list.size() / 10) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
