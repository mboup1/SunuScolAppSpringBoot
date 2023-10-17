package com.App.SunuScol.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String roleName;
    private String permissions;
    private Long userId;

    //Relation bidirectionnelle @ManyToMany avec user
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(Long roleId, String roleName, String permissions, Long userId) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.permissions = permissions;
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermissions() {return permissions;}

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {return users;}
    public void setUsers(List<User> users) {this.users = users;}
        public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}
}