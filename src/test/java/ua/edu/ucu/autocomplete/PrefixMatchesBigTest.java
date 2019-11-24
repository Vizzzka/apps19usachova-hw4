package ua.edu.ucu.autocomplete;


import ua.edu.ucu.tries.RWayTrie;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.Test;
import org.junit.Before;


public class PrefixMatchesBigTest {
    private PrefixMatches pm;

    @Before
    public void init() {
        pm = new PrefixMatches(new RWayTrie());
        pm.load("abc", "abce", "abcd", "abcde", "Abcdef");
    }

    @Test
    public void testWordsWithPrefix_String() {
        String pref = "ab";

        Iterable<String> result = pm.wordsWithPrefix(pref);

        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_and_K() {
        String pref = "abc";
        int k = 3;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);

        String[] expResult = {"abc", "abce", "abcd", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsAfterDelete() {
        String pref = "ab";
        pm.delete("abcd");
        Iterable<String> result = pm.wordsWithPrefix(pref);
        String[] expResult = {"abc", "abce", "abcde", "abcdef"};
        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testContains() {
        assertTrue(pm.contains("abce"));
        assertFalse(pm.contains("abcdefg"));
        pm.load("abcdefg abcdefgh");
        assertTrue(pm.contains("abcdefg"));
        pm.load("abc123!@$^*%^&*fed");
        assertTrue(pm.contains("abCfed"));
        assertFalse(pm.contains("abc123!@$^*%^&*fed"));
        pm.load("a");
        assertFalse(pm.contains("a"));
    }

    @Test
    public void testSize() {
        assertEquals(5, pm.size());
        pm.delete("abcdefg");
        assertEquals(5, pm.size());
        pm.delete("abcdef");
        assertEquals(4, pm.size());

    }
}
