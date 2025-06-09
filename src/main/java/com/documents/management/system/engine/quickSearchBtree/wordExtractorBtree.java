package com.documents.management.system.engine.quickSearchBtree;

import java.nio.file.*;

import com.documents.management.system.engine.algorithms.Huffman;

import java.io.IOException;

public class wordExtractorBtree {
    public static Btree extractWords(String directory) throws IOException {
        Btree btree = new Btree();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directory), "*.txt")) {
            for (Path file : stream) {
                String decodedContent = Huffman.decode(Files.readString(file));
                String clean = decodedContent.replaceAll("[^\\p{L}\\p{M}\\s]", " ");
                String[] split = clean.split("\\s+");
                for (int i = 0; i < split.length; i++) {
                    String word = split[i];
                    if (!word.isEmpty()) {
                        btree.insert(word, file.getFileName().toString(), i);
                    }
                }
            }
        }
        return btree;
    }
}