package edu.stonybrook.cs.algorithm.datastructures;

/**Priority Queue interface
 * @author Mohammad Ghaderibaneh <mghaderibane@cs.stonybrook.edu>*/
public interface PQueue<T extends Comparable<T>> {
    /**Returns the item with highest priority
     * @return {@code T}*/
    T peek();

    /**Add new element to the PQueue
     * @param elem element type of {@code T}*/
    void add(T elem);

    /**Returns and remove the item with highest priority
     * @return {@code T}*/
    T poll();

    /**Returns if the PQueue is empty*/
    boolean isEmpty();

    /**Returns number of elements in the PQueue*/
    int size();

    /**Removes all elements in the PQueue*/
    void clear();
}
