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

    //    Ajouter un utilisateur
    @RequestMapping(method = RequestMethod.POST, value = "/user/{id}")
    public ResponseEntity<String> addUserRoles(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        if (savedUser != null) {
            // Vérifiez si l'utilisateur a des rôles dans son objet User
            if (user.getRoles() != null && !user.getRoles().isEmpty()) {
                for (Role role : user.getRoles()) {
                    role.setUserId(savedUser.getUserId());
                    // Enregistrez le rôle dans la base de données
                    roleService.addRole(role);
                }
                return ResponseEntity.ok("Utilisateur ajouté avec succès avec un rôle.");
            } else {
                return ResponseEntity.badRequest().body("L'utilisateur n'a pas de rôle spécifié.");
            }
        } else {
            return ResponseEntity.badRequest().body("Échec de l'ajout de l'utilisateur.");
        }
    }

//    //    Ajouter un élève
//    @RequestMapping(method = RequestMethod.POST, value = "/student/{id}")
//    public ResponseEntity<String> addUserStudents(@RequestBody Student student) {
//        User savedStudents = userService.addUser(student);
//        if (savedStudents != null) {
//            // Vérifiez si l'utilisateur a des rôles dans son objet User
//            if (student.get != null && !student.getStudents().isEmpty()) {
//                for (Student student : student()) {
//                    student.setUserId(savedStudents.getUserId());
//                    // Enregistrez le rôle dans la base de données
//                    studentService.addStudent(student);
//                }
//                return ResponseEntity.ok("Utilisateur ajouté avec succès avec un rôle.");
//            } else {
//                return ResponseEntity.badRequest().body("L'utilisateur n'a pas de rôle spécifié.");
//            }
//        } else {
//            return ResponseEntity.badRequest().body("Échec de l'ajout de l'utilisateur.");
//        }
//    }

    //Modifier les informations d'un utilisateur (possible de faire la mise à jour pour un role)
    @RequestMapping(method = RequestMethod.PUT, value = "/user/{idUser}/{idRole}")
    public ResponseEntity<String> updateUserRoles(@PathVariable("idUser") Long idUser, @PathVariable("idRole") Long idRole, @RequestBody User updatedUser) {
        // Vérifiez d'abord si l'utilisateur avec l'ID spécifié existe
        User existingUser = userService.getUser(idUser);

        if (existingUser != null) {
            // Mettez à jour les détails de l'utilisateur à partir de la requête
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setUserName(updatedUser.getUserName());

            // Enregistrez la mise à jour de l'utilisateur dans la base de données
            User savedUser = userService.updateUser(existingUser);

            // Vérifiez si l'utilisateur a des rôles dans son objet User
            if (updatedUser.getRoles() != null && !updatedUser.getRoles().isEmpty()) {
                for (Role role : updatedUser.getRoles()) {
                    // Assurez-vous que le rôle appartienne à l'utilisateur
                    role.setUserId(savedUser.getUserId());
                    role.setRoleId(idRole);
//                    System.out.println("idRole : "+ idRole);

                    // Enregistrez ou mettez à jour le rôle dans la base de données
                    roleService.updateRole(role);
                }
            }

            return ResponseEntity.ok("Utilisateur mis à jour avec succès avec des rôles.");
        } else {
            return ResponseEntity.ok("Id de cet utilisateur n'existe pas .");
//            return ResponseEntity.notFound().build();
        }
    }

    //Supprimer un User
    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    public void deleteUser(@PathVariable long id){userService.deleteUser(id);}


}
