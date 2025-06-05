package com.documents.management.system.engine.quickSearchAVL;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.documents.management.system.engine.algorithms.Huffman;

public class DocumentWordExtractor {
    
    public static Map<String, AVLTree<String>> wordExtractor(String directory) throws IOException {
        Map<String, AVLTree<String>> wordsByFiles = new HashMap<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directory), "*.txt")) {
            for (Path file : stream) {
                AVLTree<String> avl = new AVLTree<>();
                String decodedContent = Huffman.decode(Files.readString(file));
                String clean = decodedContent.replaceAll("[^\\p{L}\\p{M}\\s]", " ");
                String[] split = clean.split("\\s+");
                for (String word : split) {
                    if (!word.isEmpty()) {
                        avl.insert(word);
                    }
                }
                wordsByFiles.put(file.getFileName().toString(), avl);
            }
        }
        return wordsByFiles;
    }

}
