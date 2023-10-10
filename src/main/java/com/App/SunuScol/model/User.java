package com.App.SunuScol.model;

import jakarta.persistence.*;

@Entity
@NamedStoredProcedureQuery(
        name = "User.addUser",
        procedureName = "AddUser",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_lastName"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_firstName"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_password"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_email"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_birthDay"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_birthPlace"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_adress")
        }
)

@NamedStoredProcedureQuery(
        name = "User.getUser",
        procedureName = "GetUser",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userId", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "p_lastName"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "p_firstName"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "p_password"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "p_email"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "p_birthDay"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "p_birthPlace"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "p_adress")
        },
        resultClasses = User.class
)

@NamedStoredProcedureQuery(
        name = "User.updateUser",
        procedureName = "UpdateUser",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_userId"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_lastName"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_firstName"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_password"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_email"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_birthDay"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_birthPlace"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_adress")
        }
)

@NamedStoredProcedureQuery(
        name = "User.deleteUser",
        procedureName = "DeleteUser",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_userId")
        }
)


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String lastName;
    private String firstName;
    private String password;
    private String email;
    private String birthDay;
    private String birthPlace;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
    private String adress;




    public User() {
    }

    public User(Long userId, String lastName, String firstName, String password, String email, String birthDay, String birthPlace, String adress) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
        this.email = email;
        this.birthDay = birthDay;
        this.birthPlace = birthPlace;
        this.adress = adress;
    }

    public Long getUserId() {return userId;}

    public void setUserId(Long userId) {this.userId = userId;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}


