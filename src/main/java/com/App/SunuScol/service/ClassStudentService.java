package com.App.SunuScol.service;

import com.App.SunuScol.model.ClassStudent;
import com.App.SunuScol.repository.ClassStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassStudentService {
    private final ClassStudentRepository classStudentRepository;
    @Autowired
    public ClassStudentService(ClassStudentRepository classStudentRepository){this.classStudentRepository = classStudentRepository;}

    public List<ClassStudent> getClassStudents() {
        List<ClassStudent> classStudents = new ArrayList<>();
        classStudentRepository.findAll().forEach(classStudent -> {
            classStudents.add(classStudent);
        });
        return classStudents;
    }

    public ClassStudent getClassStudent(long id) { return classStudentRepository.findById(id).orElse(null); }

    public ClassStudent addClassStudent(ClassStudent classStudent) {return classStudentRepository.save(classStudent);}

    public ClassStudent updateClassStudent(ClassStudent classStudent) { classStudentRepository.save(classStudent);
        return classStudent;
    }
    public void deleteClassStudent(long id) { classStudentRepository.deleteById(id);  }

}
