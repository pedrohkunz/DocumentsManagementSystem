package com.documents.management.system.models;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;

import com.documents.management.system.common.Utils;
import com.documents.management.system.engine.algorithms.Huffman;
import com.documents.management.system.engine.singletons.DocumentAVLTreeSingleton;
import com.documents.management.system.engine.singletons.DocumentBTreeSingleton;
import com.documents.management.system.engine.singletons.DocumentBtreePlusSingleton;
import com.documents.management.system.engine.singletons.DocumentHashMapSingleton;
import com.documents.management.system.engine.singletons.DocumentLinkedListSingleton;
import com.documents.management.system.engine.structures.CustomAVLTree;
import com.documents.management.system.engine.structures.CustomBTree;
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
    private LocalDateTime createdAt;
    private Long sizeInBytes;

    public static final String DOCUMENTS_FOLDER = Utils.getDocumentsDirectory() + "/DocumentsManagementSystem/";
    public static final String DOCUMENTS_FILE_EXTENSION = ".txt";

    public Document(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.sizeInBytes = 0L;
    }

    public Document save() {
        this.validateDocument();
        this.createDocumentsFolderIfNotExists();

        try {
            String compressedContent = Huffman.compress(content);

            FileWriter fileWriter = new FileWriter(DOCUMENTS_FOLDER + title + DOCUMENTS_FILE_EXTENSION);
            fileWriter.write(compressedContent);
            fileWriter.close();

            BasicFileAttributes attrs = Files.readAttributes(Paths.get(DOCUMENTS_FOLDER + title + DOCUMENTS_FILE_EXTENSION), BasicFileAttributes.class);
            this.createdAt = LocalDateTime.ofInstant(attrs.creationTime().toInstant(), java.time.ZoneId.systemDefault());
            this.sizeInBytes = Files.size(Paths.get(DOCUMENTS_FOLDER + title + DOCUMENTS_FILE_EXTENSION));

            CustomBtreePlus<Document> btreePlus = DocumentBtreePlusSingleton.getInstance();
            btreePlus.insert(this);

            CustomAVLTree<Document> avlTree = DocumentAVLTreeSingleton.getInstance();
            avlTree.insert(this);

            CustomBTree<Document> btree = DocumentBTreeSingleton.getInstance();
            btree.insert(this);

            CustomHashMap<String, Document> documentsMap = DocumentHashMapSingleton.getInstance();
            documentsMap.put(title, this);

            CustomLinkedList<Document> documentsList = DocumentLinkedListSingleton.getInstance();
            documentsList.add(this);
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

        return quickSearchBtree(keyword);
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
            CustomAVLTree<String> words = extractWordsToAVL(document.getContent());
            wordsByFiles.put(document, words);
        });

        return wordsByFiles;
    }

    private CustomAVLTree<String> extractWordsToAVL(String content) {
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
        CustomLinkedList<Document> documents = new CustomLinkedList<Document>();

        CustomHashMap<Document, CustomBTree<String>> wordsByFiles = indexWordsByDocumentFromBTree();
        wordsByFiles.forEach((document, words) -> {
            if (words.contains(keyword)) {
                documents.add(document);
            }
        });
    
        return documents;
    }

    private CustomHashMap<Document, CustomBTree<String>> indexWordsByDocumentFromBTree() {
        CustomHashMap<Document, CustomBTree<String>> wordsByFiles = new CustomHashMap<Document, CustomBTree<String>>();

        CustomBTree<Document> tree = DocumentBTreeSingleton.getInstance();
        tree.toLinkedList().forEach(document -> {
            CustomBTree<String> words = extractWordsToBTree(document.getContent());
            wordsByFiles.put(document, words);
        });

        return wordsByFiles;
    }

    private CustomBTree<String> extractWordsToBTree(String content) {
        CustomBTree<String> tree = new CustomBTree<String>();
        
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

    public static void populateStructures() {
        CustomBtreePlus<Document> btreePlus = new CustomBtreePlus<Document>();
        CustomAVLTree<Document> avlTree = new CustomAVLTree<Document>();
        CustomBTree<Document> btree = new CustomBTree<Document>();
        CustomHashMap<String, Document> documentsHashMap = new CustomHashMap<String, Document>();
        CustomLinkedList<Document> documentsLinkedList = new CustomLinkedList<Document>();

        try {
            Files.list(Paths.get(DOCUMENTS_FOLDER))
                .filter(path -> path.toString().endsWith(DOCUMENTS_FILE_EXTENSION))
                .forEach(path -> {
                    String fileName = path.getFileName().toString();
                    String title = fileName.substring(0, fileName.length() - DOCUMENTS_FILE_EXTENSION.length());
                    
                    String content = "";
                    try {
                        content = Huffman.decode(new String(Files.readAllBytes(path)));
                        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
                        LocalDateTime createdAt = LocalDateTime.ofInstant(attrs.creationTime().toInstant(), java.time.ZoneId.systemDefault());

                        Document document = new Document(title, content, createdAt, Files.size(path));

                        btreePlus.insert(document);
                        avlTree.insert(document);
                        btree.insert(document);
                        documentsHashMap.put(title, document);
                        documentsLinkedList.add(document);
                    } catch (Exception e) {
                        throw new RuntimeException("Error populating structures: " + e.getMessage(), e);
                    }
                });
        } catch (Exception e) {
            throw new RuntimeException("Error loading documents: " + e.getMessage(), e);
        }

        DocumentBtreePlusSingleton.setInstance(btreePlus);
        DocumentAVLTreeSingleton.setInstance(avlTree);
        DocumentBTreeSingleton.setInstance(btree);
        DocumentHashMapSingleton.setInstance(documentsHashMap);
        DocumentLinkedListSingleton.setInstance(documentsLinkedList);
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