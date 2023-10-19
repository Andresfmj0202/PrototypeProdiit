package com.Prodiit.Prototype.Services;

import com.Prodiit.Prototype.Models.Dtos.SiteDTO;
import com.Prodiit.Prototype.Models.Entitys.SiteEntity;
import com.Prodiit.Prototype.Respositorys.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;

    private SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    //crear y guardar un sitio
    public SiteEntity createAndSaveSite(SiteEntity siteEntity){
        return siteRepository.save(siteEntity);
    }



    //obtener todos los sitios
    public List<SiteEntity> getAllSites(){
        return siteRepository.findAll();
    }

    //obtener un sitio por id
    public Optional<SiteEntity> getSiteById(long siteId){
        return siteRepository.findById(siteId);
    }

    //obtener un sitio por nombre
    public List<SiteEntity> getSiteByName(String name){
        return siteRepository.findSiteByName(name);
    }

    //actualizar un sitio
    public SiteEntity updateSite(long siteId, SiteEntity siteEntity) {
        // Realiza cualquier validación necesaria aquí

        // Establece el ID de la entidad si no se ha establecido previamente
        siteEntity.setSiteId(siteId);

        // Llama al repositorio para guardar o actualizar la entidad
        return siteRepository.save(siteEntity);
    }

    //eliminar un sitio
    public void deleteSite(long siteId){
        siteRepository.deleteById(siteId);

    }

    //traer todos los sitios por la id de la compañia
    public List<SiteEntity> getSiteByCompanyId(UUID companyId) {
        return siteRepository.findSitesByCompanyId(companyId);
    }

    public SiteDTO mapSiteEntityToDTO(SiteEntity siteEntity) {
        SiteDTO siteDTO = new SiteDTO();
        siteDTO.setSiteId(siteEntity.getSiteId());
        siteDTO.setName(siteEntity.getName());
        siteDTO.setDescription(siteEntity.getDescription());
        siteDTO.setCompanyId(siteEntity.getCompany().getCompanyId()); // Si la relación con CompanyEntity está mapeada
        return siteDTO;
    }
    public List<SiteDTO> mapSiteEntitiesToDTOs(List<SiteEntity> siteEntities) {
        return siteEntities.stream()
                .map(this::mapSiteEntityToDTO)
                .collect(Collectors.toList());
    }
}
