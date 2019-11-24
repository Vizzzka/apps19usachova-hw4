package ua.edu.ucu.tries;

import ua.edu.ucu.iterators.WordsIterator;
import ua.edu.ucu.queue.Queue;
import ua.edu.ucu.tries.TNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RWayTrie implements Trie {
    private TNode root;
    private int size;

    public RWayTrie() {
        this.root = new TNode();
        this.size = 0;
    }

    @Override
    public void add(Tuple t) {
        TNode node = this.root;
        for (int i = 0; i < t.term.length() - 1; ++i) {
                node.setSubNode(t.term.charAt(i), 0);
            node = node.getSubNode(t.term.charAt(i));
        }
        node.setSubNode(t.term.charAt(t.term.length() - 1), t.weight);
        this.size++;
    }

    @Override
    public boolean contains(String word) {
        return this.getEndOfWord(word) != null;
    }

    @Override
    public boolean delete(String word) {
        if (!this.contains(word))
            return false;
        int deletedCol = word.length();
        TNode node = this.root;
        TNode lastFreeNode = this.root;
        char subNodeValue = word.charAt(0);

        for (int i = 0; i < word.length(); ++i) {
            if (node.amountOfSubNodes() > 1) {
                deletedCol--;
                lastFreeNode = node;
                subNodeValue = word.charAt(i);
            }
            node = node.getSubNode(word.charAt(i));
        }
        this.size -= deletedCol;
        lastFreeNode.deleteSubNode(subNodeValue);
        return true;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        TNode node = this.getEndOfWord(s);
        return WordsIterator.words(node, s);
    }

    @Override
    public int size() {
        return this.size;
    }

    public TNode getRoot() {
        return root;
    }

    public void setRoot(TNode root) {
        this.root = root;
    }

    private TNode getEndOfWord(String word) {
        TNode node = this.root;
        for (int i = 0; i < word.length(); ++i) {
            if (node.getSubNode(word.charAt(i)) == null) {
                return null;
            }
            node = node.getSubNode(word.charAt(i));
        }
        return node;
    }
}
