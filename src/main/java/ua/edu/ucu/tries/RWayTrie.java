package ua.edu.ucu.tries;

import ua.edu.ucu.iterators.WordsIterator;

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
        TNode node = this.getEndOfWord(word);
        if (node == null)
            return false;
        node.setWeight(0);
        this.size--;
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
