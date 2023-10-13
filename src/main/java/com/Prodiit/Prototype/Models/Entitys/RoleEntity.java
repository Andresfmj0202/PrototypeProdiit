package com.Prodiit.Prototype.Models.Entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "rol")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long RoleId;

    private String name;
    private String description;

    @JsonBackReference
    @OneToMany(mappedBy = "role") // Un rol puede estar asociado a varios usuarios (relación uno a muchos)
    private List<UserEntity> users; // Colección de usuarios asociados a este rol



    public RoleEntity() {
    }

    public RoleEntity(String name, String description, List<UserEntity> users) {
        this.name = name;
        this.description = description;
        this.users = users;
    }

    public long getRoleId() {
        return RoleId;
    }

    public void setRoleId(long id) {
        this.RoleId = id;
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
