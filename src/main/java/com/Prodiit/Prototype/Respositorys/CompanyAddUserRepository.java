package com.Prodiit.Prototype.Respositorys;

import com.Prodiit.Prototype.Models.Entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CompanyAddUserRepository extends JpaRepository<UserEntity, UUID> {


    @Modifying
    @Query("UPDATE UserEntity u SET u.companies = (SELECT c FROM CompanyEntity c WHERE c.CompanyId = :companyId) WHERE u.UserId = :userId")
    void addCompanyToUser(@Param("userId") UUID userId, @Param("companyId") UUID companyId);



    @Modifying
    @Query("UPDATE UserEntity u SET u.companies = (SELECT c FROM CompanyEntity c WHERE c.CompanyId = :companyId) WHERE u.UserId = :userId")
    void addUserToCompany(@Param("userId") UUID userId, @Param("companyId") UUID companyId);



}
