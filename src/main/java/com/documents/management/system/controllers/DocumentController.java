package com.documents.management.system.controllers;

import com.documents.management.system.models.Document;
import com.documents.management.system.views.dialogs.GenericDialog;
import com.documents.management.system.views.screens.MainMenuScreen;
import com.documents.management.system.infrastructure.structures.LinkedList;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DocumentController {
    private static DocumentController instance = null;
    private LinkedList documents = new LinkedList();

    private DocumentController() {
    }

    public static DocumentController getInstance() {
        if (instance == null) {
            instance = new DocumentController();
        }
        return instance;
    }

    public void createDocument(String title, String content) {
        if (!isValidFields(title, content)) {
            return;
        }

        try {
            Document document = new Document(title, content).save();
            documents.add(document);
            GenericDialog.create("Documento criado com sucesso!", "Documento '" + document.getTitle() + "' cadastrado com sucesso!");
            
            new MainMenuScreen();
        } catch (RuntimeException e) {
            GenericDialog.create("Erro", "Erro inesperado ao cadastrar o documento.");
            throw new RuntimeException(e.getMessage(), e);
        }
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

    public List<String[]> getAllDocuments() {
        List<String[]> documentList = new ArrayList<>();
        for (int i = 0; i < documents.size(); i++) {
            Document doc = documents.get(i);
            documentList.add(new String[]{doc.getTitle(), doc.getContent()});
        }
        return documentList;
    }
}