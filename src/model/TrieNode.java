package model;

import java.util.HashMap;
import java.util.Map;

/**
 * this class represents a single node in the medication trie
 * 
 * each trienode stores child nodes mapped by character and
 * a boolean flag indicating whether the current path forms a complete word
 */
public class TrieNode {

    private Map<Character, TrieNode> children;
    private boolean endOfWord;

    public TrieNode() {
        this.children = new HashMap<>();
        this.endOfWord = false;
    }

    /**
     * getting the children of the node
     * @return returns the children
     */
    public Map<Character, TrieNode> getChildren() {
        return this.children;
    }

    public boolean isEndOfWord() {
        return this.endOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    /**
     * checks whether this node has a child for the given character
     * @param c character
     * @return returns true if a child node exists for the character
     */
    public boolean containsChild(char c) {
        return children.containsKey(c);
    }

    /**
     * retrieves the child node for a specific character
     * @param c character key
     * @return returns the child trie node or null if it does not exist
     */
    public TrieNode getChild(char c) {
        return children.get(c);
    }

    /**
     * this adds a child node for a specific character
     * @param c character key
     * @param node the trienode to store as the child
     */
    public void addChild(char c, TrieNode node) {
        children.put(c, node);
    }
}