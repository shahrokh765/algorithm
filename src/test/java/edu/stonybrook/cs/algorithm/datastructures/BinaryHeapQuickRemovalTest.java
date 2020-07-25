package edu.stonybrook.cs.algorithm.datastructures;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class BinaryHeapQuickRemovalTest {
    private class Student implements Comparable<Student>{
        String name;
        int id;
        Student(int id, String name){
            super();
            this.name = name;
            this.id = id;
        }
        @Override
        public String toString(){
            return String.format("{id = %d, name = %s}", id, name);
        }

        /**
         * @param that the object to be compared.
         * @return a negative integer, zero, or a positive integer as this object
         * is less than, equal to, or greater than the specified object.
         * @throws NullPointerException if the specified object is null
         * @throws ClassCastException   if the specified object's type prevents it
         *                              from being compared to this object.
         */
        @Override
        public int compareTo(Student that) {
            if (this.id < that.id)
                return -1;
            if (this.id > that.id)
                return 1;
            return this.name.compareTo(that.name);
        }
    }

    @Test
    public void peek() {
        BinaryHeapQuickRemoval<Student> pq = new BinaryHeapQuickRemoval<Student>();
        Student least = new Student(0, "");
        for (int i = 0; i < 10000; i++){
            int id = ThreadLocalRandom.current().nextInt(1, 10000);
            pq.add(new Student(id,"student " + id));
        }
        pq.add(least);
        assertEquals(pq.peek(), least);
    }

    @Test
    public void add() {
    }

    @Test
    public void poll() {
        Student[] students = new Student[10000];
        Student least = new Student(0, "");
        Student least2 = new Student(0, "x");
        for (int i = 0; i < 9998; i++){
            int id = ThreadLocalRandom.current().nextInt(1, 10000);
            students[i] = new Student(id, "student " + id);
        }
        students[9998] = least;
        students[9999] = least2;
        BinaryHeapQuickRemoval<Student> pq = new BinaryHeapQuickRemoval<>(students);
        assertEquals(pq.poll(), least);
    }

    @Test
    public void isEmpty() {
        Collection<Student> students = new ArrayList<>();
        Student least = new Student(0, "");
        Student least2 = new Student(0, "x");
        students.add(least);
        for (int i = 0; i < 10000; i++){
            int id = ThreadLocalRandom.current().nextInt(1, 10000);
            students.add(new Student(id, "student " + id));
        }
        students.add(least2);
        BinaryHeapQuickRemoval<Student> pq = new BinaryHeapQuickRemoval();
        assertTrue(pq.isEmpty());
        for (Student student : students){
            pq.add(student);
            assertFalse(pq.isEmpty());
        }
        pq.clear();
        pq.poll();
        assertTrue(pq.isEmpty());
        BinaryHeapQuickRemoval<Student> pq2 = new BinaryHeapQuickRemoval<>(students);
        assertFalse(pq2.isEmpty());
        while (!pq2.isEmpty())
            pq2.poll();
        assertTrue(pq2.isEmpty());
    }

    @Test
    public void size() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void contains() {
        Collection<Student> students = new ArrayList<>();
        Student least = new Student(0, "");
        Student least2 = new Student(0, "x");
        students.add(least);
        for (int i = 0; i < 10000; i++){
            int id = ThreadLocalRandom.current().nextInt(1, 10000);
            students.add(new Student(id, "student " + id));
        }
        students.add(least2);
        BinaryHeapQuickRemoval<Student> pq = new BinaryHeapQuickRemoval(students);
        assertTrue(pq.contains(least));
    }

    @Test
    public void remove() {
        Collection<Student> students = new ArrayList<>();
        Student least = new Student(0, "");
        Student least2 = new Student(0, "x");
        students.add(least);
        for (int i = 0; i < 10000; i++){
            int id = ThreadLocalRandom.current().nextInt(1, 10000);
            students.add(new Student(id, "student " + id));
        }
        students.add(least2);
        students.add(least);
        BinaryHeapQuickRemoval<Student> pq = new BinaryHeapQuickRemoval(students);
        assertTrue(pq.remove(least));
        assertTrue(pq.remove(least));
        assertFalse(pq.remove(least));
    }

    @Test
    public void testToString() {
        Collection<Student> students = new ArrayList<>();
        Student least = new Student(0, "");
        Student least2 = new Student(0, "x");
        students.add(least);
        for (int i = 0; i < 10000; i++){
            int id = ThreadLocalRandom.current().nextInt(1, 10000);
            students.add(new Student(id, "student " + id));
        }
        students.add(least2);
        BinaryHeapQuickRemoval<Student> pq = new BinaryHeapQuickRemoval(students);
        System.out.println(pq);
    }

}