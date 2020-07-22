package edu.stonybrook.cs.algorithm.datastructures;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class DynamicArrayTest {

    private class Student {
        String name;
        int id;
        Student(int id, String name){
            super();
            this.name = name;
            this.id = id;
        }
    }

    @Test
    public void size() {
        DynamicArray<Student> students = new DynamicArray<>(8);
        Assert.assertEquals(students.size(), 0);
        for (int i = 0; i < 100000; i++){
            students.add(new Student(i, "student" + i));
            Assert.assertEquals(students.size(), i+1);
        }
    }

    @Test
    public void isEmpty() {
        DynamicArray<Student> students = new DynamicArray<>(8);
        Assert.assertTrue(students.isEmpty());
        for (int i = 0; i < 100000; i++){
            students.add(new Student(i, "student" + i));
            Assert.assertFalse(students.isEmpty());
        }
    }

    @Test
    public void get() {
        DynamicArray<Student> students = new DynamicArray<>(8);
        for (int i = 0; i < 100000; i++){
            students.add(new Student(i, "student" + i));
        }

        for (int i = 0; i < 1000; i++){
            int idx = ThreadLocalRandom.current().nextInt(0, 100000);
            Assert.assertEquals(students.get(idx).id, idx);
            Assert.assertEquals(students.get(idx).name, "student"+idx);
        }

    }

    @Test
    public void set() {
        DynamicArray<Student> students = new DynamicArray<>(8);
        for (int i = 0; i < 100000; i++){
            students.add(new Student(i, "student" + i));
        }
        Assert.assertEquals(students.get(500).id, 500);
        students.set(new Student(222, ""), 500);
        Assert.assertEquals(students.get(500).id, 222);

    }

    @Test
    public void clear() {
        DynamicArray<Student> students = new DynamicArray<>(8);
        for (int i = 0; i < 100000; i++){
            students.add(new Student(i, "student" + i));
        }
        Assert.assertFalse(students.isEmpty());
        students.clear();
        Assert.assertTrue(students.isEmpty());
    }

    @Test
    public void add() {
        DynamicArray<Student> students = new DynamicArray<>(8);
        for (int i = 0; i < 100000; i++){
            students.add(new Student(i, "student" + i));
        }
        students.add(new Student(777, "TEST"));
        Assert.assertEquals(students.get(100000).id, 777);
        Assert.assertEquals(students.get(100000).name, "TEST");
    }

    @Test
    public void removeAtShrink(){
        DynamicArray<Student> students = new DynamicArray<>(8);
        for (int i = 0; i < 32; i++){
            students.add(new Student(i, "student" + i));
        }
        for (int i = 31; i > -1; i--)
            students.removeAt(i);
        Assert.assertTrue(students.isEmpty());
    }

    @Test
    public void removeAt() {
        DynamicArray<Student> students = new DynamicArray<>(8);
        for (int i = 0; i < 100000; i++){
            students.add(new Student(i, "student" + i));
        }
        Assert.assertEquals(students.get(1000).id, 1000);
        students.removeAt(1000);
        Assert.assertEquals(students.get(1000).id, 1001);
    }

    @Test
    public void indexOf() {
        DynamicArray<Student> students = new DynamicArray<>(8);
        for (int i = 0; i < 100000; i++){
            students.add(new Student(i, "student" + i));
        }
        Assert.assertEquals(students.indexOf(students.get(1000)), 1000);
    }

    @Test
    public void remove() {
        DynamicArray<Student> students = new DynamicArray<>(8);
        for (int i = 0; i < 100000; i++){
            students.add(new Student(i, "student" + i));
        }
        students.remove(students.get(1000));
        Assert.assertNotEquals(students.get(1000).id, 1000);
    }

    @Test
    public void contains() {
        DynamicArray<Student> students = new DynamicArray<>(8);
        for (int i = 0; i < 100000; i++){
            students.add(new Student(i, "student" + i));
            Assert.assertTrue(students.contains(students.get(i)));
        }
        Assert.assertFalse(students.contains(new Student(100, "student"+100)));
    }

    @Test
    public void iterator() {
        DynamicArray<Student> students = new DynamicArray<>(8);
        for (int i = 0; i < 100; i++){
            students.add(new Student(i, "student" + i));
            Assert.assertTrue(students.contains(students.get(i)));
        }
        for (Student student : students)
            System.out.println(student.name);
    }

    @Test
    public void toStrig() {
        DynamicArray<Student> students = new DynamicArray<>(8);
        for (int i = 0; i < 100; i++){
            students.add(new Student(i, "student" + i));
            Assert.assertTrue(students.contains(students.get(i)));
        }
        System.out.println(students);
    }
}