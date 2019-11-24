package ua.edu.ucu.autocomplete;

import org.junit.Test;
import ua.edu.ucu.tries.TNode;
import static org.junit.Assert.*;


public class TNodeTest {

    @Test
    public void testCreateTNode() {
        TNode node = new TNode();
        node.setValue('c');
        node.setWeight(5);

        char expectedValue = 'c';
        int expectedWeight = 5;
        assertEquals(expectedValue, node.getValue());
        assertEquals(expectedWeight, node.getWeight());

    }

    @Test
    public void testSubNode() {
        TNode node = new TNode();
        node.setSubNode('g', 10);
        int expectedResult = 10;

        assertEquals(expectedResult, node.getSubNode('g').getWeight());
    }

    @Test
    public void testGetSubNodes() {
        TNode node = new TNode();
        node.setSubNode('g', 10);
        node.setSubNode('a', 9);
        node.setSubNode('b', 11);
        node.setSubNode('c', 12);

        TNode[] subNodes = node.getSubNodes();
        String expectedResult = "abcg", actualResult = "";
        for (TNode element : subNodes) {
            if (element != null)
                actualResult += element.getValue();
        }
        assertEquals(expectedResult, actualResult);
    }
}
