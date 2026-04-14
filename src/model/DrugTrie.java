package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DrugTrie {

    private TrieNode root;

    public DrugTrie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null || word.trim().isEmpty()) {
            return;
        }

        TrieNode current = root;
        String normalized = word.toLowerCase();

        for (char c : normalized.toCharArray()) {
            if (!current.containsChild(c)) {
                current.addChild(c, new TrieNode());
            }
            current = current.getChild(c);
        }

        current.setEndOfWord(true);
    }

    public boolean search(String word) {
        if (word == null || word.trim().isEmpty()) {
            return false;
        }

        TrieNode current = root;
        String normalized = word.toLowerCase();

        for (char c : normalized.toCharArray()) {
            if (!current.containsChild(c)) {
                return false;
            }
            current = current.getChild(c);
        }

        return current.isEndOfWord();
    }

    public List<String> searchPrefix(String prefix) {
        List<String> results = new ArrayList<>();

        if (prefix == null || prefix.trim().isEmpty()) {
            return results;
        }

        TrieNode current = root;
        String normalized = prefix.toLowerCase();

        for (char c : normalized.toCharArray()) {
            if (!current.containsChild(c)) {
                return results;
            }
            current = current.getChild(c);
        }

        collectWords(current, normalized, results);
        return results;
    }

    private void collectWords(TrieNode node, String currentWord, List<String> results) {
        if (node.isEndOfWord()) {
            results.add(currentWord);
        }

        for (Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
            collectWords(entry.getValue(), currentWord + entry.getKey(), results);
        }
    }

    public void loadFromDrugRepo(DrugRepo repo) {
        if (repo == null) {
            return;
        }

        for (Drug drug : repo.getAllDrugs()) {
            if (drug.getName() != null) {
                insert(drug.getName());
            }

            if (drug.getBrandNames() != null) {
                for (String brandName : drug.getBrandNames()) {
                    insert(brandName);
                }
            }
        }
    }
}