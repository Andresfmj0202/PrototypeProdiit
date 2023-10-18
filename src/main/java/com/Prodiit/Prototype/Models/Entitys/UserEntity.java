package com.Prodiit.Prototype.Models.Entitys;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table (name = "user")
public class UserEntity {

    @Id
    private UUID UserId;

    private String name;
    private String email;
    private String password;
    private String salt;
    private String image;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "user_company",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    @JsonIdentityReference(alwaysAsId = true)
    private Set<CompanyEntity> companies = new HashSet<>();

    @JsonManagedReference
    @ManyToOne // Muchos usuarios pueden tener el mismo rol (relación muchos a uno)
    @JoinColumn(name = "role_id")
    private RoleEntity role;
    public UserEntity() {

    }

    public UserEntity(UUID userId, String name, String email, String password, String salt, String image, Set<CompanyEntity> companies, RoleEntity role) {
        UserId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.image = image;
        this.companies = companies;
        this.role = role;
    }

    public UUID getUserId() {
        return UserId;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<CompanyEntity> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<CompanyEntity> companies) {
        this.companies = companies;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}