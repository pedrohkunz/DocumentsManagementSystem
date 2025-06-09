package com.documents.management.system.engine.quickSearchBtree;

import java.util.*;

public class Btree {
    // Ordem mínima da B-tree
    // T é o número mínimo de filhos que um nó pode ter
    private static final int T = 3; 
    private Node root;

    // Classe para armazenar ocorrências de palavras
    public static class occurrence {
        public String arquivo;
        public int posicao;
        public occurrence(String arquivo, int posicao) {
            this.arquivo = arquivo;
            this.posicao = posicao;
        }
        @Override
        public String toString() {
            return "Arquivo: " + arquivo + ", Posição: " + (posicao + 1);
        }
    }

    // Classe para armazenar entradas na B-tree
    private static class Entry implements Comparable<Entry> {
        String palavra;
        List<occurrence> Occurrences = new ArrayList<>();
        Entry(String palavra, occurrence occurrence) {
            this.palavra = palavra;
            this.Occurrences.add(occurrence);
        }
        @Override
        public int compareTo(Entry o) {
            return palavra.compareTo(o.palavra);
        }
    }

    // Classe interna para representar um nó da B-tree
    private static class Node {
        int n = 0;
        Entry[] keys = new Entry[2 * T - 1];
        Node[] children = new Node[2 * T];
        boolean leaf = true;
    }

    public Btree() {
        root = new Node();
    }

    // Método para inserir uma palavra na B-tree
    public void insert(String palavra, String arquivo, int posicao) {
        Node r = root;
        if (r.n == 2 * T - 1) {
            Node s = new Node();
            root = s;
            s.leaf = false;
            s.children[0] = r;
            splitChild(s, 0, r);
            insertNonFull(s, palavra, arquivo, posicao);
        } else {
            insertNonFull(r, palavra, arquivo, posicao);
        }
    }

    // função que insere uma palavra em um no que nao esta cheio
    private void insertNonFull(Node x, String palavra, String arquivo, int posicao) {
        int i = x.n - 1;
        if (x.leaf) {
            while (i >= 0 && palavra.compareTo(x.keys[i].palavra) < 0) {
                x.keys[i + 1] = x.keys[i];
                i--;
            }
            if (i >= 0 && palavra.equals(x.keys[i].palavra)) {
                x.keys[i].Occurrences.add(new occurrence(arquivo, posicao));
            } else {
                x.keys[i + 1] = new Entry(palavra, new occurrence(arquivo, posicao));
                x.n++;
            }
        } else {
            while (i >= 0 && palavra.compareTo(x.keys[i].palavra) < 0) {
                i--;
            }
            i++;
            if (x.children[i].n == 2 * T - 1) {
                splitChild(x, i, x.children[i]);
                if (palavra.compareTo(x.keys[i].palavra) > 0) {
                    i++;
                }
            }
            insertNonFull(x.children[i], palavra, arquivo, posicao);
        }
    }


    // Método para dividir um nó cheio
    private void splitChild(Node x, int i, Node y) {
        Node z = new Node();
        z.leaf = y.leaf;
        z.n = T - 1;
        for (int j = 0; j < T - 1; j++) {
            z.keys[j] = y.keys[j + T];
        }
        if (!y.leaf) {
            for (int j = 0; j < T; j++) {
                z.children[j] = y.children[j + T];
            }
        }
        y.n = T - 1;
        for (int j = x.n; j >= i + 1; j--) {
            x.children[j + 1] = x.children[j];
        }
        x.children[i + 1] = z;
        for (int j = x.n - 1; j >= i; j--) {
            x.keys[j + 1] = x.keys[j];
        }
        x.keys[i] = y.keys[T - 1];
        x.n++;
    }


    // Método para buscar uma palavra na B-tree e retornar suas ocorrências
    public List<occurrence> search(String palavra) {
        return search(root, palavra);
    }

    private List<occurrence> search(Node x, String palavra) {
        int i = 0;
        while (i < x.n && palavra.compareTo(x.keys[i].palavra) > 0) {
            i++;
        }
        if (i < x.n && palavra.equals(x.keys[i].palavra)) {
            return x.keys[i].Occurrences;
        }
        if (x.leaf) {
            return Collections.emptyList();
        } else {
            return search(x.children[i], palavra);
        }
    }
}