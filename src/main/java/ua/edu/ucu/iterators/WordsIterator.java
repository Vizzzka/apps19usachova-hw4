package ua.edu.ucu.iterators;

import ua.edu.ucu.queue.Queue;
import ua.edu.ucu.tries.TNode;

import java.util.Iterator;


public class WordsIterator implements Iterator<String> {
    private Queue queue;
    private String next;

    public WordsIterator(TNode node, String word) {
        queue = new Queue();
        queue.enqueue(new Object[]{node, word});
        this.countNext();
    }

    private void countNext() {
        while(!queue.isEmpty()) {
            Object[] element = (Object[]) queue.dequeue();
            TNode node = (TNode) element[0];
            String word = (String) element[1];

            TNode[] subNodes = node.getSubNodes();
            for (TNode subNode : subNodes) {
                if (subNode != null) {
                    queue.enqueue(new Object[]{subNode, word + subNode.getValue()});
                }
            }
            if (node.getWeight() != 0) {
                next = word;
                return;
            }
        }
        next = null;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public String next() {
        String tmp = next;
        this.countNext();
        return tmp;
    }

    public static Iterable<String> words(TNode node, String s) {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new WordsIterator(node, s);
            }
        };
    }

}