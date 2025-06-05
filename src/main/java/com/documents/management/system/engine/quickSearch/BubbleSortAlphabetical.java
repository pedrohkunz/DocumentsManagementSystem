package com.documents.management.system.engine.quickSearch;

public class BubbleSortAlphabetical {

    public static void bubbleSort(String[] wordsArr) {
        boolean swapped;

        for (int i = 0; i < wordsArr.length - 1; i++){
            swapped = false;
            for (int j = 0; j < wordsArr.length - i - 1; j++){
                if (wordsArr[j].compareTo(wordsArr[j + 1]) > 0){
                    String temp = wordsArr[j];
                    wordsArr[j] = wordsArr[j + 1];
                    wordsArr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}
