package com.Prodiit.Prototype.Services;

import com.Prodiit.Prototype.Models.Dtos.AreaDTO;
import com.Prodiit.Prototype.Models.Entitys.AreaEntity;
import com.Prodiit.Prototype.Respositorys.AreaRepository;
import com.Prodiit.Prototype.Respositorys.SiteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;
    private SiteRepository siteRepository;

    public AreaService(AreaRepository areaRepository, SiteRepository siteRepository) {
        this.areaRepository = areaRepository;
        this.siteRepository = siteRepository;
    }

    public AreaEntity createAndSaveArea(AreaEntity areaEntity) {
        return areaRepository.save(areaEntity);
    }



    public AreaDTO mapToDTO(AreaEntity areaEntity) {
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setAreaId(areaEntity.getAreaId());
        areaDTO.setName(areaEntity.getName());
        areaDTO.setType(areaEntity.getType());
        areaDTO.setDateCreated(areaEntity.getDateCreated());
        areaDTO.setSiteId(areaEntity.getSite().getSiteId());
        return areaDTO;
    }

    public List<AreaDTO> getAllAreas() {
        List<AreaEntity> areaEntities = areaRepository.findAll();
        List<AreaDTO> areaDTOs = new ArrayList<>();
        for (AreaEntity areaEntity : areaEntities) {
            areaDTOs.add(mapToDTO(areaEntity));
        }
        return areaDTOs;
    }

    public Optional<AreaEntity> getAreaById(UUID areaId) {
        return areaRepository.findById(areaId);
    }



    public List<AreaDTO> getAreaByName(String name) {
        List<AreaEntity> areaEntities = areaRepository.findAreaByName(name);
        return mapAreaEntitiesToDTOs(areaEntities);
    }

    private List<AreaDTO> mapAreaEntitiesToDTOs(List<AreaEntity> areaEntities) {
        List<AreaDTO> areaDTOs = new ArrayList<>();
        for (AreaEntity areaEntity : areaEntities) {
            areaDTOs.add(mapToDTO(areaEntity));
        }
        return areaDTOs;
    }

    //traer todas las areas de un sitio por la id de la(parencia)
   public List<AreaEntity> getAreaBySiteId(Long siteId){
        return areaRepository.findAreasBySite(siteId);
    }

    public AreaDTO updateArea(UUID areaId, AreaDTO areaDTO) {
        Optional<AreaEntity> areaEntityOptional = areaRepository.findById(areaId);
        if (areaEntityOptional.isPresent()) {
            AreaEntity areaEntity = areaEntityOptional.get();
            // Actualiza los campos del área con los valores del DTO
            areaEntity.setName(areaDTO.getName());
            areaEntity.setType(areaDTO.getType());
            // Puedes agregar más campos según sea necesario

            // Guarda los cambios
            areaRepository.save(areaEntity);

            // Devuelve el DTO actualizado
            return mapToDTO(areaEntity);
        } else {
            // Maneja el caso en el que el área no existe
            return null;  // O puedes devolver un ResponseEntity con un código de error apropiado
        }
    }


    public void deleteAreaById(UUID areaId) {
        areaRepository.deleteById(areaId);
    }

    public AreaEntity updateStatusArea(UUID areaId) {
        AreaEntity areaEntity = areaRepository.findById(areaId)
                .orElseThrow(() -> new EntityNotFoundException("Area no encontrada"));

        areaEntity.setStatusArea(!areaEntity.isStatusArea());
        return areaRepository.save(areaEntity);
    }
}
