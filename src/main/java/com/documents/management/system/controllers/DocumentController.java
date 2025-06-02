package com.documents.management.system.controllers;

import com.documents.management.system.models.Document;
import com.documents.management.system.views.dialogs.GenericDialog;

public class DocumentController {
    public void createDocument(String title, String content) {
        if(!isValidFields(title, content)) {
            return;
        }

        try {
            Document document = new Document(title, content).save();
            GenericDialog.create("Documento criado com sucesso!", "Documento '" + document.getTitle() + "' cadastrado com sucesso!");
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

        if (content == null || content.isEmpty()) {
            GenericDialog.create("Erro", "Preencha o conteúdo do documento.");
            return false;
        }

        return true;
    }
}
