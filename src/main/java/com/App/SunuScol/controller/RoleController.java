package com.App.SunuScol.controller;

import com.App.SunuScol.model.Role;
import com.App.SunuScol.model.User;
import com.App.SunuScol.service.RoleService;
import com.App.SunuScol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    private  final RoleService roleService;
    private  final UserService userService;

    @Autowired
    public RoleController(RoleService roleService, UserService userService){
        this.roleService = roleService;
        this.userService = userService;
    }

    //    Acquérir  les roles des utilisateurs
    @RequestMapping(method = RequestMethod.GET, value = "/roles")
    public List<Role> getRoles(){return roleService.getRoles();}

    //    Acquérir  un role
    @RequestMapping(method = RequestMethod.GET, value = "/role/{id}")
    public Role getRole(@PathVariable long id){return roleService.getRole(id); }

    //    Ajouter un role
    @RequestMapping(method =  RequestMethod.POST, value = "/role/{id}")
    public void addRole(@RequestBody Role role){roleService.addRole(role);}

    //    Ajouter un utilisateur avec ses roles
    @RequestMapping(method = RequestMethod.POST, value = "/user_roles/{id}")
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
            }else {
                return ResponseEntity.badRequest().body("L'utilisateur n'a pas de rôle spécifié.");
            }
        } else {
            return ResponseEntity.badRequest().body("Échec de l'ajout de l'utilisateur.");
        }
    }


    //Modifier les informations d'un utilisateur avec un role (possible de faire la mise à jour pour un role)
    @RequestMapping(method = RequestMethod.PUT, value = "/user_roles/{idUser}/{idRole}")
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

    //Modifier les informations d'un role
    @RequestMapping(method =  RequestMethod.PUT, value = "/role/{id}")
    public void updateRole(@RequestBody Role role, @PathVariable long id){
        role.setRoleId(id);
        roleService.updateRole(role);
    }

    //Supprimer un role
    @RequestMapping(method = RequestMethod.DELETE, value = "/role/{id}")
    public void deleteRole(@PathVariable long id){roleService.deleteRole(id);}

}