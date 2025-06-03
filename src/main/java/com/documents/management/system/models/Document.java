package com.documents.management.system.models;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.documents.management.system.common.Utils;
import com.documents.management.system.engine.algorithms.Huffman;
import com.documents.management.system.engine.structures.LinkedList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Document {
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
        } catch (Exception e) {
            throw new RuntimeException("Error saving document: " + e.getMessage(), e);
        }

        return this;
    }

    public LinkedList loadAll() {
        LinkedList documentsList = new LinkedList();

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

                    documentsList.add(new Document(title, content));
                });
        } catch (Exception e) {
            throw new RuntimeException("Error loading documents: " + e.getMessage(), e);
        }

        return documentsList;
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
}
