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

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<User> getUsers(){return userService.getUsers();}

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public User getUser(@PathVariable long id){return userService.getUser(id); }

    @RequestMapping(method =  RequestMethod.POST, value = "/user/{id}")
    public void addUser(@RequestBody User user){userService.addUser(user);}


}
