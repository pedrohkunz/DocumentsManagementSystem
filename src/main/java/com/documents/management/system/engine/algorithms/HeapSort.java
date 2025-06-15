package com.documents.management.system.engine.algorithms;

import com.documents.management.system.engine.factories.SortAlgorithmInterface;
import com.documents.management.system.engine.structures.CustomLinkedList;
import com.documents.management.system.models.Document;

public class HeapSort implements SortAlgorithmInterface {
    private void heapify(Document[] arr, int n, int i, String sortBy) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && compare(arr[left], arr[largest], sortBy) > 0)
            largest = left;
        if (right < n && compare(arr[right], arr[largest], sortBy) > 0)
            largest = right;

        if (largest != i) {
            Document temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest, sortBy);
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

    private CustomLinkedList<Document> heapSort(CustomLinkedList<Document> list, String sortBy) {
        if (list == null || list.isEmpty()) return list;

        Document[] arr = new Document[list.size()];
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            arr[index++] = list.get(i);
        }

        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i, sortBy);

        for (int i = n - 1; i > 0; i--) {
            Document temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0, sortBy);
        }

        CustomLinkedList<Document> sortedList = new CustomLinkedList<>();
        for (Document doc : arr) {
            sortedList.add(doc);
        }

        return sortedList;
    }

    @Override
    public CustomLinkedList<Document> sortByName(CustomLinkedList<Document> list) {
        return heapSort(list, "name");
    }

    @Override
    public CustomLinkedList<Document> sortByCreatedAt(CustomLinkedList<Document> list) {
        return heapSort(list, "createdAt");
    }

    @Override
    public CustomLinkedList<Document> sortByFileSize(CustomLinkedList<Document> list) {
        return heapSort(list, "fileSize");
    }
}