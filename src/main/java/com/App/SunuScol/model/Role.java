package com.App.SunuScol.model;

import jakarta.persistence.*;


@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String roleName;
    private String permissions;
    private Long userId;

    public Long getRoleId() {return roleId;}

    public void setRoleId(Long roleId) {this.roleId = roleId;}

    public String getRoleName() {return roleName;}

    public void setRoleName(String roleName) {this.roleName = roleName;}
    public String getPermissions() {return permissions;}
    public void setPermissions(String permissions) {this.permissions = permissions;}

    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}
}