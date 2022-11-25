package ru.gb.Lesson5;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.gb.Lesson5.entity.Student;

public class MySessionFactory {
  private static final SessionFactory sessionFactory =
            new Configuration()
                    .addAnnotatedClass(Student.class)
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
