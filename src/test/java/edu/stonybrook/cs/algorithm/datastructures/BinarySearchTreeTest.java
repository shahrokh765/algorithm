package edu.stonybrook.cs.algorithm.datastructures;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {
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
    public void isEmpty() {
        BinarySearchTree<Student> binarySearchTree = new BinarySearchTree<>();
        assertTrue(binarySearchTree.isEmpty());
        for (int i = 0; i < 10; i++){
            int id = ThreadLocalRandom.current().nextInt(0, 10000);
            binarySearchTree.add(new Student(id, "student " + id));
            assertFalse(binarySearchTree.isEmpty());
        }
    }

    @Test
    public void size() {
        BinarySearchTree<Student> binarySearchTree = new BinarySearchTree<>();
        for (int i = 0; i < 10; i++){
            int id = ThreadLocalRandom.current().nextInt(0, 10000);
            binarySearchTree.add(new Student(id, "student " + id));
            assertEquals(binarySearchTree.size(), i+1);
        }
    }

    @Test
    public void add() {
        BinarySearchTree<Student> binarySearchTree = new BinarySearchTree<>();
        for (int i = 0; i < 10; i++){
            int id = ThreadLocalRandom.current().nextInt(0, 10000);
            binarySearchTree.add(new Student(id, "student " + id));
        }
    }

    @Test
    public void contains() {
        BinarySearchTree<Student> binarySearchTree = new BinarySearchTree<>();
        Student student = new Student(1562, "");
        for (int i = 0; i < 100000; i++){
            int id = ThreadLocalRandom.current().nextInt(0, 10000);
            binarySearchTree.add(new Student(id, "student " + id));
        }
        binarySearchTree.add(student);
        assertTrue(binarySearchTree.contains(student));
    }

    @Test
    public void findMax() {
        BinarySearchTree<Student> binarySearchTree = new BinarySearchTree<>();
        assertNull(binarySearchTree.findMax());
        Student max = new Student(45000, "");
        for (int i = 0; i < 100000; i++){
            int id = ThreadLocalRandom.current().nextInt(0, 10000);
            binarySearchTree.add(new Student(id, "student " + id));
        }
        binarySearchTree.add(max);
        assertEquals(binarySearchTree.findMax(), max);
    }

    @Test
    public void findMin() {
        BinarySearchTree<Student> binarySearchTree = new BinarySearchTree<>();
        assertNull(binarySearchTree.findMin());
        Student min = new Student(-1, "");
        for (int i = 0; i < 100000; i++){
            int id = ThreadLocalRandom.current().nextInt(0, 10000);
            binarySearchTree.add(new Student(id, "student " + id));
        }
        binarySearchTree.add(min);
        assertEquals(binarySearchTree.findMin(), min);
    }

    @Test
    public void height() {
        BinarySearchTree<Student> binarySearchTree = new BinarySearchTree<>();
        for (int i = 0; i < 100; i++){
            binarySearchTree.add(new Student(i, "student " + i));
            assertEquals(i + 1, binarySearchTree.height());
        }
    }

    @Test
    public void remove() {
        BinarySearchTree<Student> binarySearchTree = new BinarySearchTree<>();
        Student studentRemove = null;
        for (int i = 0; i < 100000; i++){
            int id = ThreadLocalRandom.current().nextInt(0, 10000);
            Student student = new Student(id, "student " + id);
            binarySearchTree.add(student);
            if (i == 100) studentRemove = student;
        }
        assertTrue(binarySearchTree.contains(studentRemove));
        assertTrue(binarySearchTree.remove(studentRemove));
        assertFalse(binarySearchTree.contains(studentRemove));
    }


    @Test
    public void traverse() {
        TreeTraversalOrder ORDER = TreeTraversalOrder.LEVEL_ORDER;
        BinarySearchTree<Student> binarySearchTree = new BinarySearchTree<>();
        Student studentRemove = null;
        for (int i = 0; i < 10; i++){
            int id = ThreadLocalRandom.current().nextInt(0, 10000);
            Student student = new Student(id, "student " + id);
            binarySearchTree.add(student);
            if (i == 8) studentRemove = student;
        }
        for (Student student : binarySearchTree.traverse(ORDER))
            System.out.print(student);
        System.out.println("");
        System.out.println(studentRemove);
        binarySearchTree.remove(studentRemove);
        for (Student student : binarySearchTree.traverse(ORDER))
            System.out.print(student);
    }
}