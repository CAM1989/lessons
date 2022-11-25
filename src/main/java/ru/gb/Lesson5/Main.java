package ru.gb.Lesson5;

import ru.gb.Lesson5.entity.Student;
import ru.gb.Lesson5.dao.StudentDAOImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        StudentDAOImpl student = new StudentDAOImpl();
        student.add((List<Student>) new Student("Student #1", "V"));
        student.add((List<Student>) new Student("Student #2", "V"));
        System.out.println(student.getStudent(1L));
        student.del(1L);
        student.update(new Student(2L, "Student #UPDATE", "V"));
        System.out.println(student.getStudent(2L));

        for (int i = 0; i < 1000; i++) {
            student.add((List<Student>) new Student("Student #" + i, "V"));
        }
        System.out.println(student.getStudents());
    }
}
