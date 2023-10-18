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
    private Long userId;

    private String UserName;
    private String email;

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

//    @OneToMany(
////            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            //Récupérer l'utilisateur  et les élèves associés
//            fetch = FetchType.EAGER
//    )
////    @JoinColumn(name = "user_id")
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



//    private String password;
//    private String email;
//    private String birthDay;
//    private String birthPlace;
//    private String adress;
//    private Long roleId;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Role role;

//    @ManyToOne
//    @JoinColumn(name = "roleId")
//    private Role role;


//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "role_id", referencedColumnName = "roleId")
//    private Role role;

    public User() {
    }

    public User(Long userId, String userName, String email, List<Role> roles, List<Student> students) {
        this.userId = userId;
        UserName = userName;
        this.email = email;
        this.roles = roles;
        this.students = students;
    }

    public Long getUserId() {return userId;}

    public void setUserId(Long userId) {this.userId = userId;}

    public String getUserName() {return UserName;}

    public void setUserName(String userName) {this.UserName = userName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public List<Role> getRoles() {return roles;}

    public void setRoles(List<Role> roles) {this.roles = roles;}

    //Méthodes utilitaires
    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}





    //    public String getPassword() {return password;}
//
//    public void setPassword(String password) {this.password = password;}
//
//    public String getEmail() {return email;}
//
//    public void setEmail(String email) {this.email = email;}
//
//    public String getBirthDay() {
//        return birthDay;
//    }
//
//    public void setBirthDay(String birthDay) {
//        this.birthDay = birthDay;
//    }
//
//    public String getBirthPlace() {
//        return birthPlace;
//    }
//
//    public void setBirthPlace(String birthPlace) {
//        this.birthPlace = birthPlace;
//    }
//
//    public String getAdress() {
//        return adress;
//    }
//
//    public void setAdress(String adress) {
//        this.adress = adress;
//    }
//
//    public Long getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(Long roleId) {
//        this.roleId = roleId;
//    }