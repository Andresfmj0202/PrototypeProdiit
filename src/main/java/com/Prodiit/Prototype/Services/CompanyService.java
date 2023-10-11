package com.Prodiit.Prototype.Services;

import com.Prodiit.Prototype.Models.Entitys.CompanyEntity;
import com.Prodiit.Prototype.Respositorys.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public List<CompanyEntity> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Optional<CompanyEntity> getCompanyById(UUID id){
        return companyRepository.findById(id);
    }

    public Optional<CompanyEntity> getCompanyByName(String name){
        return companyRepository.findCompanyByName(name);
    }

    public CompanyEntity updateCompany(UUID id, CompanyEntity companyEntity){
        companyEntity.setCompanyId(id);
        return companyRepository.save(companyEntity);
    }

    public void deleteCompany(UUID id){
        companyRepository.deleteById(id);
    }
}
