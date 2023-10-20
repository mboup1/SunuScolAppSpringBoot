package com.App.SunuScol.controller;

import com.App.SunuScol.model.ClassStudent;
import com.App.SunuScol.model.Role;
import com.App.SunuScol.model.Student;
import com.App.SunuScol.model.User;
import com.App.SunuScol.repository.ClassStudentRepository;
import com.App.SunuScol.service.ClassStudentService;
import com.App.SunuScol.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassStudentController {
    private  final ClassStudentService classStudentService;
    private final StudentService studentService;

    @Autowired
    public ClassStudentController(ClassStudentService classStudentService, StudentService studentService){
        this.classStudentService = classStudentService;
        this.studentService = studentService;
    }


    //    Acquérir  les classes
    @RequestMapping(method = RequestMethod.GET, value = "/class_students")
    public List<ClassStudent> getClassStudents(){return classStudentService.getClassStudents();}

    //    Acquérir  une classe
    @RequestMapping(method = RequestMethod.GET, value = "/class_student/{id}")
    public ClassStudent getClassStudent(@PathVariable long id){return classStudentService.getClassStudent(id); }


    //    //    Ajouter une classe
    @RequestMapping(method =  RequestMethod.POST, value = "/class_student/{id}")
    public void addClassStudent(@RequestBody ClassStudent classStudent){classStudentService.addClassStudent(classStudent);}

//    //    Ajouter une classe avec des élèves
//    @RequestMapping(method = RequestMethod.POST, value = "/class_student_students/{id}")
//    public ResponseEntity<String> addClassStudents(@RequestBody ClassStudent classStudent) {
//        ClassStudent savedClassStudent = classStudentService.addClassStudent(classStudent);
//        if (savedClassStudent != null) {
//            // Vérifiez si la classe a des élèves dans son objet ClassStudent
//            if (classStudent.getStudents() != null && !classStudent.getStudents().isEmpty()) {
//                for (Student student : classStudent.getStudents()) {
//                    student.setClassId(savedClassStudent.getClassId());
//                    // Enregistrez le rôle dans la base de données
//                    studentService.addStudent(student);
//                }
//                return ResponseEntity.ok("La classe ajoutée avec succès avec une élève.");
//            }else {
//                return ResponseEntity.badRequest().body("La classe n'a pas de d'élève.");
//            }
//        } else {
//            return ResponseEntity.badRequest().body("Échec de l'ajout d'une classe.");
//        }
//    }

    //Modifier les informations d'une classe
    @RequestMapping(method =  RequestMethod.PUT, value = "/class_student/{id}")
    public void updateClassStudent(@RequestBody ClassStudent classStudent, @PathVariable long id){
        classStudent.setClassId(id);
        classStudentService.updateClassStudent(classStudent);
    }

    //Supprimer une classe
    @RequestMapping(method = RequestMethod.DELETE, value = "/class_student/{id}")
    public void deleteClassStudent(@PathVariable long id){classStudentService.deleteClassStudent(id);}
}
