package edu.stonybrook.cs.algorithm.datastructures;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayStackTest {

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
        ArrayStack<Student> students = new ArrayStack<>();
        for (int i = 0; i < 10; i++) {
            students.push(new Student(i, "student " + i));
            assertEquals(students.size(), i+1);
        }
        for (int i = 9; i >= 0; i--) {
            students.pop();
            assertEquals(students.size(), i);
        }
    }

    @Test
    public void isEmpty() {
        ArrayStack<Student> students = new ArrayStack<>();
        assertTrue(students.isEmpty());
        for (int i = 0; i < 10; i++) {
            students.push(new Student(i, "student " + i));
        }
        assertFalse(students.isEmpty());
        while (students.size() != 1) {
            students.pop();
            assertFalse(students.isEmpty());
        }
        students.pop();
        assertTrue(students.isEmpty());

    }

    @Test
    public void push() {
    }

    @Test
    public void pop() {
        ArrayStack<Student> students = new ArrayStack<>();
//        students.pop();
    }

    @Test
    public void peek() {
        ArrayStack<Student> students = new ArrayStack<>();
        for (int i = 0; i < 10; i++) {
            students.push(new Student(i, "student " + i));
        }
        Student last = new Student(1111, "test");
        students.push(last);
        Assert.assertEquals(last, students.peek());
        Assert.assertEquals(last, students.pop());
    }

    @Test
    public void peekFirst() {
        ArrayStack<Student> students = new ArrayStack<>();
        Student first = new Student(1111, "test");
        students.push(first);
        for (int i = 0; i < 10; i++) {
            students.push(new Student(i, "student " + i));
        }

        Assert.assertEquals(first, students.peekFirst());
    }

    @Test
    public void iterator() {
        ArrayStack<Student> students = new ArrayStack<>();
        Student first = new Student(1111, "test");
        students.push(first);
        for (int i = 0; i < 10; i++) {
            students.push(new Student(i, "student " + i));
        }
        for (Student student : students)
            System.out.println(student);
    }
}