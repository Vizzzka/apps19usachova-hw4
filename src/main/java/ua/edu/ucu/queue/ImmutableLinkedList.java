package ua.edu.ucu.queue;


public final class ImmutableLinkedList implements ImmutableList{

    private Node head;
    private int size;

    public ImmutableLinkedList() {
        head = null;
        size = 0;
    }

    public ImmutableLinkedList(Object[] c) {
        this.size = c.length;
        if (c.length == 0) {
            this.head = null;
            return;
        }
        this.head = new Node(c[0]);
        Node temp = this.head;
        for (int i = 1; i < this.size; ++i) {
            temp.setNext(new Node(c[i]));
            temp = temp.getNext();
        }
    }

    public ImmutableLinkedList copy() {
        if (this.size == 0){
            return new ImmutableLinkedList();
        }
        ImmutableLinkedList newLinkedList = new ImmutableLinkedList();
        newLinkedList.size = this.size;
        newLinkedList.head = new Node(this.head.getData());
        Node temp = this.head.getNext();
        Node newTemp = newLinkedList.head;

        for (int i = 1; i < this.size; ++i) {
            newTemp.setNext(new Node(temp.getData()));
            newTemp = newTemp.getNext();
            temp = temp.getNext();
        }
        return newLinkedList;
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return addAll(this.size, new Object[]{e});
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(this.size, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        this.rangeCheckForAdd(index);

        if (index > this.size && index < 0)
            throw new RuntimeException();

        ImmutableLinkedList newLinkedList = new ImmutableLinkedList(c);
        ImmutableLinkedList copyLinkedList = this.copy();

        if (index == 0) {
            int last_index = newLinkedList.size - 1;
            newLinkedList.findNode(last_index).setNext(copyLinkedList.head);
            newLinkedList.size = c.length + this.size();
            return newLinkedList;
        }
        if (index == this.size) {
            int last_index = copyLinkedList.size() - 1;
            copyLinkedList.findNode(last_index).setNext(newLinkedList.head);
            copyLinkedList.size = this.size + c.length;
            return copyLinkedList;
        }
        int last_index = newLinkedList.size() - 1;
        Node temp = copyLinkedList.findNode(index - 1);
        newLinkedList.findNode(last_index).setNext(temp.getNext());
        temp.setNext(newLinkedList.head);
        copyLinkedList.size = this.size + c.length;
        return copyLinkedList;
    }

    @Override
    public Object get(int index) {
        this.rangeCheck(index);
        return this.findNode(index).getData();
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        this.rangeCheck(index);
        ImmutableLinkedList newLinkedList = this.copy();
        Node node = newLinkedList.findNode(index);
        node.setData(e);
        return newLinkedList;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        this.rangeCheck(index);
        ImmutableLinkedList newLinkedList = this.copy();
        newLinkedList.size--;
        if (index == 0) {
            newLinkedList.head = newLinkedList.head.getNext();
            return newLinkedList;
        }
        Node node = newLinkedList.findNode(index - 1);
        node.setNext(node.getNext().getNext());
        return newLinkedList;

    }

    @Override
    public int indexOf(Object e) {
        Node temp = this.head;
        for (int i = 0; i < this.size; ++i) {
            if (temp.getData() == null)
                continue;
            if (temp.getData().equals(e))
                return i;
            temp = temp.getNext();
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Object[] toArray() {
        Node temp = this.head;
        Object[] result = new Object [this.size];

        for (int i = 0; i < this.size; ++i) {
            result[i] = temp.getData();
            temp = temp.getNext();
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node temp = this.head;
        for (int i = 0; i < this.size; ++i) {
            res.append(temp.getData().toString()).append(" ");
            temp = temp.getNext();
        }
        return res.toString();
    }

    private Node findNode(int index) {
        Node temp = this.head;
        for (int i = 0; i < index; ++i) {
            temp = temp.getNext();
        }
        return temp;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Out of range " + index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Out of range " + index);
        }
    }

    public ImmutableLinkedList addFirst(Object e) {
        return this.add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return this.add(e);
    }

    public Object getFirst() {
        this.rangeCheck(0);
        return this.findNode(0).getData();
    }

    public Object getLast() {
        this.rangeCheck(size - 1);
        return this.findNode(size - 1).getData();
    }

    public ImmutableLinkedList removeFirst() {
        return this.remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return this.remove(size - 1);
    }

}
