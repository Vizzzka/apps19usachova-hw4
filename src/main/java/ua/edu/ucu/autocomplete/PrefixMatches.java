package ua.edu.ucu.autocomplete;

import ua.edu.ucu.iterators.WordsKLengthIterator;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int n = 0;
        for (String sentence : strings) {
            String[] words = sentence.split(" ");
            for (String word : words) {
                n++;
                this.trie.add(new Tuple(word, word.length()));
            }
        }
        return n;
    }

    public boolean contains(String word) {
        return this.trie.contains(word);
    }

    public boolean delete(String word) {
        return this.trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return this.trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        return WordsKLengthIterator.words(this.trie.wordsWithPrefix(pref), k);
    }

    public int size() {
        return this.trie.size();
    }
}
