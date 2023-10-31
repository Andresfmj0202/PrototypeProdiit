package com.Prodiit.Prototype.Services;

import com.Prodiit.Prototype.Models.Dtos.CompanyDTO;
import com.Prodiit.Prototype.Models.Dtos.SiteDTO;
import com.Prodiit.Prototype.Models.Entitys.CompanyEntity;
import com.Prodiit.Prototype.Models.Entitys.SiteEntity;
import com.Prodiit.Prototype.Respositorys.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    private CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyEntity createAndSaveCompany(CompanyEntity companyEntity) {
        // Verificar que el nombre de la compañía no esté vacío
        String companyName = companyEntity.getName();
        if (companyName == null || companyName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la compañía no puede estar vacío.");
        }

        // Generar un ID único y asignarlo a la entidad
        companyEntity.setCompanyId(UUID.randomUUID());

        // Guardar la entidad en la base de datos
        return companyRepository.save(companyEntity);
    }
    //mapear entity
    public CompanyEntity mapToEntity(CompanyDTO companyDTO) {
        CompanyEntity entity = new CompanyEntity();
        entity.setCompanyId(UUID.randomUUID()); // Generar un ID único
        entity.setName(companyDTO.getName());
        entity.setDescription(companyDTO.getDescription());
        entity.setImageLogo(companyDTO.getImageLogo());
        // Mapea otros campos si es necesario
        return entity;
    }

    //mapear a dto
    public CompanyDTO mapToDTO(CompanyEntity companyEntity) {
        CompanyDTO dto = new CompanyDTO();
        dto.setCompanyId(companyEntity.getCompanyId());
        dto.setName(companyEntity.getName());
        dto.setDescription(companyEntity.getDescription());
        dto.setImageLogo(companyEntity.getImageLogo());
        // Puedes agregar más campos según tus necesidades.
        return dto;
    }

    public List<CompanyEntity> getAllCompanies() {
        List<CompanyEntity> companies = companyRepository.findAll();

        // Crear un mapa para almacenar el recuento de sitios por empresa
        Map<UUID, Integer> siteCounts = new HashMap<>();

        for (CompanyEntity company : companies) {
            // Contar el número de sitios en la empresa
            int siteCount = company.getSites().size();

            // Actualizar el recuento de sitios en la entidad de empresa
            siteCounts.put(company.getCompanyId(), siteCount);
        }

        // Establecer el recuento de sitios en cada empresa
        for (CompanyEntity company : companies) {
            int siteCount = siteCounts.get(company.getCompanyId());
            company.setSiteCount(siteCount);
        }

        return companies;
    }

    //
    public List<CompanyDTO> mapToDTOList(List<CompanyEntity> companyEntities) {
        List<CompanyDTO> companyDTOs = new ArrayList<>();
        for (CompanyEntity entity : companyEntities) {
            companyDTOs.add(mapToDTO(entity));
        }
        return companyDTOs;
    }

    //
    public Optional<CompanyEntity> getCompanyById(UUID id){
        return companyRepository.findById(id);
    }

    public List<CompanyEntity> getCompanyByName(String name){
        return companyRepository.findCompanyByName(name);
    }

    public CompanyEntity updateCompany(UUID id, CompanyEntity companyEntity) {
        // Realiza cualquier validación necesaria aquí

        // Establece el ID de la entidad si no se ha establecido previamente
        companyEntity.setCompanyId(id);

        // Llama al repositorio para guardar o actualizar la entidad
        return companyRepository.save(companyEntity);
    }

    public void deleteCompany(UUID id){
        companyRepository.deleteById(id);
    }


    //total de sitios
    public int getSiteCountForCompany(UUID companyId) {
        Optional<CompanyEntity> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            return company.get().getSiteCount();
        } else {
            return 0; // Maneja el caso de que la compañía no exista
        }
    }

}
