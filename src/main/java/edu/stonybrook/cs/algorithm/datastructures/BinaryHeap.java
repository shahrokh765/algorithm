package edu.stonybrook.cs.algorithm.datastructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**BinaryHeap implementation of min PriorityQueue. Note this data structure does not support fast
 * removal of non-top element.
 * Reverse your Comparator for max PQueue*/

public class BinaryHeap<T extends Comparable<T>> implements PQueue<T>{

    private ArrayList<T> heap = null;

    public BinaryHeap(){
        this(1);
    }

    public BinaryHeap(int initCapacity){
        super();
        heap = new ArrayList<>();
    }

    /**Constructing the PQ with array of elements. O(n)*/
    public BinaryHeap(T[] elms){
        super();
        heap = new ArrayList<>(elms.length);
        //add all elements
        Collections.addAll(heap, elms);
        for (int i = Math.max(0, size() / 2 - 1); i >= 0; i--) bubbleDown(i);
    }

    /**Constructing the PQ with array of elements. O(n)*/
    public BinaryHeap(Collection<T> elms){
        super();
        heap = new ArrayList<>(elms.size());
        // add all elements
        heap.addAll(elms);
        for (int i = Math.max(0, size() / 2 - 1); i >= 0; i--) bubbleDown(i);
    }
    /**
     * Returns the item with highest priority
     *
     * @return {@code T}
     */
    @Override
    public T peek() {
        return heap.get(0);
    }

    /**
     * Add new element to the PQueue
     *
     * @param elem element type of {@code T}
     */
    @Override
    public void add(T elem) {
        if (elem == null) throw new IllegalArgumentException("Null input is not accepted");
        heap.add(elem);
        bubbleUp(heap.size() - 1);
    }

    // bubble-up the new elements if needed, O(log(n))
    // parent of i-th element is (i-1)/2
    private void bubbleUp(int i){
        int parent = (i - 1)/2;
        while (!isLess(parent, i) && i > 0){
            swap(parent, i);
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    // bubble-down after removing element in i, O(log(n))
    // children of i-th element are 2*i+1 and 2*i+2
    private void bubbleDown(int i){
        while (true){
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = left;
            if (right < size() && isLess(right, left)) smallest = right;

            if (left >= size() || isLess(i, smallest)) break;

            swap(i, smallest);
            i = smallest;
        }
    }

    private boolean isLess(int i, int j){
        return heap.get(i).compareTo(heap.get(j)) <= 0;
    }

    //swap location of i and j elements, O(1)
    private void swap(int i, int j){
        T tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    /**
     * Returns and remove the item with highest priority
     *
     * @return {@code T}
     */
    @Override
    public T poll() {
        return removeAt(0);
    }

    // remove element at i-th index, O(n)
    private T removeAt(int i){
        if (heap.isEmpty()) return null;

        int heapSize = heap.size();
        T elm = heap.get(i);
        swap(i, heapSize - 1);
        heap.remove(heapSize - 1);
        // if i == last element
        if (i == heapSize - 1) return elm;

        T elmAtI = heap.get(i); // to check if bubble-down does not work
        bubbleDown(i);
        // if element does not go down, try to bubble it up
        if (elmAtI == heap.get(i)) bubbleUp(i);

        return elm;
    }

    /**
     * Returns if the PQueue is empty
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns number of elements in the PQueue
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Removes all elements in the PQueue
     */
    @Override
    public void clear() {
        heap.clear();
    }

    /**Checks if the heal contains an element, O(n).*/
    public boolean contains(T elm){
        return indexOf(elm) != -1;
    }

    private int indexOf(T elm){
        if (elm == null) return -1;
        for (int i = 0; i < size(); i++) if (heap.get(i).equals(elm)) return i;
        return -1;
    }

    /**Remove an element from the PQueue if exists, O(n).*/
    public boolean remove(T elm){
        int i = indexOf(elm);
        if (i == -1) return false;
        removeAt(i);
        return true;
    }

    @Override
    public String toString(){
        return heap.toString();
    }
}
