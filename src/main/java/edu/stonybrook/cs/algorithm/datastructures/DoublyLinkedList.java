package edu.stonybrook.cs.algorithm.datastructures;

import java.util.Iterator;

/**
 * Doubly Linked List implementation.
 * @author Mohammad Ghaderibaneh, <mghaderibane@cs.stonybrook.edu>*/

public class DoublyLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private static class Node<T>{
        T data;
        Node<T> next, prev;
        Node(T data, Node<T> prev, Node<T> next){
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString(){ return data.toString(); }
    }

    /**Clear all elements in the list(O(n))*/
    public void clear(){
        Node<T> node = head;
        while (node != null){
            Node<T> next = node.next;
            node.prev = node.next = null;
            node.data = null;
            node = next;
        }
        size = 0;
        head = tail = null;
    }

    /**@return size of list*/
    public int size(){ return size;}

    /**@return True if list is empty, else false.*/
    public boolean isEmpty(){ return size == 0; }

    /**Add a new item to the end(tail) of the list
     * @param data new node of type {@code T}*/
    public void addLast(T data){
        Node<T> node = new Node<>(data, null, null);
        if (size() == 0){
            head = tail = node;
        }else{
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    /**Add a new item to the end(tail) of the list
     * @param data new node of type {@code T}*/
    public void add(T data){
        addLast(data);
    }

    /**Add a new item to the beginning(head) of the list
     * @param data new node of type {@code T}*/
    public void addFirst(T data){
        if (size() == 0)
            head = tail = new Node<>(data, null, null);
        else{
            head.prev = new Node<>(data, null, head);
            head = head.prev;
        }
        size++;
    }

    // check the boundary for an index
    private void checkIndex(int index){
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException("Illegal Index");
    }

    /**Add a new item at the index-th of the list (O(n))
     * @param data new node of type {@code T}
     * @param index index where you want to add new element*/
    public void addAt(T data, int index){
        checkIndex(index);
        if (index == 0)
            addFirst(data);
        else if (index == size())
            addLast(data);
        else{
            Node<T> node = head;
            for (int i = 0; i < index - 1; i++)
                node = node.next;
            Node<T> newNode = new Node<>(data, node, node.next);
            node.next = newNode;
            newNode.next.prev = newNode;
        }
    }

    /**Return the first element of the list(O(1))
     * @return {@code T}*/
    public T peekFirst(){
        if (size() == 0) throw new RuntimeException("Empty list");
        return head.data;
    }

    /**Return the first element of the list(O(1))
     * @return {@code T}*/
    public T peekLast(){
        if (size() == 0) throw new RuntimeException("Empty list");
        return tail.data;
    }

    /**Remove and return the first element of the list(O(1))
     * @return {@code T}*/
    public T removeFirst(){
        T data = peekFirst();
        head = head.next;
        size--;
        if (size() == 0)
            tail = null;
        else
            head.prev = null;
        return data;
    }

    /**Remove and return the last element of the list(O(1))
     * @return {@code T}*/
    public T removeLast(){
        T data = peekLast();
        tail = tail.prev;       // set tail to the previous one
        size--;
        if (size() == 0)
            head = null;
        else
            tail.next = null;
        return data;
    }

    private T remove(Node<T> node){
        if (node == head) return removeFirst();
        if (node == tail) return removeLast();

        T data = node.data;
        //adjust pointers
        node.next.prev = node.prev;
        node.prev.next = node.next;

        //memory cleaning
        node.data = null;
        node = node.next = node.prev = null;

        size--;

        return data;
    }

    /**Remove and return index-th element
     * @param index index to be deleted
     * @return {@code T}*/
    public T removeAt(int index){
        checkIndex(index);
        Node<T> node;
        if (index < size / 2) {
            node = head;
            for (int i = 0; i < index; i++)
                node = node.next;
        }else{
            node = tail;
            for (int i = size() - 1; i > index; i--)
                node = node.prev;
        }
        return remove(node);
    }

    /**return index-th element
     * @param index index to be deleted
     * @return {@code T}*/
    public T get(int index){
        checkIndex(index);
        Node<T> node;
        if (index < size / 2) {
            node = head;
            for (int i = 0; i < index; i++)
                node = node.next;
        }else{
            node = tail;
            for (int i = size() - 1; i > index; i--)
                node = node.prev;
        }
        return node.data;
    }
    /**Remove a particular object(O(n))
     * @return if it is in the list
     * @param obj target object*/
    public boolean remove(Object obj){
        Node<T> node = head;
        //search for the first occurrence of a node that has null data
        if (obj == null){
            for (; node != null; node = node.next){
                if (node.data == null){
                    remove(node);
                    return true;
                }
            }
            // search for non-null values
        }else{
            for (; node != null; node = node.next) {
                if (obj.equals(node.data)) {
                    remove(node);
                    return true;
                }
            }
        }
        return false;
    }

    /**return the index where input object is located.
     * @param obj object
     * @return index(-1 if not existed)*/
    public int indexOf(Object obj){
        Node<T> node = head;
        int index = 0;
        //search for the first occurrence of a node that has null data
        if (obj == null){
            for (; node != null; node = node.next, index++){
                if (node.data == null){
                    return index;
                }
            }
            // search for non-null values
        }else{
            for (; node != null; node = node.next, index++) {
                if (obj.equals(node.data)) {
                    return index;
                }
            }
        }
        return -1;
    }

    /**Check if obj exists in the list*/
    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;
            @Override
            public boolean hasNext() {
                if (node == null)
                    return false;
                return node.next != null;
            }

            @Override
            public T next() {
                T data = node.data;
                node = node.next;
                return data;
            }
        };
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> node = head;
        for (; node != null; node = node.next) {
            sb.append(node.data.toString());
            if (node.next != null)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
