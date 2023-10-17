package com.App.SunuScol.service;

import com.App.SunuScol.model.Role;
import com.App.SunuScol.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){this.roleRepository = roleRepository;}

    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(role -> {
            roles.add(role);
        });
        return roles;
    }

    public Role getRole(long id) { return roleRepository.findById(id).orElse(null); }

    public Role addRole(Role role) {return roleRepository.save(role);}

    public void updateRole(Role role) { roleRepository.save(role); }
    public void deleteRole(long id) { roleRepository.deleteById(id);  }
}
