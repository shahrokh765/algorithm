package edu.stonybrook.cs.algorithm.datastructures;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SinglyLinkedListTest {

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
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++)
            students.add(new Student(i, "student " + i));
        Assert.assertFalse(students.isEmpty());
        students.clear();
        Assert.assertTrue(students.isEmpty());
//        assertNull(students.peekFirst());
//        assertNull(students.peekLast());
    }

    @Test
    public void size() {
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "student " + i));
            assertEquals(students.size(), i+1);
        }
        for (int i = 9; i >= 0; i--) {
            if (i%2==0)
                students.removeFirst();
            else
                students.removeLast();
            assertEquals(students.size(), i);
        }
    }

    @Test
    public void isEmpty() {
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        assertTrue(students.isEmpty());
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "student " + i));
        }
        assertFalse(students.isEmpty());
        students.clear();
        assertTrue(students.isEmpty());
    }

    @Test
    public void addLast() {
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        assertTrue(students.isEmpty());
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "student " + i));
        }
        Student last = new Student(1234, "test");
        students.addLast(last);
        assertEquals(students.peekLast(), last);
    }

    @Test
    public void add() {
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        assertTrue(students.isEmpty());
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "student " + i));
        }
        Student last = new Student(1234, "test");
        students.add(last);
        assertEquals(students.peekLast(), last);
    }

    @Test
    public void addFirst() {
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        assertTrue(students.isEmpty());
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "student " + i));
        }
        Student fist = new Student(1234, "test");
        students.addFirst(fist);
        assertEquals(students.peekFirst(), fist);
    }

    @Test
    public void addAt() {
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        assertTrue(students.isEmpty());
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "student " + i));
        }
        Student student = new Student(1234, "test");
        students.addAt(student, 5);
        assertEquals(students.get(5), student);
    }

    @Test
    public void peekFirst() {
    }

    @Test
    public void peekLast() {
    }

    @Test
    public void removeFirst() {
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        assertTrue(students.isEmpty());
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "student " + i));
        }
        Student fist = new Student(1234, "test");
        students.addFirst(fist);
        assertEquals(students.peekFirst(), fist);
        students.removeFirst();
        assertNotEquals(students.peekFirst(), fist);
        assertEquals(students.peekFirst().id, 0);
    }

    @Test
    public void removeLast() {
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "student " + i));
            assertEquals(students.size(), i+1);
        }
        for (int i = 9; i >= 1; i--) {
            students.removeLast();
            assertNotEquals(students.peekLast().id, i);
            assertEquals(students.peekLast().id, i-1);
        }
    }

    @Test
    public void removeAt() {
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "student " + i));
            assertEquals(students.size(), i+1);
        }
        students.removeAt(5);
        assertEquals(students.get(5).id, 6);
    }

    @Test
    public void get() {
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "student " + i));
            assertEquals(students.get(i).id, i);
        }
        for (int i = 9; i >= 1; i--) {
            students.removeLast();
            assertEquals(students.get(i-1).id, i-1);
        }
    }

    @Test
    public void remove() {
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "student " + i));
            assertEquals(students.get(i).id, i);
        }
        students.remove(students.get(5));
        assertNotEquals(students.get(5), 6);
    }

    @Test
    public void indexOf() {
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "student " + i));
            assertEquals(students.get(i).id, i);
        }
        int cnt = 0;
        for (Student student : students)
            assertEquals(students.indexOf(student), cnt++);
    }

    @Test
    public void contains() {
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "student " + i));
            assertEquals(students.get(i).id, i);
        }
        for (Student student : students)
            assertTrue(students.contains(student));
    }

    @Test
    public void iterator() {
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "student " + i));
            assertEquals(students.get(i).id, i);
        }
        for (Student student : students)
            System.out.println(student);
    }

    @Test
    public void testToString() {
        SinglyLinkedList<Student> students = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "student " + i));
            assertEquals(students.get(i).id, i);
        }
        System.out.println(students);
    }
}