package com.documents.management.system.controllers;

import com.documents.management.system.engine.structures.CustomLinkedList;
import com.documents.management.system.models.Document;
import com.documents.management.system.views.dialogs.GenericDialog;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DocumentController {
    private Document documentModel = new Document();

    public void createDocument(String title, String content) {
        if (!isValidFields(title, content)) {
            return;
        }

        try {
            new Document(
                title, 
                content
            ).save();
            GenericDialog.create("Documento criado com sucesso!", "Documento cadastrado com sucesso!");
        } catch (RuntimeException e) {
            GenericDialog.create("Erro", "Erro inesperado ao cadastrar o documento.");
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public CustomLinkedList<Document> getAllDocuments() {
        return documentModel.readAllFromBtreePlus();
    }

    private Boolean isValidFields(String title, String content) {
        if (title == null || title.isEmpty()) {
            GenericDialog.create("Erro", "Preencha o título do documento.");
            return false;
        }

        if (Files.exists(Paths.get(Document.DOCUMENTS_FOLDER + title + Document.DOCUMENTS_FILE_EXTENSION))) {
            GenericDialog.create("Erro", "Já existe um documento com o título '" + title + "'.");
            return false;
        }

        if (content == null || content.isEmpty()) {
            GenericDialog.create("Erro", "Preencha o conteúdo do documento.");
            return false;
        }

        return true;
    }

     public CustomLinkedList<Document> searchDocuments(String keyword, String selectedSearchMethod) {
        return documentModel.searchByKeyword(keyword, selectedSearchMethod);
    }
}