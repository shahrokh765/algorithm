package edu.stonybrook.cs.algorithm.datastructures;

import java.util.Iterator;
import java.util.function.Consumer;

/**Generic Dynamic Array
 * @author Mohammad Ghaderibaneh <mghaderibane@cs.stonybrook>
 * @version 1.0
 * @since 1.0*/
public class DynamicArray<T> implements Iterable<T>{
    private T[] arr;      // underlying array
    private int len = 0;    // size of elements in array
    private int capacity;   // actual array size

    /**
     * @param initialCapacity initial capacity of the array
     */
    public DynamicArray(int initialCapacity){
        if (initialCapacity < 0) throw new IllegalArgumentException("Negative Capacity (" + initialCapacity +
                ") not accepted");
        this.capacity = initialCapacity;
        arr = (T[]) new Object[capacity];
    }

    /**
     * DynamicArray of 16 is created*/
    public DynamicArray(){
        this(16);
    }

    public int size(){ return len; }

    public boolean isEmpty(){ return size() == 0; }

    private void checkIdx(int idx){
        if (idx < 0 || idx >= len) throw new IndexOutOfBoundsException();
    }

    public T get(int index){
        checkIdx(index);
        return arr[index];
    }

    public void set(T object, int index){
        checkIdx(index);
        arr[index] = object;
    }

    public void clear(){
        for (int i = 0; i < len; i++) arr[i] = null;
        len = 0;
    }

    public void add(T object){
        if (len + 1 >= capacity){   // need to be resized, the last one is always null to avoid outOfBound exception
            if (capacity == 0) capacity = 1;
            else capacity *= 2;
            T[] newArr = (T[]) new Object[capacity];
            for (int i = 0; i < len; i++)
                newArr[i] = arr[i];
            arr = newArr;
        }
        arr[len++] = object;
    }

    public T removeAt(int index){
        checkIdx(index);
        T object = arr[index];
        for (int i = index; i < len; i++)   // swapping elements at > index to left
            arr[i] = arr[i+1];
        arr[--len] = null;
        shrink();
        return object;
    }

    private void shrink() {
        if (len + 1 > capacity / 2) return;
        capacity /= 2;
        T[] newArr = (T[]) new Object[capacity];
        for (int i = 0; i < len; i++)
            newArr[i] = arr[i];
        arr = newArr;
    }

    public int indexOf(Object obj){
        for (int i = 0; i < len; i++){
            if (obj == null){
                if (arr[i] == null) return i;
            }else{
                if (obj.equals(arr[i])) return i;
            }
        }
        return -1;
    }

    public boolean remove(Object obj){
        int idx = indexOf(obj);
        if (idx == -1)
            return false;
        removeAt(idx);
        return true;

    }

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
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next() {
                return arr[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Deletion is not allowed here");
            }
        };
    }

    @Override
    public String toString(){
        if (len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i = 0; i < len - 1; i++) sb.append(arr[i] + ", ");
            return sb.append(arr[len - 1] + "]").toString();
        }
    }
}
