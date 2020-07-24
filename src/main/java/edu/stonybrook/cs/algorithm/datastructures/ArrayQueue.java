package edu.stonybrook.cs.algorithm.datastructures;

import java.util.Iterator;

/** Array-based Queue implementation. Due to Queue feature and for efficiency sake, capacity of the queue should
 * be provided.
 * @author Mohammad Ghaderibaneh <mghaderibane@cs.stonybrook.edu>
 * @param <T>
 */
public class ArrayQueue<T> extends Queue<T> {
    private Object[] arr;
    private int front = 0, rear = 0;
    private int capacity;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        super();
        this.capacity = capacity;
        arr = (T[]) new Object[this.capacity];
    }
    /**
     * Returns size of the stack
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns if the stack is empty
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Push element into the stack.
     *
     * @param elm element
     */
    @Override
    public void enqueue(T elm) {
        if (size() == capacity) throw new RuntimeException("Queue is full");
        arr[front++] = elm;
        front = fixIndex(front);
        size++;
    }

    private int fixIndex(int index){
        return index % capacity;
    }

    /**
     * Pop top element from the stack
     */
    @Override
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (size() == 0) throw new RuntimeException("Queue is empty");
        T data = (T) arr[rear++];
        rear = fixIndex(rear);
        size--;
        return data;
    }

    /**
     * Returns the top element without removing it.
     */
    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        return (T) arr[rear];
    }

    @SuppressWarnings("unchecked")
    public T peekLast(){ return (T) arr[fixIndex(front - 1)]; }

    /**
     * Returns Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int newRear = rear;
            @Override
            public boolean hasNext() {
                return front != newRear;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                T data = (T) arr[newRear++];
                newRear = fixIndex(newRear);
                return data;
            }
        };
    }
}
