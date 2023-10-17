package com.App.SunuScol.service;

import com.App.SunuScol.model.User;
import com.App.SunuScol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){this.userRepository = userRepository;}

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            users.add(user);
        });
        return users;
    }
    public User getUser(long id) { return userRepository.findById(id).orElse(null); }

    public User addUser(User user) { return userRepository.save(user);}
    public User updateUser(User user) { userRepository.save(user);
        return user;
    }
    public void deleteUser(long id) { userRepository.deleteById(id);  }

}