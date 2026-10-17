
package com.studentmanagement.controller;

import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.student.Student;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));
    }

    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @RequestBody Student details) {

        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        student.setName(details.getName());
        student.setEmail(details.getEmail());
        student.setBranch(details.getBranch());
        student.setYear(details.getYear());

        return repository.save(student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        repository.deleteById(id);
        return "Student deleted successfully";
    }
}
