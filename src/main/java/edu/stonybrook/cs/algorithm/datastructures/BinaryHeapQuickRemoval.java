package edu.stonybrook.cs.algorithm.datastructures;

import java.util.*;

/**BinaryHeapQuickRemoval implementation of min PriorityQueue. Using a hash table, this data structure enable fast
 * removal of a particular item(O(log(n))).
 * Reverse your Comparator for max PQueue*/
public class BinaryHeapQuickRemoval<T extends Comparable<T>> implements PQueue<T>{
    private ArrayList<T> heap = null;

    // this hash map keeps the location of different element to enable fast removal.
    // TreeSet is used in case where an element added more than once.
    private HashMap<T, TreeSet<Integer>> hashMap = new HashMap<>();

    public BinaryHeapQuickRemoval(){
        this(1);
    }

    public BinaryHeapQuickRemoval(int initCapacity){
        super();
        heap = new ArrayList<>();
    }

    /**Constructing the PQ with array of elements. O(n)*/
    public BinaryHeapQuickRemoval(T[] elms){
        super();
        heap = new ArrayList<>(elms.length);
        //add all elements
        for (int i = 0; i < elms.length; i++){
            heap.add(elms[i]);
            toMap(elms[i], i);
        }
        for (int i = Math.max(0, size() / 2 - 1); i >= 0; i--) bubbleDown(i);
    }

    /**Constructing the PQ with array of elements. O(n)*/
    public BinaryHeapQuickRemoval(Collection<T> elms){
        this(elms.size());
        // add all elements
        for (T elm : elms) add(elm);
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
        toMap(elem, size() - 1);
        bubbleUp(size() - 1);
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
        T elmI = heap.get(i);
        T elmJ = heap.get(j);
        heap.set(i, elmJ);
        heap.set(j, elmI);

        //adjust hash map
        mapSwap(i, j, elmI, elmJ);
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
        fromMap(elm, heapSize - 1);
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
        hashMap.clear();
    }

    /**Checks if the heal contains an element, O(log(n))*/
    public boolean contains(T elm){
        return indexOf(elm) != -1;
    }

    private int indexOf(T elm){
        if (elm == null) return -1;
        Integer idx = getMap(elm);
        if (idx == null)
            return -1;
        return idx;
    }

    /**Remove an element from the PQueue if exists, O(log(n))*/
    public boolean remove(T elm){
        int i = indexOf(elm);
        if (i == -1) return false;
        removeAt(i);
        return true;
    }

    private void mapSwap(int i,  int j, T elmI, T elmJ){
        hashMap.get(elmI).remove(i);
        hashMap.get(elmJ).remove(j);

        hashMap.get(elmI).add(j);
        hashMap.get(elmJ).add(i);
    }

    // add i value elm key to map<elm, i>
    private void toMap(T elm, int i){
        if (!hashMap.containsKey(elm)) hashMap.put(elm, new TreeSet<>());
        hashMap.get(elm).add(i);
    }

    //remove i value from elm key
    private void fromMap(T elm, int i){
        hashMap.get(elm).remove(i);
        // remove if its set is empty
        if (hashMap.get(elm).size() == 0) hashMap.remove(elm);
    }

    //get the first value in map equivalent to elm key
    private Integer getMap(T elm){
        TreeSet<Integer> setElm = hashMap.get(elm);
        if (setElm != null) return setElm.first();
        return null;
    }

    @Override
    public String toString(){
        return heap.toString();
    }
}
