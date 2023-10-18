package com.App.SunuScol.controller;

import com.App.SunuScol.model.Student;
import com.App.SunuScol.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StudentController  {
    private  final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){this.studentService = studentService; }

    //    Acquérir  les utilisateurs
    @RequestMapping(method = RequestMethod.GET, value = "/students")
    public List<Student> getStudents(){return studentService.getStudents();}

    //    Acquérir  un utilisateurs
    @RequestMapping(method = RequestMethod.GET, value = "/student/{id}")
    public Student getStudent(@PathVariable long id){return studentService.getStudent(id); }

    //    Ajouter un utilisateur
    @RequestMapping(method =  RequestMethod.POST, value = "/student/{id}")
    public void addStudent(@RequestBody Student student){studentService.addStudent(student);}

    //Modifier les informations d'un utilisateur
    @RequestMapping(method =  RequestMethod.PUT, value = "/student/{id}")
    public void updateStudent(@RequestBody Student student, @PathVariable long id){
        student.setStudentId(id);
        studentService.updateStudent(student);
    }

    //Supprimer un Student
    @RequestMapping(method = RequestMethod.DELETE, value = "/student/{id}")
    public void deleteStudent(@PathVariable long id){studentService.deleteStudent(id);}
}

