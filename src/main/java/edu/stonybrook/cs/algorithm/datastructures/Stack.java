package edu.stonybrook.cs.algorithm.datastructures;

import java.util.Iterator;

/**Stack interface
 * @author Mohammad Ghaderibaneh <mghaderibane@cs.stonybrook.edu>*/

public abstract class Stack<T>  implements Iterable<T>{

    /**Returns size of the stack*/
    public abstract int size();

    /**Returns if the stack is empty*/
    public abstract boolean isEmpty();

    /**Push element into the stack.
     * @param elm element*/
    public abstract void push(T elm);

    /**Pop top element from the stack*/
    public abstract T pop();

    /**Returns the top element without removing it.*/
    public abstract T peek();

    /**Returns Iterator*/
    @Override
    public abstract Iterator<T> iterator();

}
