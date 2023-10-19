package com.App.SunuScol.controller;

import com.App.SunuScol.model.Role;
import com.App.SunuScol.model.Student;
import com.App.SunuScol.model.User;
import com.App.SunuScol.repository.StudentRepository;
import com.App.SunuScol.service.RoleService;
import com.App.SunuScol.service.StudentService;
import com.App.SunuScol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private  final UserService userService;
    private final RoleService roleService;
    public  final StudentService studentService;

    @Autowired
    public UserController(UserService userService, RoleService roleService, StudentService studentService){
        this.userService = userService;
        this.roleService = roleService;
        this.studentService = studentService;
    }

//    Acquérir  les utilisateurs
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<User> getUsers(){return userService.getUsers();}

    //    Acquérir  un utilisateurs
    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public User getUser(@PathVariable long id){return userService.getUser(id); }

    //Supprimer un User
    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    public void deleteUser(@PathVariable long id){userService.deleteUser(id);}


}
