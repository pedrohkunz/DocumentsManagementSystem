package com.documents.management.system.models;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.documents.management.system.common.Utils;
import com.documents.management.system.engine.algorithms.Huffman;
import com.documents.management.system.engine.singletons.DocumentAVLTreeSingleton;
import com.documents.management.system.engine.singletons.DocumentBtreePlusSingleton;
import com.documents.management.system.engine.structures.CustomAVLTree;
import com.documents.management.system.engine.structures.CustomBtreePlus;
import com.documents.management.system.engine.structures.CustomHashMap;
import com.documents.management.system.engine.structures.CustomLinkedList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Document implements Comparable<Document> {
    private String title;
    private String content;

    public static final String DOCUMENTS_FOLDER = Utils.getDocumentsDirectory() + "/DocumentsManagementSystem/";
    public static final String DOCUMENTS_FILE_EXTENSION = ".txt";

    public Document save() {
        this.validateDocument();
        this.createDocumentsFolderIfNotExists();

        try {
            String compressedContent = Huffman.compress(content);

            FileWriter fileWriter = new FileWriter(DOCUMENTS_FOLDER + title + DOCUMENTS_FILE_EXTENSION);
            fileWriter.write(compressedContent);
            fileWriter.close();

            CustomBtreePlus<Document> btreePlus = DocumentBtreePlusSingleton.getInstance();
            btreePlus.insert(this);

            CustomAVLTree<Document> avlTree = DocumentAVLTreeSingleton.getInstance();
            avlTree.insert(this);
        } catch (Exception e) {
            throw new RuntimeException("Error saving document: " + e.getMessage(), e);
        }

        return this;
    }

    public CustomLinkedList<Document> readAllFromBtreePlus() {
        CustomBtreePlus<Document> tree = DocumentBtreePlusSingleton.getInstance();
        CustomLinkedList<Document> documentsList = new CustomLinkedList<Document>();

        try {
            documentsList = tree.toLinkedList();
        } catch (Exception e) {
            throw new RuntimeException("Error reading tree: " + e.getMessage(), e);
        }

        return documentsList;
    }

    public CustomLinkedList<Document> searchByKeyword(String keyword, String selectedSearchMethod) {
        if(selectedSearchMethod == "AVLTree") {
            return quickSearchAVL(keyword);
        }

        if(selectedSearchMethod == "BTree") {
            return quickSearchBtree(keyword);
        }

        return quickSearchBtreePlus(keyword);
    }

    private CustomLinkedList<Document> quickSearchAVL(String keyword) {
        CustomLinkedList<Document> documents = new CustomLinkedList<Document>();

        CustomHashMap<Document, CustomAVLTree<String>> wordsByFiles = indexWordsByDocumentFromAvlTree();
        wordsByFiles.forEach((document, words) -> {
            if (words.contains(keyword)) {
                documents.add(document);
            }
        });
    
        return documents;
    }

     private CustomHashMap<Document, CustomAVLTree<String>> indexWordsByDocumentFromAvlTree() {
        CustomHashMap<Document, CustomAVLTree<String>> wordsByFiles = new CustomHashMap<Document, CustomAVLTree<String>>();

        CustomAVLTree<Document> tree = DocumentAVLTreeSingleton.getInstance();
        tree.toLinkedList().forEach(document -> {
            CustomAVLTree<String> words = extractWords(document.getContent());
            wordsByFiles.put(document, words);
        });

        return wordsByFiles;
    }

    private CustomAVLTree<String> extractWords(String content) {
        CustomAVLTree<String> tree = new CustomAVLTree<String>();
        
        if (content == null || content.isEmpty()) {
            return tree;
        }

        String[] words = content.split("[\\s\\p{Punct}]+");
        for (String word : words) {
            if (!word.isEmpty()) {
                tree.insert(word.toLowerCase());
            }
        }

        return tree;
    }

    private CustomLinkedList<Document> quickSearchBtree(String keyword) {
        return new CustomLinkedList<>(); // todo
    }

    private CustomLinkedList<Document> quickSearchBtreePlus(String keyword) {
        return new CustomLinkedList<Document>(); // todo
    }

    public static void loadAllInBtreePlus() {
        CustomBtreePlus<Document> tree = new CustomBtreePlus<Document>();

        try {
            Files.list(Paths.get(DOCUMENTS_FOLDER))
                .filter(path -> path.toString().endsWith(DOCUMENTS_FILE_EXTENSION))
                .forEach(path -> {
                    String fileName = path.getFileName().toString();
                    String title = fileName.substring(0, fileName.length() - DOCUMENTS_FILE_EXTENSION.length());
                    
                    String content = "";
                    try {
                        content = Huffman.decode(new String(Files.readAllBytes(path)));
                    } catch (Exception e) {
                        throw new RuntimeException("Error reading document: " + e.getMessage(), e);
                    }

                    tree.insert(new Document(title, content));
                });
        } catch (Exception e) {
            throw new RuntimeException("Error loading documents: " + e.getMessage(), e);
        }

        DocumentBtreePlusSingleton.setInstance(tree);
    }

    public static void loadAllInAVLTree() {
        CustomAVLTree<Document> tree = new CustomAVLTree<Document>();

        try {
            Files.list(Paths.get(DOCUMENTS_FOLDER))
                .filter(path -> path.toString().endsWith(DOCUMENTS_FILE_EXTENSION))
                .forEach(path -> {
                    String fileName = path.getFileName().toString();
                    String title = fileName.substring(0, fileName.length() - DOCUMENTS_FILE_EXTENSION.length());
                    
                    String content = "";
                    try {
                        content = Huffman.decode(new String(Files.readAllBytes(path)));
                    } catch (Exception e) {
                        throw new RuntimeException("Error reading document: " + e.getMessage(), e);
                    }

                    tree.insert(new Document(title, content));
                });
        } catch (Exception e) {
            throw new RuntimeException("Error loading documents: " + e.getMessage(), e);
        }

        DocumentAVLTreeSingleton.setInstance(tree);
    }

    private void validateDocument() {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }

        if(Files.exists(Paths.get(DOCUMENTS_FOLDER + title + DOCUMENTS_FILE_EXTENSION))) {
            throw new RuntimeException("Document with title '" + title + "' already exists.");
        }
    }

    private void createDocumentsFolderIfNotExists() {
        if(!Files.exists(Paths.get(DOCUMENTS_FOLDER))) {
            try {
                Files.createDirectories(Paths.get(DOCUMENTS_FOLDER));
            } catch (Exception e) {
                throw new RuntimeException("Error creating documents folder: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public int compareTo(Document otherDocument) {
        if (this.title == null && otherDocument.title == null) return 0;
        if (this.title == null) return -1;
        if (otherDocument.title == null) return 1;
        return this.title.compareTo(otherDocument.title);
    }
}
