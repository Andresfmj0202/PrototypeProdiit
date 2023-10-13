package com.Prodiit.Prototype.Services;

import com.Prodiit.Prototype.Models.Dtos.CompanyDTO;
import com.Prodiit.Prototype.Models.Entitys.CompanyEntity;
import com.Prodiit.Prototype.Respositorys.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    private CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyEntity createAndSaveCompany(CompanyEntity companyEntity) {
        // Generar un ID único y asignarlo a la entidad
        companyEntity.setCompanyId(UUID.randomUUID());
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


    public List<CompanyEntity> getAllCompanies(){
        return companyRepository.findAll();
    }
    //
    public List<CompanyDTO> mapToDTOList(List<CompanyEntity> companyEntities) {
        List<CompanyDTO> companyDTOs = new ArrayList<>();
        for (CompanyEntity entity : companyEntities) {
            companyDTOs.add(mapToDTO(entity));
        }
        return companyDTOs;
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


    //
    public Optional<CompanyEntity> getCompanyById(UUID id){
        return companyRepository.findById(id);
    }

    public List<CompanyEntity> getCompanyByName(String name){
        return companyRepository.findCompanyByName(name);
    }

    public CompanyEntity updateCompany(UUID id, CompanyEntity companyEntity){
        companyEntity.setCompanyId(id);
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
