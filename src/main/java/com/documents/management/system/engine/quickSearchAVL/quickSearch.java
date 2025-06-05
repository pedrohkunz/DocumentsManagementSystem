package com.documents.management.system.engine.quickSearchAVL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class quickSearch {

    static String userHome = System.getProperty("user.home");
    static String directory = userHome + "\\Documents\\DocumentsManagementSystem";

    // TESTE
    public static void main(String[] args) throws IOException {
    showWordsByFile();
    searchWordInFiles("bola");
    showAllWords();
    }
    // TESTE

    // Pesquisa a palavra em todos arquivos e mostra em quais arquivos ela aparece
    public static void searchWordInFiles(String palavra) throws IOException {
        Map<String, AVLTree<String>> wordsByFiles = DocumentWordExtractor.wordExtractor(directory);
        boolean found = false;
        for (Map.Entry<String, AVLTree<String>> entry : wordsByFiles.entrySet()) {
            if (entry.getValue().contains(palavra)) {
                System.out.println("Palavra \"" + palavra + "\" encontrada no arquivo: " + entry.getKey());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Palavra \"" + palavra + "\" não encontrada em nenhum arquivo.");
        }
    }

    // Mostra todas as palavras de todos arquivos de forma ordenada
    public static void showAllWords() throws IOException {
        Map<String, AVLTree<String>> wordsByFiles = DocumentWordExtractor.wordExtractor(directory);
        List<String> todasPalavras = new ArrayList<>();
        for (AVLTree<String> avl : wordsByFiles.values()) {
            avl.addAllToList(todasPalavras); // Método na AVLTree
        }
        Collections.sort(todasPalavras);
        System.out.println("Todas as palavras ordenadas: " + todasPalavras);
    }

    // Mostra todas as palavras de cada arquivo de forma individual e ordenada
    public static void showWordsByFile() throws IOException {
        Map<String, AVLTree<String>> wordsByFiles = DocumentWordExtractor.wordExtractor(directory);
        for (Map.Entry<String, AVLTree<String>> entry : wordsByFiles.entrySet()) {
            System.out.print("Arquivo: " + entry.getKey() + " | Palavras ordenadas: ");
            entry.getValue().inOrder();
            System.out.println();
        }
    }
}
