package com.Prodiit.Prototype.Models.Entitys;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table (name = "company")
public class CompanyEntity {

    @Id
    private UUID CompanyId;

    private String name;

    private String description;

    private String imageLogo;

    @ManyToMany(mappedBy = "companies")
    private Set<UserEntity> users = new HashSet<>();

    public CompanyEntity() {
    }

    public CompanyEntity(UUID CompanyId, String name, String description, String imageLogo, Set<UserEntity> users) {
        this.CompanyId = CompanyId;
        this.name = name;
        this.description = description;
        this.imageLogo = imageLogo;
        this.users = users;
    }

    public UUID getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(UUID id) {
        this.CompanyId = id;
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

    public String getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(String imageLogo) {
        this.imageLogo = imageLogo;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
