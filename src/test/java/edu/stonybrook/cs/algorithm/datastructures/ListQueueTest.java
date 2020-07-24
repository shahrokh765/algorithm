package edu.stonybrook.cs.algorithm.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListQueueTest {

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
        ListQueue<Student> students = new ListQueue<>();
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
        ListQueue<Student> students = new ListQueue<>();
        assertTrue(students.isEmpty());
        for (int i = 0; i < 10; i++) {
            students.enqueue(new Student(i, "student " + i));
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
        ListQueue<Student> students = new ListQueue<>();
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
        ListQueue<Student> students = new ListQueue<>();
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
        ListQueue<Student> students = new ListQueue<>();
        students.dequeue();
    }

    @Test
    public void peekLast() {
    }

    @Test
    public void iterator() {
        ListQueue<Student> students = new ListQueue<>();
        Student first = new Student(123, "first test");
        students.enqueue(first);
        for (int i = 0; i < 10; i++) {
            students.enqueue(new Student(i, "student " + i));
        }
        Student last = new Student(1233, "last test");
        students.enqueue(last);
        for (Student student : students)
            System.out.println(student);
    }
}