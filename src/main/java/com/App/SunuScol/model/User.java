package com.App.SunuScol.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
//Pour mettre à jour les données modifiées mais pas toutes
@DynamicUpdate
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")

    private Long userId;
    @Column(name = "user_name")
    private String UserName;

    @Column(name = "email")
    private String email;

    @Column(name = "class_id")
    private Long classId;


    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            }
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

//    //Probléme à résoudre ultérieurement
//    @OneToMany(
////            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            //Récupérer l'utilisateur  et les élèves associés
//            fetch = FetchType.EAGER
//    )
//    @JoinColumn(name = "user2_id")
//List<Student> students = new ArrayList<>();

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE,
        }
    )
    @JoinTable(
            name = "user_students",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    List<Student> students = new ArrayList<>();

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            }
    )
    @JoinTable(
            name = "user_classStudents",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    List<ClassStudent> classstudents = new ArrayList<>();

    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}

    public String getUserName() {return UserName;}
    public void setUserName(String userName) {this.UserName = userName;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public List<Role> getRoles() {return roles;}
    public void setRoles(List<Role> roles) {this.roles = roles;}

    public List<Student> getStudents() {return students;}
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<ClassStudent> getClassstudents() {
        return classstudents;
    }

    public void setClassstudents(List<ClassStudent> classstudents) {
        this.classstudents = classstudents;
    }

    public Long getClassId() {return classId;}
    public void setClassId(Long classId) {this.classId = classId;}
}