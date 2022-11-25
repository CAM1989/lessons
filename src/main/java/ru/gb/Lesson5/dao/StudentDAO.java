package ru.gb.Lesson5.dao;

import java.util.List;

import ru.gb.Lesson5.entity.Student;

public interface StudentDAO {

    void add(List<Student> students);

    void del(Long id);

    void update(Student student);

    Student getStudent(Long id);

    List<Student> getStudents();
}
