package com.Prodiit.Prototype.Respositorys;

import com.Prodiit.Prototype.Models.Entitys.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AreaRepository extends JpaRepository<AreaEntity, UUID> {
    void flush(); // Declaración del método flush

   @Query("SELECT a FROM AreaEntity a WHERE a.site.siteId = :siteId")
    List<AreaEntity> findAreasBySite(@Param("siteId") Long siteId);

    List<AreaEntity> findAreaByName(String name);
}
