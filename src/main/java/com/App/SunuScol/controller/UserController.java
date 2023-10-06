package com.App.SunuScol.controller;

import com.App.SunuScol.model.User;
import com.App.SunuScol.repository.UserRepository;
import com.App.SunuScol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private  final UserService userService;

    @Autowired
    public UserController(UserService userService){this.userService = userService; }

//    Acquérir  les utilisateurs
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<User> getUsers(){return userService.getUsers();}

    //    Acquérir  un utilisateurs
    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public User getUser(@PathVariable long id){return userService.getUser(id); }

    //    Ajouter un utilisateur
    @RequestMapping(method =  RequestMethod.POST, value = "/user/{id}")
    public void addUser(@RequestBody User user){userService.addUser(user);}

    //Modifier les informations d'un utilisateur
    @RequestMapping(method =  RequestMethod.PUT, value = "/user/{id}")
    public void updateUser(@RequestBody User user, @PathVariable long id){
        userService.updateUser(user, id);
    }

    //Supprimer un User
    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    public void deleteUser(@PathVariable long id){userService.deleteUser(id);}


}
