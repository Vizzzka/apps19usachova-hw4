package ua.edu.ucu.tries;


public class TNode {
    private char value;
    private TNode[] subNodes;
    private int weight;

    public final int amountOfLetters = 26;

    public TNode() {
        this.weight = 0;
        this.subNodes = new TNode[this.amountOfLetters];
    }

    public TNode(char value, int weight) {
        this.weight = weight;
        this.value = value;
        this.subNodes = new TNode[this.amountOfLetters];
    }

    public TNode[] getSubNodes() {
        return this.subNodes.clone();
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) { this.value = value;}

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int x) {
        this.weight = x;
    }

    public void setSubNode(char subValue, int wordLength) {
        int index = this.charValueToIndex(subValue);
        if (this.subNodes[index] == null)
            this.subNodes[index] = new TNode(subValue, wordLength);
        else
            this.subNodes[index].weight = Math.max(this.subNodes[index].weight, wordLength);
    }

    public TNode getSubNode(char subValue) {
        int index = this.charValueToIndex(subValue);
        if (checkIndex(index))
            return this.subNodes[index];
        return null;
    }

    private boolean checkIndex(int index) {
        return index >= 0 && index < this.amountOfLetters;
    }

    private int charValueToIndex(char value) {
        return (int)value - (int)'a';
    }

}
