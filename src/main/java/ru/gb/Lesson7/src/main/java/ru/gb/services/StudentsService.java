package ru.gb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.entities.Student;
import ru.gb.repositories.StudentsRepository;

import java.util.Optional;
import java.util.List;

@Service
public class StudentsService {

    private StudentsRepository studentsRepository;

    @Autowired
    public void setStudentsRepository(StudentsRepository studentsRepository){
        this.studentsRepository = studentsRepository;
    }

    public void saveOrUpdate(Student student) {
        studentsRepository.save(student);
    }

    public Optional<Student> findById(Long id) {
        return studentsRepository.findById(id);
    }

    public void deleteById(Long id) {
        studentsRepository.deleteById(id);
    }

    public List<Student> findAll() {
        return studentsRepository.findAll();
    }

}