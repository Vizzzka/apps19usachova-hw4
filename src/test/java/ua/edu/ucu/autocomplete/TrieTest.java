package ua.edu.ucu.autocomplete;

import org.junit.Test;
import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Tuple;
import static org.junit.Assert.*;


public class TrieTest {

    @Test
    public void testAddContains() {
        RWayTrie trie = new RWayTrie();
        trie.add(new Tuple("abcd", 10));
        trie.add(new Tuple("abcde", 12));
        trie.add(new Tuple("abc", 2));

        assertTrue(trie.contains("abcd"));
        assertTrue(trie.contains("abcde"));
        assertTrue(trie.contains("abc"));
        assertFalse(trie.contains("#"));
        assertFalse(trie.contains("bbk"));
    }

    public void testDelete() {
        RWayTrie trie = new RWayTrie();
        trie.add(new Tuple("abcd", 10));
        trie.add(new Tuple("abcde", 12));
        trie.add(new Tuple("abc", 2));
        trie.add(new Tuple("abcdefg", 2));
        boolean deleted = trie.delete("abc");

        assertFalse(trie.contains("abc"));
        assertTrue(trie.contains("abcd"));
        assertFalse(trie.delete("ll"));
        assertEquals(3, trie.size());

    }

}
