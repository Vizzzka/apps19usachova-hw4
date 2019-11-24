package ua.edu.ucu.iterators;

import java.util.Iterator;


public class WordsKLengthIterator implements Iterator<String> {
    private Iterator<String> iter;
    private int k;
    private String next;
    private int sizes_num = 0;
    private int max_size = -1;


    public WordsKLengthIterator(Iterator<String> iter, int k) {
        this.k = k;
        this.iter = iter;
        countNext();
    }

    private void countNext() {
        next = iter.next();
        if (!iter.hasNext() || (sizes_num == k && next.length() > max_size)) {
            next = null;
            return;
        }
        if (next.length() > max_size) {
            sizes_num++;
            max_size = next.length();
        }
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public String next() {
        String tmp = next;
        countNext();
        return tmp;
    }

    public static Iterable<String> words(Iterable<String> iter, int k) {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new WordsKLengthIterator(iter.iterator(), k);
            }
        };
    }
}