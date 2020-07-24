package edu.stonybrook.cs.algorithm.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayQueueTest {
    private class Student {
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
    }

    @Test
    public void size() {
        ArrayQueue<Student> students = new ArrayQueue<>(1000);
        for (int i = 0; i < 10; i++) {
            students.enqueue(new Student(i, "student " + i));
            assertEquals(students.size(), i+1);
        }
        for (int i = 9; i >= 0; i--) {
            students.dequeue();
            assertEquals(students.size(), i);
        }
    }

    @Test
    public void isEmpty() {
        ArrayQueue<Student> students = new ArrayQueue<>(100);
        assertTrue(students.isEmpty());
        for (int i = 0; i < 80; i++) {
            students.enqueue(new Student(i, "student " + i));
            assertFalse(students.isEmpty());
        }
        while (students.size() != 40) {
            students.dequeue();
            assertFalse(students.isEmpty());
        }
        for (int i = 0; i < 50; i++) {
            students.enqueue(new Student(i+100, "student " + i+100));
            assertFalse(students.isEmpty());
        }
        while (students.size() != 1) {
            students.dequeue();
            assertFalse(students.isEmpty());
        }
        students.dequeue();
        assertTrue(students.isEmpty());
    }

    @Test
    public void enqueue() {
        ArrayQueue<Student> students = new ArrayQueue<>(1000);
        Student first = new Student(123, "first test");
        students.enqueue(first);
        for (int i = 0; i < 10; i++) {
            students.enqueue(new Student(i, "student " + i));
        }
        Student last = new Student(1233, "last test");
        students.enqueue(last);
        assertEquals(first, students.peek());
        assertEquals(last, students.peekLast());
    }

    @Test
    public void dequeue() {
        ArrayQueue<Student> students = new ArrayQueue<>(1000);
        Student first = new Student(123, "first test");
        students.enqueue(first);
        for (int i = 0; i < 10; i++) {
            students.enqueue(new Student(i, "student " + i));
        }
        Student last = new Student(1233, "last test");
        students.enqueue(last);
        assertEquals(first, students.dequeue());
        assertNotEquals(first, students.peek());
    }

    @Test(expected = RuntimeException.class)
    public void peek() {
        ArrayQueue<Student> students = new ArrayQueue<>(1000);
        students.dequeue();
    }

    @Test
    public void peekLast() {
    }

    @Test
    public void iterator() {
        ArrayQueue<Student> students = new ArrayQueue<>(1000);
        Student first = new Student(123, "first test");
        students.enqueue(first);
        for (int i = 0; i < 10; i++) {
            students.enqueue(new Student(i, "student " + i));
        }
        Student last = new Student(1233, "last test");
        students.enqueue(last);
        students.dequeue();
        students.dequeue();
        for (Student student : students)
            System.out.println(student);
    }

}