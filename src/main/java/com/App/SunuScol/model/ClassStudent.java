package com.App.SunuScol.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "class_student")
@Entity
public class ClassStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;
//    private  Long studentId;
    private String classLevel; //3eme, 1ere
//    private  Long Edt_id; //timeTable = emploi du temps

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            }
    )
    @JoinTable(
            name = "class_students_student",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    List<Student> students = new ArrayList<>();


    public Long getClassId() {return classId;}

    public void setClassId(Long classId) {this.classId = classId;}

//    public Long getStudentId() {return studentId;}
////
//    public void setStudentId(Long studentId) {this.studentId = studentId;}

    public String getClassLevel() {return classLevel;}

    public void setClassLevel(String classLevel) {this.classLevel = classLevel;}

    public List<Student> getStudents() {return students;}

    public void setStudents(List<Student> students) {this.students = students;}
}
