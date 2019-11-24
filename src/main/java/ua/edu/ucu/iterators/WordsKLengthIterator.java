package ua.edu.ucu.iterators;

import java.util.Iterator;


public class WordsKLengthIterator implements Iterator<String> {
    private Iterator<String> itr;
    private int k;
    private String next;
    private int colDiffSizes;
    private int maxSize;


    public WordsKLengthIterator(Iterator<String> itr, int k) {
        this.itr = itr;
        this.k = k;
        this.colDiffSizes = 0;
        this.maxSize = 0;
        countNext();
    }

    private void countNext() {
        this.next = itr.next();
        // if there is no next element
        if (!this.itr.hasNext()) {
            this.next = null;
            return;
        }
        // if already have k different sizes and next element has bigger
        if (this.colDiffSizes == this.k && this.next.length() > this.maxSize){
            this.next = null;
            return;
        }

        if (this.next.length() > this.maxSize) {
            this.colDiffSizes++;
            this.maxSize = this.next.length();
        }
    }

    @Override
    public String next() {
        String tmp = this.next;
        countNext();
        return tmp;
    }

    @Override
    public boolean hasNext() {
        return this.next != null;
    }

    public static Iterable<String> words(Iterable<String> itr, int k) {
        return () -> new WordsKLengthIterator(itr.iterator(), k);
    }
}