package com.documents.management.system.engine.algorithms;

import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Huffman {
    private static final String SEPARATOR = "::SEPARATOR::";

    private static class Node implements Comparable<Node> {
        char ch;
        int freq;
        Node left, right;

        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.freq, other.freq);
        }
    }

    private static Map<Character, String> buildCodes(Node root) {
        Map<Character, String> codes = new HashMap<>();
        buildCodeHelper(root, "", codes);
        return codes;
    }

    private static void buildCodeHelper(Node node, String code, Map<Character, String> codes) {
        if (node == null) return;
        if (node.isLeaf()) {
            codes.put(node.ch, code);
        }
        buildCodeHelper(node.left, code + '0', codes);
        buildCodeHelper(node.right, code + '1', codes);
    }

    public static String compress(String data) {
        if (data == null || data.isEmpty()) return "";

        Map<Character, Integer> frequency = new HashMap<>();
        for (char c : data.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            pq.offer(new Node(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.offer(new Node('\0', left.freq + right.freq, left, right));
        }

        Node root = pq.poll();
        Map<Character, String> codes = buildCodes(root);

        StringBuilder encoded = new StringBuilder();
        for (char c : data.toCharArray()) {
            encoded.append(codes.get(c));
        }

        Gson gson = new Gson();
        String codeMapJson = gson.toJson(codes);
        return codeMapJson + SEPARATOR + encoded;
    }

    public static String decode(String input) {
        if (input == null || !input.contains(SEPARATOR)) return "";

        String[] parts = input.split(SEPARATOR, 2);
        String codeMapJson = parts[0];
        String encodedData = parts[1];

        Gson gson = new Gson();
        Map<String, String> invertedMap = gson.fromJson(codeMapJson, new TypeToken<Map<String, String>>(){}.getType());


        Map<String, Character> reversedMap = new HashMap<>();
        for (Map.Entry<String, String> entry : invertedMap.entrySet()) {
            reversedMap.put(entry.getValue(), entry.getKey().charAt(0));
        }

        StringBuilder currentCode = new StringBuilder();
        StringBuilder decoded = new StringBuilder();

        for (char bit : encodedData.toCharArray()) {
            currentCode.append(bit);
            if (reversedMap.containsKey(currentCode.toString())) {
                decoded.append(reversedMap.get(currentCode.toString()));
                currentCode.setLength(0);
            }
        }

        return decoded.toString();
    }
}
