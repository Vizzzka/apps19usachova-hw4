package ua.edu.ucu.autocomplete;

import org.junit.Test;
import ua.edu.ucu.queue.Queue;

import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void testPeek() throws Exception {
        Queue myQueue = new Queue();
        myQueue.enqueue(2);
        myQueue.enqueue(3);

        assertEquals(myQueue.peek(), 2);

        myQueue.enqueue(5);
        myQueue.enqueue(7);

        assertEquals(myQueue.peek(), 2);
    }

    @Test
    public void testEnqueue() throws Exception {
        Queue myQueue = new Queue();
        myQueue.enqueue(2);
        assertEquals(myQueue.peek(), 2);

        myQueue.enqueue(3);
        myQueue.dequeue();
        assertEquals(myQueue.peek(), 3);

    }

    @Test
    public void testDequeue() throws Exception {
        Queue myQueue = new Queue();
        myQueue.enqueue(2);
        assertEquals(myQueue.peek(), 2);
        myQueue.enqueue(3);
        assertEquals(myQueue.peek(), 2);

        myQueue.enqueue(5);
        myQueue.enqueue(6);
        myQueue.dequeue();
        myQueue.enqueue(3);

        myQueue.dequeue();
        myQueue.dequeue();
        myQueue.dequeue();
        myQueue.dequeue();
        myQueue.enqueue(1);
        assertEquals(myQueue.peek(), 1);

    }

}
