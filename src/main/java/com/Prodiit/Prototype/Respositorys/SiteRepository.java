package com.Prodiit.Prototype.Respositorys;

import com.Prodiit.Prototype.Models.Entitys.SiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SiteRepository extends JpaRepository<SiteEntity, Long> {
    List<SiteEntity> findSiteByName(String name);

    @Query("SELECT s FROM SiteEntity s WHERE s.company.id = :companyId")
    List<SiteEntity> findSitesByCompanyId(@Param("companyId") UUID companyId);
}
