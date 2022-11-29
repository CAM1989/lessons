package ru.gb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.entities.Student;


@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {
}