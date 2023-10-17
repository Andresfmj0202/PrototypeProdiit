package com.Prodiit.Prototype.Models.Dtos;

import java.util.List;

public class RoleDTO {
    private long roleId;
    private String name;
    private String description;
    private List<UserDTO> users; // Lista de usuarios asociados a este rol

    public RoleDTO() {
    }

    public RoleDTO(long roleId, String name, String description, List<UserDTO> users) {
        this.roleId = roleId;
        this.name = name;
        this.description = description;
        this.users = users;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
