package com.App.SunuScol.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "class_student")
public class ClassStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;
//    private  Long studentId;
    private String classLevel; //3eme, 1ere
//    private  Long Edt_id; //timeTable = emploi du temps


    public Long getClassId() {return classId;}

    public void setClassId(Long classId) {this.classId = classId;}

//    public Long getStudentId() {return studentId;}
//
//    public void setStudentId(Long studentId) {this.studentId = studentId;}

    public String getClassLevel() {return classLevel;}

    public void setClassLevel(String classLevel) {this.classLevel = classLevel;}
}
