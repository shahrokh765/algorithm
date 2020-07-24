package edu.stonybrook.cs.algorithm.datastructures;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoublyLinkedListTest {

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
    public void clear() {
        DoublyLinkedList<Student> students = new DoublyLinkedList<>();
        for (int i = 0; i < 1000; i++)
            students.add(new Student(i, "student " + i));
        Assert.assertFalse(students.isEmpty());
        students.clear();
        Assert.assertTrue(students.isEmpty());
    }

    @Test
    public void size() {
        DoublyLinkedList<Student> students = new DoublyLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            students.add(new Student(i, "student " + i));
            Assert.assertEquals(students.size(), i+1);
        }
        students.clear();
        Assert.assertEquals(students.size(), 0);
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void addLast() {
        DoublyLinkedList<Student> students = new DoublyLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            students.add(new Student(i, "student " + i));
            Assert.assertEquals(students.size(), i+1);
        }
        Student last = new Student(1234, "last");
        students.addLast(last);
        assertEquals(last, students.peekLast());
    }

    @Test
    public void addFirst() {
        DoublyLinkedList<Student> students = new DoublyLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            students.add(new Student(i, "student " + i));
            Assert.assertEquals(students.size(), i+1);
        }
        Student first = new Student(1234, "first");
        students.addFirst(first);
        assertEquals(first, students.peekFirst());
    }

    @Test
    public void addAt() {
        DoublyLinkedList<Student> students = new DoublyLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            students.add(new Student(i, "student " + i));
            Assert.assertEquals(students.size(), i+1);
        }
        Student first = new Student(1234, "first");
        students.addAt(first, 10);
        assertEquals(first, students.get(10));
    }

    @Test
    public void peekFirst() {
    }

    @Test
    public void peekLast() {
    }

    @Test
    public void removeFirst() {
        DoublyLinkedList<Student> students = new DoublyLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            students.add(new Student(i, "student " + i));
            Assert.assertEquals(students.size(), i+1);
        }
        Student first = new Student(1234, "first");
        students.addFirst(first);
        assertEquals(first, students.peekFirst());
        students.removeFirst();
        Assert.assertNotEquals(first, students.peekFirst());
    }

    @Test
    public void removeLast() {
        DoublyLinkedList<Student> students = new DoublyLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            students.add(new Student(i, "student " + i));
            Assert.assertEquals(students.size(), i+1);
        }
        Student last = new Student(1234, "last");
        students.addLast(last);
        assertEquals(last, students.peekLast());
        students.removeLast();
        Assert.assertNotEquals(last, students.peekLast());
    }

    @Test
    public void removeAt() {
        DoublyLinkedList<Student> students = new DoublyLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            students.add(new Student(i, "student " + i));
            Assert.assertEquals(students.size(), i+1);
        }
        Student first = new Student(1234, "first");
        students.addAt(first, 10);
        assertEquals(first, students.get(10));
        students.removeAt(10);
        assertNotEquals(first, students.get(10));
    }

    @Test
    public void remove() {
        DoublyLinkedList<Student> students = new DoublyLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            students.add(new Student(i, "student " + i));
            Assert.assertEquals(students.size(), i+1);
        }
        Student first = new Student(1234, "first");
        students.addAt(first, 10);
        assertEquals(first, students.get(10));
        students.remove(first);
        assertNotEquals(first, students.get(10));
    }

    @Test
    public void indexOf() {
        DoublyLinkedList<Student> students = new DoublyLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            students.add(new Student(i, "student " + i));
            Assert.assertEquals(students.size(), i+1);
        }
        Student first = new Student(1234, "first");
        students.addAt(first, 10);
        assertEquals(10, students.indexOf(first));
        assertEquals(-1, students.indexOf(new Student(1234, "first")));

    }

    @Test
    public void contains() {
        DoublyLinkedList<Student> students = new DoublyLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            students.add(new Student(i, "student " + i));
            Assert.assertEquals(students.size(), i+1);
        }
        Student first = new Student(1234, "first");
        Student first2 = new Student(1234, "first");
        students.addAt(first, 10);
        assertTrue(students.contains(first));
        assertFalse(students.contains(first2));
    }

    @Test
    public void iterator() {
        DoublyLinkedList<Student> students = new DoublyLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            students.add(new Student(i, "student " + i));
        }
        for (Student student : students)
            System.out.println(String.format("Student %s", student));
    }

    @Test
    public void testToString() {
        DoublyLinkedList<Student> students = new DoublyLinkedList<>();
        for (int i = 0; i < 1000; i++) {
            students.add(new Student(i, "student " + i));
        }
        System.out.println(students);
    }
}