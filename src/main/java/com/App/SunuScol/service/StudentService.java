package com.App.SunuScol.service;

import com.App.SunuScol.model.Student;
import com.App.SunuScol.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){this.studentRepository = studentRepository;}

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(student -> {
            students.add(student);
        });
        return students;
    }

    public Student getStudent(long id) { return studentRepository.findById(id).orElse(null); }

    public Student addStudent(Student student) {return studentRepository.save(student);}

    public void updateStudent(Student student) { studentRepository.save(student); }
    public void deleteStudent(long id) { studentRepository.deleteById(id);  }
}
