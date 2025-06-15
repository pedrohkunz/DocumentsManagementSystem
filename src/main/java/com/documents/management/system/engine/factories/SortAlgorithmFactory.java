package com.documents.management.system.engine.factories;

import com.documents.management.system.engine.algorithms.HeapSort;
import com.documents.management.system.engine.algorithms.MergeSort;
import com.documents.management.system.engine.algorithms.QuickSort;
import com.documents.management.system.engine.algorithms.SelectionSort;

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
}
