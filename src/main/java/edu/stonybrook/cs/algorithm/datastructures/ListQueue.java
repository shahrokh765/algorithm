package edu.stonybrook.cs.algorithm.datastructures;


import java.util.Iterator;

/** LinkedList-based Queue implementation
 * @author Mohammad Ghaderibaneh <mghaderibane@cs.stonybrook.edu>
 * @param <T>
 */
public class ListQueue<T> extends Queue<T> {
    private DoublyLinkedList<T> list = new DoublyLinkedList<>();

    public ListQueue(){ super(); }

    public ListQueue(T firstElm){
        super();
        list.addFirst(firstElm);
    }
    /**
     * Returns size of the stack
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Returns if the stack is empty
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Push element into the stack.
     *
     * @param elm element
     */
    @Override
    public void enqueue(T elm) {
        list.addLast(elm);
    }

    /**
     * Pop top element from the stack
     */
    @Override
    public T dequeue() {
        checkEmpty();
        return list.removeFirst();
    }

    /**
     * Returns the top element without removing it.
     */
    @Override
    public T peek() {
        checkEmpty();
        return list.peekFirst();
    }

    public T peekLast() {
        checkEmpty();
        return list.peekLast();
    }

    private void checkEmpty(){
        if (list.isEmpty())
            throw new RuntimeException("Queue is empty");
    }

    /**
     * Returns Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
