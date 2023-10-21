package com.App.SunuScol.controller;

import com.App.SunuScol.model.ClassStudent;
import com.App.SunuScol.model.Student;
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

    //    Ajouter une classe avec des élèves
    @RequestMapping(method = RequestMethod.POST, value = "/class_student_students/{id}")
    public ResponseEntity<String> addClassStudents(@RequestBody ClassStudent classStudent) {
        ClassStudent savedClassStudent = classStudentService.addClassStudent(classStudent);
        if (savedClassStudent != null) {
            // Vérifiez si la classe a des élèves dans son objet ClassStudent
            if (classStudent.getStudents() != null && !classStudent.getStudents().isEmpty()) {
                for (Student student : classStudent.getStudents()) {
                    student.setClassId(savedClassStudent.getClassId());
                    // Enregistrez le rôle dans la base de données
                    studentService.addStudent(student);
                }
                return ResponseEntity.ok("Classe ajoutée avec succès avec une élève.");
            }else {
                return ResponseEntity.badRequest().body("La classe n'a pas d'élève.");
            }
        } else {
            return ResponseEntity.badRequest().body("Échec de l'ajout d'une classe.");
        }
    }



    //Modifier les informations d'une classe
    @RequestMapping(method =  RequestMethod.PUT, value = "/class_student/{id}")
    public void updateClassStudent(@RequestBody ClassStudent classStudent, @PathVariable long id){
        classStudent.setClassId(id);
        classStudentService.updateClassStudent(classStudent);
    }

    //Modifier les informations d'une classe avec un élève (impossible de mettre à jour plus d'un élève)
    @RequestMapping(method = RequestMethod.PUT, value = "/class_student_students/{idClass_student}/{idStudent}")
    public ResponseEntity<String> updateClassStudents(@PathVariable("idClass_student") Long idClass_student, @PathVariable("idStudent") Long idStudent,
                                                      @RequestBody ClassStudent updatedClassStudent) {
        // Vérifiez d'abord si la classe avec l'ID spécifié existe
        ClassStudent existingClassStudent = classStudentService.getClassStudent(idClass_student);

        if (existingClassStudent != null) {
            // Mettez à jour les détails de la classe à partir de la requête
            existingClassStudent.setClassLevel(updatedClassStudent.getClassLevel());

            // Enregistrez la mise à jour de la classe dans la base de données
            ClassStudent savedClassStudent = classStudentService.updateClassStudent(existingClassStudent);

            // Vérifiez si la classe a des rôles dans son objet User
            if (updatedClassStudent.getStudents() != null && !updatedClassStudent.getStudents().isEmpty()) {
                for (Student student : updatedClassStudent.getStudents()) {
                    // Assurez-vous que l'élève appartienne à la classe
                    student.setClassId(savedClassStudent.getClassId());
                    student.setStudentId(idStudent);
                    // Enregistrez ou mettez à jour le rôle dans la base de données
                    studentService.updateStudent(student);
                }
            }
            return ResponseEntity.ok("Utilisateur mis à jour avec succès avec des rôles.");
        } else {
            return ResponseEntity.ok("Id de cet utilisateur n'existe pas .");
//            return ResponseEntity.notFound().build();
        }
    }

    //Supprimer une classe
    @RequestMapping(method = RequestMethod.DELETE, value = "/class_student/{id}")
    public void deleteClassStudent(@PathVariable long id){classStudentService.deleteClassStudent(id);}
}
