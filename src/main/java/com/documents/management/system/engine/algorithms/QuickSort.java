package com.documents.management.system.engine.algorithms;

import com.documents.management.system.engine.factories.SortAlgorithmInterface;
import com.documents.management.system.engine.structures.CustomLinkedList;
import com.documents.management.system.models.Document;

public class QuickSort implements SortAlgorithmInterface {
    private int partition(Document[] arr, int low, int high, String sortBy) {
        Document pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot, sortBy) <= 0) {
                i++;
                Document temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Document temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    private void quickSort(Document[] arr, int low, int high, String sortBy) {
        if (low < high) {
            int pi = partition(arr, low, high, sortBy);
            quickSort(arr, low, pi - 1, sortBy);
            quickSort(arr, pi + 1, high, sortBy);
        }
    }

    private int compare(Document a, Document b, String sortBy) {
        switch (sortBy) {
            case "name":
                return a.getTitle().compareTo(b.getTitle());
            case "createdAt":
                return a.getCreatedAt().compareTo(b.getCreatedAt());
            case "fileSize":
                return Long.compare(a.getSizeInBytes(), b.getSizeInBytes());
            default:
                return 0;
        }
    }

    private CustomLinkedList<Document> sort(CustomLinkedList<Document> list, String sortBy) {
        if (list == null || list.isEmpty()) return list;

        Document[] arr = new Document[list.size()];
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            arr[index++] = list.get(i);
        }

        quickSort(arr, 0, arr.length - 1, sortBy);

        CustomLinkedList<Document> sortedList = new CustomLinkedList<>();
        for (Document doc : arr) {
            sortedList.add(doc);
        }

        return sortedList;
    }

    @Override
    public CustomLinkedList<Document> sortByName(CustomLinkedList<Document> list) {
        return sort(list, "name");
    }

    @Override
    public CustomLinkedList<Document> sortByCreatedAt(CustomLinkedList<Document> list) {
        return sort(list, "createdAt");
    }

    @Override
    public CustomLinkedList<Document> sortByFileSize(CustomLinkedList<Document> list) {
        return sort(list, "fileSize");
    }
}