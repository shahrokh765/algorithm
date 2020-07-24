package edu.stonybrook.cs.algorithm.datastructures;

import java.util.Iterator;


/**Stack implementation using SinglyLinkedList*/
public class ListStack<T> extends Stack<T>{
    private SinglyLinkedList<T> list = new SinglyLinkedList<>();

    public ListStack(){super();}

    public ListStack(T firstElm){
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
    public void push(T elm) {
        list.addFirst(elm);

    }

    /**
     * Pop top element from the stack
     */
    @Override
    public T pop() {
        checkIfEmpty();
        return list.removeFirst();
    }

    /**
     * Returns the top element without removing it.
     */
    @Override
    public T peek() {
        checkIfEmpty();
        return list.peekFirst();
    }

    /**Returns the first(bottom) element in the stack*/
    public T peekFirst(){
        checkIfEmpty();
        return list.peekLast();
    }

    /**
     * Returns Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    private void checkIfEmpty(){
        if (list.isEmpty())
            throw new RuntimeException("Empty Stack");
    }
}
