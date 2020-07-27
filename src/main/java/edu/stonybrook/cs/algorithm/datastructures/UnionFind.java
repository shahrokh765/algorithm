package edu.stonybrook.cs.algorithm.datastructures;

import java.util.*;

/**Dynamic Union-Find implementation. By dynamic, it means new nodes can be added(add method) after the
 * object is instantiated. A hash table is also used to support generic.
 * Path-compression is also used to increase the efficiency of the Find and Union operations.
 * @author Mohammad Ghaderibaneh, <mghaderibane@cs.stonybrook.edu>*/

public class UnionFind<T> {
    // a hash-map that maps user data-structure to index used for
    private HashMap<T, Integer> hashMap;

    // a dynamic array that keeps track of each element's parent
    private ArrayList<Integer> parents;

    // a dynamic array that holds the size of each set, only root values are valid
    private ArrayList<Integer> sz;

    // number of components
    private int numComponents;

    // number of elements in the Union-Find
    private int size;

    /**Create new Union-Find with initial capacity
     * @param initCapacity initial capacity*/
    public UnionFind(int initCapacity){
        super();
        if (initCapacity < 0) throw new IllegalArgumentException("Negative capacity is not allowed");
        numComponents = 0;
        size = 0;
        hashMap = new HashMap<>();
        parents = new ArrayList<>(initCapacity);
        sz = new ArrayList<>();
    }

    /**Create new Union-Find with initial capacity of 16*/
    public UnionFind(){
        this(16);
    }

    /**Create Union-Find data structure given an array of elements
     * @param elements elements of {@code T}*/
    public UnionFind(T[] elements){
        super();
        numComponents = elements.length;
        size = elements.length;
        sz = new ArrayList<>(size);
        parents = new ArrayList<>(size);
        hashMap = new HashMap<>();
        for (int i = 0; i < elements.length; i++){
            T elm = elements[i];
            sz.add(i, 1);    // each component has size of 1 at the beginning
            parents.add(i, i);      // each component is its own parent at the beginning
            hashMap.put(elm, i);    // map elm to i for referencing
        }
    }

    /**Create Union-Find data structure given an List of elements
     * @param elements elements of {@code T}*/
    public UnionFind(List<T> elements){
        super();
        numComponents = elements.size();
        size = elements.size();
        sz = new ArrayList<>(size);
        parents = new ArrayList<>(size);
        hashMap = new HashMap<>();
        for (int i = 0; i < elements.size(); i++){
            T elm = elements.get(i);
            sz.add(i, 1);    // each component has size of 1 at the beginning
            parents.add(i, i);      // each component is its own parent at the beginning
            hashMap.put(elm, i);    // map elm to i for referencing
        }
    }

    /**Dynamically add new elements to the Union-Find*/
    public void add(T elm){
        if (hashMap.containsKey(elm)) return;   // avoid duplicates
        int idx = parents.size();
        parents.add(idx, idx);
        sz.add(idx, 1);
        hashMap.put(elm, idx);
        size++;
        numComponents++;
    }

    /**Returns the component ID to which elm belongs. Component ID are positive, unique numbers
     * but they don't have to follow. -1 would be returned if elm does not exist
     * a pattern.
     * @param elm element {@code T}
     * @return component id*/
    public int find(T elm){
        Integer elmId = hashMap.get(elm);
        if (elmId == null) return -1;
//        return find(elmId);   // uncomment this line and comment(delete) the rest of this method, if you want recursion
        int root = elmId;
        // find the root of the element which is the id of that component
        while (root != parents.get(root)) root = parents.get(root);

        // path-compression
        while (elmId != root){
            int parent = parents.get(elmId);
            parents.set(elmId, root);
            elmId = parent;
        }

        return elmId;
    }

    // recursively find the root and does path-compression.
    // due to path-compression, recursively finding root should not create much overhead.
    private int find(int idx){
        int parent = parents.get(idx);
        if (parent == idx) return idx;
        int root = find(parent);
        parents.set(idx, root);
        return root;
    }

    /**Returns if two elements are connected
     * @param thisElm first element
     * @param thatElm second element*/
    public boolean connected(T thisElm, T thatElm){
        return find(thisElm) == find(thatElm);
    }

    private boolean connected(int thisIdx, int thatIdx){
        return find(thisIdx) == find(thatIdx);
    }

    /**Returns number of elements the component elm belong to
     * @return number of elements in the component
     * @param elm target element*/
    public int componentSize(T elm){
        return sz.get(find(elm));
    }

    /**Returns the number of all elements*/
    public int size(){
        return size;
    }

    /**Return number of disjoint components*/
    public int components(){ return numComponents; }

    public void unify(T thisElm, T thatElm){
        int thisIdx = hashMap.get(thisElm);
        int thatIdx = hashMap.get(thatElm);

        if (connected(thisIdx, thatIdx)) return;

        int thisRoot = find(thisIdx);
        int thatRoot = find(thatIdx);

        // connect root of smaller set to the bigger
        if (sz.get(thisRoot) > sz.get(thatRoot)){
            parents.set(thatRoot, thisRoot);
            sz.set(thisRoot, sz.get(thisRoot) + sz.get(thatRoot));
        }else{
            parents.set(thisRoot, thatRoot);
            sz.set(thatRoot, sz.get(thisRoot) + sz.get(thatRoot));
        }
        numComponents--;    // reduce number of components after joining two components
    }

    /**Returns a nested ArrayList containing elements of each components together.
     * Null is returned if the union-find is empty*/
    public ArrayList<ArrayList<T>> componentElements(){
        if (size() <= 0) return null;
        HashMap<Integer, Integer> rootMap = new HashMap<>();    // relate component root id to returning component id
        ArrayList<ArrayList<T>> componentElements = new ArrayList<ArrayList<T>>();
        int rootArrayId = 0;
        for (Map.Entry<T, Integer> elements : hashMap.entrySet()){
            int root = find(elements.getKey());
            if (!rootMap.containsKey(root)){
                rootMap.put(root, rootArrayId++);
                componentElements.add(new ArrayList<>());
            }
            componentElements.get(rootMap.get(root)).add(elements.getKey());
        }
        return componentElements;
    }
}
