package ua.edu.ucu.tries;


public class TNode {
    private char value;
    private TNode[] subNodes;
    private int wordLength;

    public final int amountOfLetters = 26;

    public TNode() {
        this.wordLength = 0;
        this.subNodes = new TNode[this.amountOfLetters];
    }

    public TNode(char value, int wordLength) {
        this.wordLength = wordLength;
        this.value = value;
        this.subNodes = new TNode[this.amountOfLetters];
    }

    public void setSubNode(char subValue, int wordLength) {
        int index = this.charValueToIndex(subValue);
        if (this.subNodes[index] == null)
            this.subNodes[(int)subValue - (int)'a'] = new TNode(subValue, wordLength);
    }

    public TNode getSubNode(char subValue) {
        int index = this.charValueToIndex(subValue);
        return this.subNodes[index];
    }

    public TNode[] getSubNodes() {
        return this.subNodes.clone();
    }

    private int charValueToIndex(char value) {
        return (int)value - (int)'a';
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getWordLength() {
        return this.wordLength;
    }

    public int amountOfSubNodes() {
        int amount = 0;
        for (TNode subNode : this.subNodes) {
            if (subNode != null) {
                amount++;
            }
        }
        return amount;
    }

    public void deleteSubNode(char value) {
        int index = this.charValueToIndex(value);
        this.subNodes[index] = null;
    }

}
