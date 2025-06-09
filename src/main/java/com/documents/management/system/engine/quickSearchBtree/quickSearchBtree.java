package com.documents.management.system.engine.quickSearchBtree;

import java.io.IOException;
import java.util.List;

public class quickSearchBtree {

    private Btree btree;

    // Construtor que já extrai as palavras dos arquivos do diretório
    public quickSearchBtree(String directory) throws IOException {
        this.btree = wordExtractorBtree.extractWords(directory);
    }

    /**
     * Busca rápida de uma palavra na B-tree e mostra os arquivos onde ela aparece.
     * @param palavra Palavra a ser buscada.
     */
    public void quickSearch(String palavra) {
        List<Btree.occurrence> ocorrencias = btree.search(palavra);
        if (ocorrencias.isEmpty()) {
            System.out.println("Palavra \"" + palavra + "\" não encontrada em nenhum arquivo.");
        } else {
            System.out.println("Ocorrências da palavra \"" + palavra + "\":");
            for (Btree.occurrence o : ocorrencias) {
                System.out.println(o);
            }
        }
    }
}