package edu.stonybrook.cs.algorithm.datastructures;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UnionFindTest {
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
    public void add() {
        Student[] students = new Student[10];
        for (int i = 0; i < students.length; i++)
            students[i] = new Student(i, "student " + i);
        UnionFind<Student> unionFind = new UnionFind<>(students);
        Student student11 = new Student(10, "student " + 10);
        unionFind.add(student11);
        unionFind.add(students[5]);
        assertEquals(unionFind.size(), 11);
    }

    @Test
    public void find() {
        Student[] students = new Student[8];
        for (int i = 0; i < students.length; i++)
            students[i] = new Student(i, "student " + i);
        UnionFind<Student> unionFind = new UnionFind<>(students);
        unionFind.unify(students[0], students[1]);
        assertEquals(unionFind.find(students[0]), unionFind.find(students[1]));
        unionFind.unify(students[2], students[3]);
        assertEquals(unionFind.find(students[2]), unionFind.find(students[3]));
        unionFind.unify(students[4], students[5]);
        assertEquals(unionFind.find(students[4]), unionFind.find(students[5]));
        unionFind.unify(students[6], students[7]);

        Student student8 = new Student(8, "student 8");
        Student student9 = new Student(9, "Student 9");
        unionFind.add(student8);
        unionFind.add(student9);

        unionFind.unify(student8, student9);
        assertEquals(unionFind.find(student8), unionFind.find(student9));
        unionFind.unify(student9, students[6]);
        assertEquals(unionFind.find(students[6]), unionFind.find(student9));
        unionFind.unify(students[7], students[5]);
        assertEquals(unionFind.find(students[5]), unionFind.find(students[7]));
        unionFind.unify(students[0], students[2]);
        assertEquals(unionFind.find(students[0]), unionFind.find(students[2]));
        unionFind.unify(students[3], students[4]);
        assertEquals(unionFind.find(students[3]), unionFind.find(students[4]));
        unionFind.unify(students[1], students[6]);
        assertEquals(unionFind.find(students[1]), unionFind.find(students[6]));
        unionFind.unify(student8, student9);
        assertEquals(unionFind.find(student8), unionFind.find(student9));

    }

    @Test
    public void connected() {
    }

    @Test
    public void componentSize() {
        Student[] students = new Student[8];
        for (int i = 0; i < students.length; i++)
            students[i] = new Student(i, "student " + i);
        UnionFind<Student> unionFind = new UnionFind<>(students);

        unionFind.unify(students[0], students[1]);
        assertEquals(unionFind.componentSize(students[0]), 2 );
        assertEquals(unionFind.componentSize(students[1]), 2 );
        unionFind.unify(students[2], students[3]);
        assertEquals(unionFind.componentSize(students[2]), 2 );
        unionFind.unify(students[4], students[5]);
        assertEquals(unionFind.componentSize(students[4]), 2 );
        unionFind.unify(students[6], students[7]);
        assertEquals(unionFind.componentSize(students[6]), 2 );

        Student student8 = new Student(8, "student 8");
        Student student9 = new Student(9, "Student 9");
        unionFind.add(student8);
        assertEquals(unionFind.componentSize(student8), 1 );
        unionFind.add(student9);

        unionFind.unify(student8, student9);
        assertEquals(unionFind.componentSize(student8), 2 );
        unionFind.unify(student9, students[6]);
        assertEquals(unionFind.componentSize(student9), 4);
        unionFind.unify(students[7], students[5]);
        assertEquals(unionFind.componentSize(students[5]), 6);
        unionFind.unify(students[0], students[2]);
        assertEquals(unionFind.componentSize(students[0]), 4);
        unionFind.unify(students[3], students[4]);
        assertEquals(unionFind.componentSize(students[0]), 10);
        unionFind.unify(students[1], students[6]);
        assertEquals(unionFind.componentSize(students[0]), 10);
        unionFind.unify(student8, student9);
        assertEquals(unionFind.componentSize(students[0]), 10);

    }

    @Test
    public void size() {
    }

    @Test
    public void components() {
        Student[] students = new Student[8];
        for (int i = 0; i < students.length; i++)
            students[i] = new Student(i, "student " + i);
        UnionFind<Student> unionFind = new UnionFind<>(students);

        unionFind.unify(students[0], students[1]);
        assertEquals(unionFind.components(), 7);
        unionFind.unify(students[2], students[3]);
        assertEquals(unionFind.components(), 6);
        unionFind.unify(students[4], students[5]);
        assertEquals(unionFind.components(), 5);
        unionFind.unify(students[6], students[7]);
        assertEquals(unionFind.components(), 4);

        Student student8 = new Student(8, "student 8");
        Student student9 = new Student(9, "Student 9");
        unionFind.add(student8);
        assertEquals(unionFind.components(), 5);
        unionFind.add(student9);
        assertEquals(unionFind.components(), 6);

        unionFind.unify(student8, student9);
        assertEquals(unionFind.components(), 5);
        unionFind.unify(student9, students[6]);
        assertEquals(unionFind.components(), 4);
        unionFind.unify(students[7], students[5]);
        assertEquals(unionFind.components(), 3);
        unionFind.unify(students[0], students[2]);
        assertEquals(unionFind.components(), 2);
        unionFind.unify(students[3], students[4]);
        assertEquals(unionFind.components(), 1);
        unionFind.unify(students[1], students[6]);
        assertEquals(unionFind.components(), 1);
        unionFind.unify(student8, student9);
        assertEquals(unionFind.components(), 1);
    }

    @Test
    public void union() {
    }

    @Test
    public void componentElements(){
        Student[] students = new Student[8];
        for (int i = 0; i < students.length; i++)
            students[i] = new Student(i, "student " + i);
        UnionFind<Student> unionFind = new UnionFind<>(students);

        unionFind.unify(students[0], students[1]);
        unionFind.unify(students[2], students[3]);
        unionFind.unify(students[4], students[5]);
        unionFind.unify(students[6], students[7]);

        Student student8 = new Student(8, "student 8");
        Student student9 = new Student(9, "Student 9");
        unionFind.add(student8);
        unionFind.add(student9);

        unionFind.unify(student8, student9);
        unionFind.unify(student9, students[6]);
//        unionFind.unify(students[7], students[5]);
        unionFind.unify(students[0], students[2]);
        unionFind.unify(student8, student9);

        int numComponent = 0;
        for (ArrayList<Student> component : unionFind.componentElements()) {
            System.out.println("Component #" + numComponent++);
            for (Student student : component)
                System.out.println(student);
        }

    }
}