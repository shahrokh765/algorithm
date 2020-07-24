package edu.stonybrook.cs.algorithm.datastructures;

import java.util.Iterator;

/**Dynamic Array-based stack implementation*/
public class ArrayStack<T> extends Stack<T>{
    private DynamicArray<T> list = new DynamicArray<>();

    public ArrayStack(){ super();}

    public ArrayStack(T firstElm){
        super();
        list.add(firstElm);
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
        list.add(elm);
    }

    /**
     * Pop top element from the stack
     */
    @Override
    public T pop() {
        checkIfEmpty();
        return list.removeAt(list.size() - 1);
    }

    /**
     * Returns the top element without removing it.
     */
    @Override
    public T peek() {
        checkIfEmpty();
        return list.get(list.size() - 1);
    }

    private void checkIfEmpty(){
        if (list.isEmpty())
            throw new RuntimeException("Empty Stack");
    }

    public T peekFirst(){
        checkIfEmpty();
        return list.get(0);
    }

    /**
     * Returns Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return list.reversed().iterator();
    }
}
