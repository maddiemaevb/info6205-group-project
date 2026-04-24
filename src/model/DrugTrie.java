package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * trie data structure used for medication autocomplete
 * 
 * this trie stores the drug names and brand names character by character.
 * 
 * also allows the front end search bar to return the autocomplete suggestions based on the
 * prefix.
 */
public class DrugTrie {

    private TrieNode root;

    public DrugTrie() {
        this.root = new TrieNode();
    }

    /**
     * this inserts a word into the trie
     * 
     * each character becomes apart of a path through the trienode objects
     * @param word the drug name or brand name to insert
     */
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

    /**
     * this searches for an exact word in the trie
     * @param word the drug name to search for
     * @return returns true if the full word does exist within the trie or false if not
     */
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

    /**
     * this returns all of the drug names that begin with the given prefix of the search
     * 
     * this method is used for the autocomplete api. and it goes through the trie to find the node
     * that matches the prefix and collects all of the complete words below that node.
     * @param prefix the partial drug name typed in by the user
     * @return returns a list of the matching drug names
     */
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

    /**
     * recursively collects all complete words below a given trie node
     * @param node current trie node
     * @param currentWord the word built so far
     * @param results the list where completed words are stored
     */
    private void collectWords(TrieNode node, String currentWord, List<String> results) {
        if (node.isEndOfWord()) {
            results.add(currentWord);
        }

        for (Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
            collectWords(entry.getValue(), currentWord + entry.getKey(), results);
        }
    }

    /**
     * this loads in the repository from the drug repo class
     * @param repo 
     */
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