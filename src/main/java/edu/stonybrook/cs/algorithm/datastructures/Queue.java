package edu.stonybrook.cs.algorithm.datastructures;

import java.util.Iterator;

/**Stack interface
 * @author Mohammad Ghaderibaneh <mghaderibane@cs.stonybrook.edu>*/
public abstract class Queue<T> implements Iterable<T>{

    public Queue(){
        super();
    }

    /**Returns size of the stack*/
    public abstract int size();

    /**Returns if the stack is empty*/
    public abstract boolean isEmpty();

    /**Push element into the stack.
     * @param elm element*/
    public abstract void enqueue(T elm);

    /**Pop top element from the stack*/
    public abstract T dequeue();

    /**Returns the top element without removing it.*/
    public abstract T peek();

    /**Returns Iterator*/
    @Override
    public abstract Iterator<T> iterator();
}
