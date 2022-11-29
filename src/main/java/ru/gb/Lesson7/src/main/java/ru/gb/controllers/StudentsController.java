package ru.gb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.entities.Student;
import ru.gb.exceptions.StudentNotFoundException;
import ru.gb.services.StudentsService;

@Controller
@RequestMapping("/students")
public class StudentsController {

    private StudentsService studentsService;

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentsService.findAll());
        return "students";
    }

    @GetMapping("/{id}")
    public String getStudentById(Model model, @PathVariable Long id) {
        model.addAttribute("student", studentsService.findById(id).orElseThrow(() -> new StudentNotFoundException("Студент с таким id: "+ id + " не найден")));
        return "students";
    }

    @GetMapping("/add")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        studentsService.saveOrUpdate(student);
        return "redirect:/student";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentsService.deleteById(id);
        return "redirect:/students";
    }
}
