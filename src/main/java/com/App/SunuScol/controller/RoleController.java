package com.App.SunuScol.controller;

import com.App.SunuScol.model.Role;
import com.App.SunuScol.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    private  final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){this.roleService = roleService; }

    //    Acquérir  les roles des utilisateurs
    @RequestMapping(method = RequestMethod.GET, value = "/roles")
    public List<Role> getRoles(){return roleService.getRoles();}

    //    Acquérir  un role
    @RequestMapping(method = RequestMethod.GET, value = "/role/{id}")
    public Role getRole(@PathVariable long id){return roleService.getRole(id); }

    //    Ajouter un
    @RequestMapping(method =  RequestMethod.POST, value = "/role/{id}")
    public void addRole(@RequestBody Role role){roleService.addRole(role);}

    //Modifier les informations d'un
    @RequestMapping(method =  RequestMethod.PUT, value = "/role/{id}")
    public void updateRole(@RequestBody Role role, @PathVariable long id){
        role.setRoleId(id);
        roleService.updateRole(role);
    }

    //Supprimer un Role
    @RequestMapping(method = RequestMethod.DELETE, value = "/role/{id}")
    public void deleteRole(@PathVariable long id){roleService.deleteRole(id);}

}
