package com.documents.management.system.engine.quickSearchBtree;

import java.util.List;

public class TesteInternoBtree {
    public static void main(String[] args) {
        // Documento de teste interno
        String nomeArquivo = "documento1.txt";
        String conteudo = "bola casa bola rua bola casa rua bola";

        // Cria a Btree
        Btree btree = new Btree();

        // Insere as palavras do documento interno na Btree
        String[] palavras = conteudo.split("\\s+");
        for (int i = 0; i < palavras.length; i++) {
            String palavra = palavras[i];
            if (!palavra.isEmpty()) {
                btree.insert(palavra, nomeArquivo, i);
            }
        }

        // Testa busca de palavras
        buscarPalavra(btree, "bola");
        buscarPalavra(btree, "casa");
        buscarPalavra(btree, "rua");
        buscarPalavra(btree, "carro");
    }

    private static void buscarPalavra(Btree btree, String palavra) {
        List<Btree.occurrence> ocorrencias = btree.search(palavra);
        if (ocorrencias.isEmpty()) {
            System.out.println("Palavra \"" + palavra + "\" não encontrada.");
        } else {
            System.out.println("Ocorrências da palavra \"" + palavra + "\":");
            for (Btree.occurrence o : ocorrencias) {
                System.out.println(o);
            }
        }
        System.out.println();
    }
}