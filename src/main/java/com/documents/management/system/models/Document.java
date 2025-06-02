package com.documents.management.system.models;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.documents.management.system.common.Utils;

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
            FileWriter fileWriter = new FileWriter(DOCUMENTS_FOLDER + title + DOCUMENTS_FILE_EXTENSION);
            fileWriter.write(content);
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException("Error saving document: " + e.getMessage(), e);
        }

        return this;
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
