package com.App.SunuScol.model;

import jakarta.persistence.*;

@Entity
//@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private Long userId;
    private Long classId;
    private String lastName;
    private String firstName;


//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user; // Relation many-to-one vers User

//    public Student() {
//    }

//    public Student(Long studentId, Long userId, String lastName, String firstName) {
//        this.studentId = studentId;
//        this.userId = userId;
//        this.lastName = lastName;
//        this.firstName = firstName;
//    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClassId() {return classId;}
    public void setClassId(Long classId) {this.classId = classId;}

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



}
