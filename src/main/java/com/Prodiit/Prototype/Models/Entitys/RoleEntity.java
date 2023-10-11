package com.Prodiit.Prototype.Models.Entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table (name = "rol")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long RoleId;

    private String name;
    private String description;


    @JsonIgnoreProperties("role") // Ignora la propiedad "role" en la entidad UserEntity
    @OneToOne(mappedBy = "role", cascade = CascadeType.ALL)
    private UserEntity user;


    public RoleEntity() {
    }

    public RoleEntity(String name, String description) {
        this.name = name;
        this.description = description;
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

}
