package com.Prodiit.Prototype.Models.Dtos;

import java.util.UUID;

public class UserDTO {
    private UUID userId;
    private String name;
    private String email;
    private String image;
    private long roleId; // Representa el ID de la entidad RoleEntity
    // Otros campos según tus necesidades

    private String password;

    private boolean status;

    // Constructores, getters y setters

    public UserDTO() {
        // Constructor vacío
    }

    public UserDTO(UUID userId, String name, String email, String image, long roleId, String password, boolean status) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.image = image;
        this.roleId = roleId;
        this.password = password;
        this.status = status;
    }

    // Getters y setters para los campos

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
