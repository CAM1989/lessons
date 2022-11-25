package ru.gb.Lesson5.dao;

import java.util.List;

import org.hibernate.Session;
import ru.gb.Lesson5.MySessionFactory;
import ru.gb.Lesson5.entity.Student;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public void add(List<Student> students) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        students.forEach(student -> session.save(new Student(student.getName(), student.getMark())));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void del(Long id) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Student s = session.get(Student.class, id);
        if (s != null) {
            session.delete(s);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Student student) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Student studentFromDb = session.get(Student.class, student.getId());
        if (studentFromDb != null) {
            studentFromDb.setName(student.getName());
            studentFromDb.setMark(student.getMark());
            session.update(studentFromDb);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Student getStudent(Long id) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        session.getTransaction().commit();
        session.close();
        return student;
    }

    @Override
    public List<Student> getStudents() {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Student> students = session.createQuery("from Student as s order by s.id ", Student.class).list();
        session.getTransaction().commit();
        session.close();
        return students;
    }
}